package net.sprd.compact.schema;

import java.util.Arrays;

public class ByteArray {
    
    private byte[] data;

    public ByteArray(byte[] data) {
        this.data=data;
    }

    public byte[] getData() {
        return data;
    }
    
    public int hashCode() {
        int hash = 0x55 | 0x55<<8| 0x55<<16| 0x55<<24;
        for(int i =0;i<data.length;i++) {
            if (i+3<data.length) {
                hash = hash ^ (data[i]|data[i+1]<<8|data[i+2]<<16|data[i+3]<<24);
            } else {
                hash = hash ^ data[i];
                if (i+1<data.length) {
                    hash = hash ^ (data[i+1]<<8);
                    if (i+2<data.length) {
                        hash = hash ^ (data[i+2]<<16);
                    }
                }
            }
        }
        return hash;
    }
    
    public boolean equals(Object barray) {
        return Arrays.equals(data, ((ByteArray)barray).getData());
    }

}
