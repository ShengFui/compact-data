package net.sprd.bitcompact;

import org.junit.Assert;
import org.junit.Test;

public class BitStorageTest {
    
    @Test
    public void testInt() {
        
        testInt(0,Integer.MAX_VALUE, 0, 32);
        testInt(0,Integer.MIN_VALUE, 0, 32);
        testInt(0,0, 0, 32);
        testInt(0,1, 0, 32);
        testInt(0,-1, 0, 32);
        System.out.println();
        
        testInt(0,Integer.MAX_VALUE, 5, 32);
        testInt(0,Integer.MIN_VALUE, 5, 32);
        testInt(0,0, 5, 32);
        testInt(0,1, 5, 32);
        testInt(0,-1, 5, 32);
        
        testInt(-1,Integer.MAX_VALUE, 5, 32);
        testInt(-1,Integer.MIN_VALUE, 5, 32);
        testInt(-1,0, 5, 32);
        testInt(-1,1, 5, 32);
        testInt(-1,-1, 5, 32);
    }
    
    public void testInt(int firstByte, int testInt, int bitOffset, int bitLength) {
        byte[] data = new byte[10];
        data[0] = (byte)firstByte;
        BitStorage.setInt(data, bitOffset, bitLength, testInt);
        BitStorage.print(data);
        int val = BitStorage.getInt(data, bitOffset, bitLength);
        Assert.assertEquals(testInt, val);
    }

}
