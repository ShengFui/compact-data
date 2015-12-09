package net.sprd.bytecompact.schema;

import net.sprd.bytecompact.ByteStorage;
import net.sprd.compact.ValueSize;

public class StringSchema extends ObjectSchema<String, String> {

    public StringSchema() {
    }

    @Override
    protected int getObjectByteCount(byte[] data, int offset) {
        return ByteStorage.getStringByteCount(data, offset);
    }
    
    @Override
    protected int getObjectByteCount(String value) {
        return ByteStorage.getStringByteCount(value);
    }

    @Override
    protected ValueSize<String> getObject(byte[] data, int offset) {
        return ByteStorage.getString(data, offset);
    }

    @Override
    protected int setObject(byte[] data, int offset, String value) {
        return ByteStorage.setString(data, offset, value);
    }

}
