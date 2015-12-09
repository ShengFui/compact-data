package net.sprd.bitcompact.schema;

import net.sprd.bitcompact.BitStorage;

public class DoubleBitSchema extends AbstractDoubleBitSchema{

    public DoubleBitSchema() {
        super(8);
    }
    
    public double getDouble(byte[] data, int offset) {
        return Double.longBitsToDouble(BitStorage.getLong(data, offset, bitCount));
    }
    public int setDouble(byte[] data, int offset, double value) {
        BitStorage.setLong(data, offset, bitCount, Double.doubleToRawLongBits(value));
        return this.bitCount;
    }


}
