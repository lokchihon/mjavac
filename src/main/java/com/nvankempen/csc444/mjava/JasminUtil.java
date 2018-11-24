package com.nvankempen.csc444.mjava;

import jasmin.ClassFile;

import java.io.*;
import java.nio.file.Path;

public class JasminUtil {
    public static void assemble(Path filename, Path output) throws Exception {

        FileInputStream in = new FileInputStream(filename.toFile());
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));

        ClassFile file = new ClassFile();
        file.readJasmin(reader, filename.toFile().getName(), true);
        in.close();

        if (file.errorCount() > 0) {
            System.out.println("[ERROR] There were errors assembling the .j file: " + filename.toString());
            return;
        }

        File out = new File(output.toFile(), file.getClassName() + ".class");
        FileOutputStream outs = new FileOutputStream(out);
        file.write(outs);
        outs.close();
        System.out.println("Generated: " + out.toPath().toString());
    }
}
