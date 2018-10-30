package com.nvankempen.csc444.mjava;

import com.nvankempen.csc444.mjava.ast.analysis.TypeCheckVisitor;
import com.nvankempen.csc444.mjava.ast.nodes.Program;
import com.nvankempen.csc444.mjava.parser.VisitorParser;
import com.nvankempen.csc444.mjava.utils.TestErrorHandler;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.*;

public class TypeCheckTest {

    private Program getProgram(String filename) throws IOException {
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        InputStream in = loader.getResourceAsStream(filename);

        MiniJavaParser parser = new MiniJavaParser(new CommonTokenStream(new MiniJavaLexer(CharStreams.fromStream(in))));

        ParserErrorListener errorListener = new ParserErrorListener();
        parser.removeErrorListeners();
        parser.addErrorListener(errorListener);

        ParseTree tree = parser.program();

        assertFalse(errorListener.hasSyntaxErrors(), String.format("There were syntax errors in the test file '%s'.", filename));
        return (new VisitorParser()).parse(tree);
    }

    @Test
    void testInheritance() throws IOException {
        Program program = getProgram("Inheritance.mjava");
        TypeCheckVisitor check = new TypeCheckVisitor();
        TestErrorHandler errors = new TestErrorHandler();
        check.setErrorHandler(errors);
        check.visit(program);

        assertTrue(errors.atLine(36), "Type check should detect the cycle.");
        assertTrue(errors.atLine(48), "The superclass does not exist.");
        assertEquals(2, errors.nb(), "Too many errors were detected.");
    }

    @Test
    void testSamples() throws IOException {
        for (String file : new String[]{ "Factorial", "BinarySearch", "BubbleSort", "TreeVisitor", "QuickSort", "LinearSearch", "LinkedList", "BinaryTree" }) {
            Program program = getProgram(file + ".mjava");
            TypeCheckVisitor check = new TypeCheckVisitor();
            TestErrorHandler errors = new TestErrorHandler();
            check.setErrorHandler(errors);
            check.visit(program);

            assertEquals(0, errors.nb(), String.format("Type check errors were found in sample file '%s'.", file));
        }
    }

    @Test
    void testOverloadOverride() throws IOException {
        Program program = getProgram("OverloadOverride.mjava");
        TypeCheckVisitor check = new TypeCheckVisitor();
        TestErrorHandler errors = new TestErrorHandler();
        check.setErrorHandler(errors);
        check.visit(program);

        assertTrue(errors.atLine(3), "System.out.println can only use int.");
        assertTrue(errors.atLine(11), "Duplicate method.");
        assertTrue(errors.atLine(17), "Overload on return type.");

    }
}
