package com.nvankempen.csc444.mjava.ast.utils;

public abstract class Type {
    public abstract String getName();

    @Override
    public boolean equals(Object obj) {

        if (!(obj instanceof Type)) {
            return false;
        }

        Type other = (Type) obj;

        if (isUnknown() || other.isUnknown()) {
            return true;
        }
        if(other.isInt()) {
            return this.isInt();
        }
        if (other.isBoolean()) {
            return this.isBoolean();
        }
        if (other.isIntArray()) {
            return this.isIntArray();
        }

        return this.getName().equals(other.getName());
    }

    @Override
    public int hashCode() {
        return getName().hashCode();
    }

    public boolean isInt() {
        return isUnknown() || this instanceof IntegerType;
    }

    public boolean isBoolean() {
        return isUnknown() || this instanceof BooleanType;
    }

    public boolean isIntArray() {
        return isUnknown() || this instanceof IntegerArrayType;
    }

    public boolean isUnknown() {
        return this instanceof UnknownType;
    }
}
