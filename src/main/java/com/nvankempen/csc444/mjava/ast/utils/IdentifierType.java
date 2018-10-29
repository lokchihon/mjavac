package com.nvankempen.csc444.mjava.ast.utils;

import com.nvankempen.csc444.mjava.ast.nodes.Identifier;

public class IdentifierType extends Type {

    private Identifier type;

    public IdentifierType(Identifier type) {
        this.type = type;
    }

    public Identifier getIdentifier() {
        return type;
    }

    @Override
    public String getName() {
        if (type == null) return null;

        return type.getName();
    }
}
