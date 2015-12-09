package net.sprd.compact;

public class ValueSize<T> {
    
    private T value;
    private int size;
    
    public ValueSize(T value, int size) {
        super();
        this.value = value;
        this.size = size;
    }

    public T getValue() {
        return value;
    }

    public int getSize() {
        return size;
    }

}
