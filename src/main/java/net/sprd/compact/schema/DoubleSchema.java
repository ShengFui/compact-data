package net.sprd.compact.schema;

public abstract class DoubleSchema extends SimpleSchema<Double>{
    
    public DoubleSchema(int byteCount) {
        super(byteCount);
    }

    public abstract double getDouble(byte[] data, int offset);
    public abstract int setDouble(byte[] data, int offset, double value);

}
