package net.sprd.bitcompact.schema;

public abstract class AbstractFloatBitSchema extends AbstractDoubleBitSchema{

    public AbstractFloatBitSchema(int bitCount) {
        super(bitCount);
    }
    
    public abstract float getFloat(byte[] data, int offset);
    public abstract int setFloat(byte[] data, int offset, float value);
    
    public double getDouble(byte[] data, int offset){
        return getFloat(data, offset);
    }
    public int setDouble(byte[] data, int offset, double value) {
        return setFloat(data, offset, (float)value);
    }


}
