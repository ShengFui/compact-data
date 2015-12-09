package net.sprd.bytecompact.schema;

public abstract class FloatSchema extends SimpleSchema<Double>{
    
    public FloatSchema(int byteCount) {
        super(byteCount);
    }

    public abstract float getFloat(byte[] data, int offset);
    public abstract int setFloat(byte[] data, int offset, float value);

}
