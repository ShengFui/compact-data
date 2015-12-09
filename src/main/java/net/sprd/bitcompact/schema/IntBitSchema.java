package net.sprd.bitcompact.schema;

import net.sprd.bitcompact.BitStorage;

public class IntBitSchema extends SimpleBitSchema<Integer>{
    
    private int absolutOffset;

    public IntBitSchema(int bitCount, int absolutOffset) {
        super(bitCount);
        this.absolutOffset=absolutOffset;
    }
    
    public int getInt(byte[] data, int offset) {
        return BitStorage.getInt(data, offset, bitCount)+absolutOffset;
    }
    public int setInt(byte[] data, int offset, int value) {
        BitStorage.setInt(data, offset, bitCount, value-absolutOffset);
        return this.bitCount;
    }


}
