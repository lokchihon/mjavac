package com.nvankempen.csc444.mjava;

import com.beust.jcommander.JCommander;
import com.nvankempen.csc444.mjava.ast.analysis.PrintVisitor;
import com.nvankempen.csc444.mjava.ast.analysis.TypeCheckVisitor;
import com.nvankempen.csc444.mjava.ast.nodes.*;
import com.nvankempen.csc444.mjava.codegen.JasminVisitor;
import com.nvankempen.csc444.mjava.parser.VisitorParser;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.ParseTree;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;

public class MiniJava {

    public static void main(String[] args) throws IOException {

        CLIParameters params = new CLIParameters();
        JCommander.newBuilder().addObject(params).build().parse(args);

        Path input  = new File(params.getFilename()).toPath();
        Path output = new File(params.getOutputDirectory()).toPath();

        MiniJavaParser parser = new MiniJavaParser(
                new CommonTokenStream(
                        new MiniJavaLexer(
                                CharStreams.fromPath(input)
                        )
                )
        );

        if (!Files.exists(output)) {
            Files.createDirectories(output);
        } else {
            if (!Files.isDirectory(output)) {
                System.out.println("[ERROR] The MJava compiler needs a valid directory to output generated files!");
                return;
            }
        }

        ParserErrorListener errorListener = new ParserErrorListener();
        parser.removeErrorListeners();
        parser.addErrorListener(errorListener);

        ParseTree tree = parser.program();

        if (!errorListener.hasSyntaxErrors()) {

            Program program = (new VisitorParser()).parse(tree);
            TypeCheckVisitor check = new TypeCheckVisitor();
            check.visit(program);
            if (!check.getErrorHandler().hasErrors()) {

                if (params.prettify()) {
                    PrintStream out = new PrintStream(new File(output.toFile(), input.getFileName().toString() + ".pretty"));
                    PrintVisitor print = new PrintVisitor(out);
                    print.visit(program);
                    out.close();
                }

                JasminVisitor v = new JasminVisitor(output);
                v.visit(program);
                v.getGeneratedFiles().forEach(f -> {
                    try {
                        JasminUtil.assemble(f, output);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
            }
        }
    }
}
