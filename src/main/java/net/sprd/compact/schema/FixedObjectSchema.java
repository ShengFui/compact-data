package net.sprd.compact.schema;

public abstract class FixedObjectSchema<T> extends SimpleSchema<T> implements Schema<T>{
    
    public FixedObjectSchema(int byteCount) {
        super(byteCount);
    }
    
    public abstract ValueSize<T> get(byte[] data, int offset);
    
    public abstract int set(byte[] data, int offset, T value);

}
