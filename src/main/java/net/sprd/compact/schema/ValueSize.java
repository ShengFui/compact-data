package net.sprd.compact.schema;

public class ValueSize<T> {
    
    private T value;
    private int byteCount;
    
    public ValueSize(T value, int byteCount) {
        super();
        this.value = value;
        this.byteCount = byteCount;
    }

    public T getValue() {
        return value;
    }

    public int getByteCount() {
        return byteCount;
    }

}
