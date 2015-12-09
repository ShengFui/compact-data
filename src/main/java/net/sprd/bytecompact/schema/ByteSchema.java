package net.sprd.bytecompact.schema;

import net.sprd.bytecompact.ByteStorage;

public class ByteSchema extends SimpleSchema<Integer>{
    
    public ByteSchema() {
        super(1);
    }
    public byte getByte(byte[] data, int offset) {
        return ByteStorage.getByte(data, offset);
    }
    public int setByte(byte[] data, int offset, byte value) {
        ByteStorage.setByte(data, offset, value);
        return 1;
    }

}
