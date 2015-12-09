package net.sprd.bitcompact.schema;

import net.sprd.bitcompact.BitStorage;

public abstract class BooleanBitSchema extends SimpleBitSchema<Boolean>{
    
    public BooleanBitSchema() {
        super(1);
    }
    
    public boolean getBool(byte[] data, int offset) {
        return BitStorage.getBoolean(data, offset);
    }
    public int setBool(byte[] data, int offset, boolean value) {
        BitStorage.setBoolean(data, offset, value);
        return 1;
    }


}
