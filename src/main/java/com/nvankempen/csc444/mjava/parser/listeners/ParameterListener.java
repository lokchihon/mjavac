package com.nvankempen.csc444.mjava.parser.listeners;

import com.nvankempen.csc444.mjava.MiniJavaParser;
import com.nvankempen.csc444.mjava.MiniJavaParserBaseListener;
import com.nvankempen.csc444.mjava.ast.nodes.Formal;
import com.nvankempen.csc444.mjava.ast.nodes.FormalList;
import com.nvankempen.csc444.mjava.ast.nodes.Identifier;

import java.util.ArrayList;
import java.util.List;

public class ParameterListener extends MiniJavaParserBaseListener {
    private List<Formal> parameters = new ArrayList<>();

    public FormalList getParameters() {
        return new FormalList(parameters);
    }

    @Override
    public void enterParameters(MiniJavaParser.ParametersContext ctx) {
        for (int i = 0; i < ctx.type().size(); ++i) {
            TypeListener type = new TypeListener();
            ctx.type(i).enterRule(type);

            parameters.add(
                    new Formal(type.getType(),
                            new Identifier(ctx.IDENTIFIER(i).getText())
                    )
            );
        }
    }
}
