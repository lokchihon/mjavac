package com.nvankempen.csc444.mjava.ast.utils;

import com.nvankempen.csc444.mjava.ast.nodes.ClassDeclaration;
import com.nvankempen.csc444.mjava.ast.nodes.Identifier;
import java.util.Map;

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

    public String toDescriptor() {
        if (this.isInt()) return "I";
        if (this.isBoolean()) return "B";
        if (this.isIntArray()) return "[I";

        return "L" + getName() + ";";
    }

    /**
     * Checks if this type is equal or a subclass of the given type.
     * @param sup the potential superclass.
     * @param classes the map of identifiers to classes, used to retrieve superclasses.
     * @return 0 if the classes are equal, else 1 per level of difference, or -1 if there is no match.
     */
    public int conformsTo(Type sup, Map<Identifier, ClassDeclaration> classes) {
        if (isUnknown() || sup.isUnknown()) {
            return 0;
        }
        if(sup.isInt()) {
            return this.isInt() ? 0 : -1;
        }
        if (sup.isBoolean()) {
            return this.isBoolean() ? 0 : -1;
        }
        if (sup.isIntArray()) {
            return this.isIntArray() ? 0 : -1;
        }

        IdentifierType sub = (IdentifierType) this;
        for (int i = 0; sub.getName() != null; ++i) {
            if (sub.equals(sup)) {
                return i;
            }

            sub = new IdentifierType(classes.get(sub.getIdentifier()).getSuperclass());
        }

        return -1;
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
