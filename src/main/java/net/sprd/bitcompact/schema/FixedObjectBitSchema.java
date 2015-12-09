package net.sprd.bitcompact.schema;

import net.sprd.compact.schema.ValueSize;

public abstract class FixedObjectBitSchema<T> extends SimpleBitSchema<T> implements BitSchema<T>{
    
    public FixedObjectBitSchema(int byteCount) {
        super(byteCount);
    }
    
    public abstract ValueSize<T> get(byte[] data, int offset);
    
    public abstract int set(byte[] data, int offset, T value);

}
