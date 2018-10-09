package com.nvankempen.csc444.mjava.ast.nodes;

import java.util.Iterator;
import java.util.List;
import java.util.Spliterator;
import java.util.function.Consumer;

public class MethodDeclarationList implements Iterable<MethodDeclaration> {
    private List<MethodDeclaration> methods;

    public MethodDeclarationList(List<MethodDeclaration> methods) {
        this.methods = methods;
    }

    @Override
    public Iterator<MethodDeclaration> iterator() {
        return methods.iterator();
    }

    @Override
    public void forEach(Consumer<? super MethodDeclaration> action) {
        methods.forEach(action);
    }

    @Override
    public Spliterator<MethodDeclaration> spliterator() {
        return methods.spliterator();
    }
}
