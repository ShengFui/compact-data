package net.sprd.compact.schema;

import net.sprd.compact.ByteStorage;

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
