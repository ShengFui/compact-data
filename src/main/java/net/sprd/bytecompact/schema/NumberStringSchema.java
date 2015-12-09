package net.sprd.bytecompact.schema;

import net.sprd.bytecompact.ByteStorage;
import net.sprd.compact.ValueSize;

public class NumberStringSchema extends ObjectSchema<String, String> {

    public NumberStringSchema() {
    }

    @Override
    protected int getObjectByteCount(byte[] data, int offset) {
        return ByteStorage.getNumberStringByteCount(data, offset);
    }
    
    @Override
    protected int getObjectByteCount(String value) {
        return ByteStorage.getNumberStringByteCount(value);
    }

    @Override
    protected ValueSize<String> getObject(byte[] data, int offset) {
        return ByteStorage.getNumberString(data, offset);
    }

    @Override
    protected int setObject(byte[] data, int offset, String value) {
        return ByteStorage.setNumberString(data, offset, value);
    }

}
