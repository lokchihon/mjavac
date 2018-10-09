package com.nvankempen.csc444.mjava.ast.nodes;

import java.util.Iterator;
import java.util.List;
import java.util.Spliterator;
import java.util.function.Consumer;

public class VarDeclarationList implements Iterable<VarDeclaration> {
    private List<VarDeclaration> variables;

    public VarDeclarationList(List<VarDeclaration> variables) {
        this.variables = variables;
    }

    @Override
    public Iterator<VarDeclaration> iterator() {
        return variables.iterator();
    }

    @Override
    public void forEach(Consumer<? super VarDeclaration> action) {
        variables.forEach(action);
    }

    @Override
    public Spliterator<VarDeclaration> spliterator() {
        return variables.spliterator();
    }
}
