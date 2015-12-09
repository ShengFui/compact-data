package net.sprd.bitcompact.schema;

import net.sprd.bitcompact.BitStorage;

public class FloatBitSchema extends AbstractFloatBitSchema{

    public FloatBitSchema() {
        super(32);
    }
    
    public float getFloat(byte[] data, int offset) {
        return Float.intBitsToFloat(BitStorage.getInt(data, offset, bitCount));
    }
    public int setFloat(byte[] data, int offset, float value) {
        BitStorage.setInt(data, offset, bitCount, Float.floatToIntBits(value));
        return this.bitCount;
    }


}
