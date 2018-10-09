package com.nvankempen.csc444.mjava.ast.nodes;

import java.util.Iterator;
import java.util.List;
import java.util.Spliterator;
import java.util.function.Consumer;

public class FormalList implements Iterable<Formal> {

    private List<Formal> formals;

    public FormalList(List<Formal> formals) {
        this.formals = formals;
    }

    @Override
    public Iterator<Formal> iterator() {
        return formals.iterator();
    }

    @Override
    public void forEach(Consumer<? super Formal> action) {
        formals.forEach(action);
    }

    @Override
    public Spliterator<Formal> spliterator() {
        return formals.spliterator();
    }
}
