package net.sprd.bitcompact.schema;

import net.sprd.bitcompact.BitStorage;
import net.sprd.compact.ValueSize;

public class NumberStringBitSchema extends ObjectBitSchema<String, String> {

    public NumberStringBitSchema() {
    }

    @Override
    protected int getObjectBitCount(byte[] data, int offset) {
        return BitStorage.getNumberString(data, offset).getSize();
    }
    
    @Override
    protected int getObjectBitCount(String value) {
        return (value.length()+1)*4;
    }

    @Override
    protected ValueSize<String> getObject(byte[] data, int offset) {
        return BitStorage.getNumberString(data, offset);
    }

    @Override
    protected int setObject(byte[] data, int offset, String value) {
        return BitStorage.setNumberString(data, offset, value);
    }

}
