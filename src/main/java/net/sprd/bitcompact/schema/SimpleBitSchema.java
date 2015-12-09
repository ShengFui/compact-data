package net.sprd.bitcompact.schema;

public class SimpleBitSchema<T> implements BitSchema<T>{
    
    protected int bitCount;

    public SimpleBitSchema(int bitCount) {
        this.bitCount=bitCount;
    }
    
    public int getBitCount(byte[] data, int offset) {
        return bitCount;
    }
    
    public int getBitCount(T t) {
        return bitCount;
    }
    

}
