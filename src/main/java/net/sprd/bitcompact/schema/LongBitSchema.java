package net.sprd.bitcompact.schema;

import net.sprd.bitcompact.BitStorage;

public class LongBitSchema extends SimpleBitSchema<Long>{
    
    private long absolutOffset;

    public LongBitSchema(int bitCount, long absolutOffset) {
        super(bitCount);
        this.absolutOffset=absolutOffset;
    }
    
    public long getLong(byte[] data, int offset) {
        return BitStorage.getLong(data, offset, bitCount)+absolutOffset;
    }
    public int setLong(byte[] data, int offset, long value) {
        BitStorage.setLong(data, offset, bitCount, value-absolutOffset);
        return this.bitCount;
    }


}
