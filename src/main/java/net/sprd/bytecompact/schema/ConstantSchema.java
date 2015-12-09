package net.sprd.bytecompact.schema;

import net.sprd.compact.ValueSize;

public class ConstantSchema<T> extends FixedObjectSchema<T> {

    protected T value;
    
    public ConstantSchema(T value) {
        super(0);
        this.value = value;
    }

    @Override
    public ValueSize<T> get(byte[] data, int offset) {
        return new ValueSize<T>(value,0);
    }

    @Override
    public int set(byte[] data, int offset, T value) {
        return 0;
    }

}
