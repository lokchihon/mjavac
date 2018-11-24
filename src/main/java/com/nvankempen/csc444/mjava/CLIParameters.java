package com.nvankempen.csc444.mjava;

import com.beust.jcommander.Parameter;

public class CLIParameters {

    @Parameter(description = "The MiniJava file to load and compile.", required = true)
    private String filename;

    @Parameter(
            names = { "-o", "-output", "--output" },
            description = "The directory to which output the .j and .class files. Must be empty or non-existent.",
            required = true
    )
    private String output;

    @Parameter(
            names = { "-p", "-pretty", "--pretty" },
            description = "Generate a \"prettified\" version of the MiniJava code in the output directory."
    )
    private boolean pretty = false;

    public String getFilename() {
        return filename;
    }

    public String getOutputDirectory() {
        return output;
    }

    public boolean prettify() {
        return pretty;
    }
}
