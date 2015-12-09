package net.sprd.compact.schema;

import java.util.HashMap;

public class SimpleByteCache implements ByteCache{
    
    protected HashMap<ByteArray,byte[]> byteArrays = new HashMap<>();
    
    public byte[] getCachedData(byte[] data) {
        ByteArray ba = new ByteArray(data);
        byte[] internal = byteArrays.get(ba);
        if (internal != null) {
            return internal;
        } else {
            byteArrays.put(ba, data);
            return data;
        }
    }
    
    public int size() {
        return byteArrays.size();
    }

}
