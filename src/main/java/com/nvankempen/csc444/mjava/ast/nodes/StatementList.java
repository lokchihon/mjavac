package com.nvankempen.csc444.mjava.ast.nodes;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Spliterator;
import java.util.function.Consumer;

public class StatementList implements Iterable<Statement> {
    private List<Statement> statements;

    public StatementList(List<Statement> statements) {
        this.statements = statements;
    }

    public StatementList() {
        this.statements = new ArrayList<>();
    }

    public boolean add(Statement statement) {
        return statements.add(statement);
    }

    @Override
    public Iterator<Statement> iterator() {
        return statements.iterator();
    }

    @Override
    public void forEach(Consumer<? super Statement> action) {
        statements.forEach(action);
    }

    @Override
    public Spliterator<Statement> spliterator() {
        return statements.spliterator();
    }
}
