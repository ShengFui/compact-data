package net.sprd.bitcompact.schema;

import net.sprd.bitcompact.BitStorage;

public class FloatingBitSchema extends AbstractDoubleBitSchema{

    private double factor;
    private double minValue;
    /**
     * store unsigned integer for (value-minValue)*factor with bitCount bits
     * handle values between [minValue ; minValue + ((2^bitCount -1)/factor)], 
     * precision (minimal distance between 2 values) is 1/factor 
     * 
     * @param bitCount number of bits to store
     * @param factor factor to scale number to int
     * @param minValue minimum value
     */
    public FloatingBitSchema(int bitCount, double factor, double minValue) {
        super(bitCount);
        this.factor=factor;
        this.minValue=minValue;
    }
    
    public double getDouble(byte[] data, int offset) {
        return bitCount>32 ?
                (BitStorage.getLong(data, offset, bitCount)/factor)+minValue:
                    (BitStorage.getInt(data, offset, bitCount)/factor)+minValue;
    }
    public int setDouble(byte[] data, int offset, double value) {
        if (bitCount>32) {
            BitStorage.setLong(data, offset, bitCount, (long)(((value-minValue)*factor)+0.5));
        } else {
            BitStorage.setInt(data, offset, bitCount, (int)(((value-minValue)*factor)+0.5));
        }
        return this.bitCount;
    }


}
