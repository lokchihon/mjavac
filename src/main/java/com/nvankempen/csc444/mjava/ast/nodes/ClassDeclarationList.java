package com.nvankempen.csc444.mjava.ast.nodes;

import java.util.Iterator;
import java.util.List;
import java.util.Spliterator;
import java.util.function.Consumer;

public class ClassDeclarationList implements Iterable<ClassDeclaration> {
    private List<ClassDeclaration> classes;

    public ClassDeclarationList(List<ClassDeclaration> classes) {
        this.classes = classes;
    }

    @Override
    public Iterator<ClassDeclaration> iterator() {
        return classes.iterator();
    }

    @Override
    public void forEach(Consumer<? super ClassDeclaration> action) {
        classes.forEach(action);
    }

    @Override
    public Spliterator<ClassDeclaration> spliterator() {
        return classes.spliterator();
    }
}
