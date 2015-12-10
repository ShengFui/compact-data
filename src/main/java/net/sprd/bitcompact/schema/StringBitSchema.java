package net.sprd.bitcompact.schema;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;

import net.sprd.bitcompact.BitStorage;
import net.sprd.compact.ValueSize;

public class StringBitSchema extends ObjectBitSchema<String, String> {

    public StringBitSchema() {
    }

    @Override
    protected int getObjectBitCount(byte[] data, int offset) {
        int bitCount =0;
        int val = 0;
        do {
            val = BitStorage.getInt(data, offset+bitCount, 8);
            bitCount+=8;
        } while (val!=0);
        return bitCount;
    }
    
    @Override
    protected int getObjectBitCount(String value) {
        if (value == null || value.isEmpty()) {
            return 8;
        }
        return 8* (value.getBytes(StandardCharsets.UTF_8).length+1);
    }

    @Override
    protected ValueSize<String> getObject(byte[] data, int offset) {
        byte val = 0;
        int byteCount = getObjectBitCount(data, offset)/8;
        byte[] stringBytes = new byte[byteCount-1];
        for (int i=0;i<(byteCount-1);i++){
            val = (byte)(BitStorage.getInt(data, offset+i*8, 8) & 255);
            stringBytes[i] = val;
        }
        return new ValueSize<String>(new String(stringBytes, StandardCharsets.UTF_8), byteCount);
    }

    @Override
    protected int setObject(byte[] data, int offset, String value) {
        byte[] b = value.getBytes(StandardCharsets.UTF_8);
        int bitOffset=offset;
        for (int i = 0;i<b.length;i++) {
           BitStorage.setInt(data, bitOffset, 8, b[i]);
           bitOffset+=8;
        }
        data[offset+b.length] = 0;
        bitOffset+=8;
        return bitOffset-offset;
    }

}
