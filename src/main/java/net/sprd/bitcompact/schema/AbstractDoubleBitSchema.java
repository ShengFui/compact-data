package net.sprd.bitcompact.schema;

public abstract class AbstractDoubleBitSchema extends SimpleBitSchema<Double>{

    public AbstractDoubleBitSchema(int bitCount) {
        super(bitCount);
    }
    
    public abstract double getDouble(byte[] data, int offset);
    public abstract int setDouble(byte[] data, int offset, double value);


}
