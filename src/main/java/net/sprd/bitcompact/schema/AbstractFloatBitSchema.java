package net.sprd.bitcompact.schema;

public abstract class AbstractFloatBitSchema extends SimpleBitSchema<Double>{

    public AbstractFloatBitSchema(int bitCount) {
        super(bitCount);
    }
    
    public abstract float getFloat(byte[] data, int offset);
    public abstract int setFloat(byte[] data, int offset, float value);


}
