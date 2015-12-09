package net.sprd.bitcompact;

import org.junit.Assert;
import org.junit.Test;

import net.sprd.compact.schema.ValueSize;

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
        System.out.println();
        
        testInt(-1,Integer.MAX_VALUE, 5, 32);
        testInt(-1,Integer.MIN_VALUE, 5, 32);
        testInt(-1,0, 5, 32);
        testInt(-1,1, 5, 32);
        testInt(-1,-1, 5, 32);
        System.out.println();
    }
    
    public void testInt(int firstByte, int testInt, int bitOffset, int bitLength) {
        byte[] data = new byte[10];
        data[0] = (byte)firstByte;
        BitStorage.setInt(data, bitOffset, bitLength, testInt);
        BitStorage.print(data);
        int val = BitStorage.getInt(data, bitOffset, bitLength);
        Assert.assertEquals(testInt, val);
    }
    
    @Test
    public void testLong() {
        
        testLong(0,Long.MAX_VALUE, 0, 64);
        testLong(0,Long.MIN_VALUE, 0, 64);
        testLong(0,0, 0, 64);
        testLong(0,1, 0, 64);
        testLong(0,-1, 0, 64);
        System.out.println();
        
        testLong(0,Long.MAX_VALUE, 5, 64);
        testLong(0,Long.MIN_VALUE, 5, 64);
        testLong(0,0, 5, 64);
        testLong(0,1, 5, 64);
        testLong(0,-1, 5, 64);
        System.out.println();
        
        testLong(-1,Long.MAX_VALUE, 5, 64);
        testLong(-1,Long.MIN_VALUE, 5, 64);
        testLong(-1,0, 5, 64);
        testLong(-1,1, 5, 64);
        testLong(-1,-1, 5, 64);
        System.out.println();
    }
    
    public void testLong(int firstByte, long testLong, int bitOffset, int bitLength) {
        byte[] data = new byte[10];
        data[0] = (byte)firstByte;
        BitStorage.setLong(data, bitOffset, bitLength, testLong);
        BitStorage.print(data);
        long val = BitStorage.getLong(data, bitOffset, bitLength);
        Assert.assertEquals(testLong, val);
    }
    
    @Test
    public void testNumberString() {
        testNumberString(-1, "12987", 0);
        testNumberString(-1, "12987", 4);
        testNumberString(-1, "12987", 5);
    }
    
    public void testNumberString(int firstByte, String testValue, int bitOffset) {
        byte[] data = new byte[10];
        data[0] = (byte)firstByte;
        BitStorage.setNumberString(data, bitOffset, testValue);
        BitStorage.print(data);
        ValueSize<String> value = BitStorage.getNumberString(data, bitOffset);
        Assert.assertEquals(testValue, value.getValue());
    }

}
