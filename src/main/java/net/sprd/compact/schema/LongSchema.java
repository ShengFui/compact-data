package net.sprd.compact.schema;

public abstract class LongSchema extends SimpleSchema<Long>{
    
    public LongSchema(int byteCount) {
        super(byteCount);
    }

    public abstract long getLong(byte[] data, int offset);
    public abstract int setLong(byte[] data, int offset, long value);

}
