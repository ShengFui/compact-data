package net.sprd.compact.schema;

public abstract class Creator<T> {
    public abstract Object create(T value);
}
