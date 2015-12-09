package net.sprd.compact;

public abstract class Creator<T> {
    public abstract Object create(T value);
}
