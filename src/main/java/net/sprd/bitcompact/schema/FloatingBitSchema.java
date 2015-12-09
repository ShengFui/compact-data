package net.sprd.bitcompact.schema;

import net.sprd.bitcompact.BitStorage;

public abstract class FloatingBitSchema extends AbstractDoubleBitSchema{

    private double factor;
    public FloatingBitSchema(int bitCount, double factor) {
        super(bitCount);
        this.factor=factor;
    }
    
    public double getDouble(byte[] data, int offset) {
        return bitCount>32 ?
                BitStorage.getLong(data, offset, bitCount)/factor:
                    BitStorage.getInt(data, offset, bitCount)/factor;
    }
    public int setDouble(byte[] data, int offset, double value) {
        if (bitCount>32) {
            BitStorage.setLong(data, offset, bitCount, (long)(value*factor));
        } else {
            BitStorage.setInt(data, offset, bitCount, (int)(value*factor));
        }
        return this.bitCount;
    }


}
