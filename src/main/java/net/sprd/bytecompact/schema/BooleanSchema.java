package net.sprd.bytecompact.schema;

import net.sprd.bytecompact.ByteStorage;

public class BooleanSchema extends SimpleSchema<Integer>{
    
    public BooleanSchema() {
        super(1);
    }

    public boolean getBoolean(byte[] data, int offset) {
        return ByteStorage.getBoolean(data, offset);
    }
    public int setBoolean(byte[] data, int offset, boolean value) {
        ByteStorage.setBoolean(data, offset, value);
        return 1;
    }
    

}
