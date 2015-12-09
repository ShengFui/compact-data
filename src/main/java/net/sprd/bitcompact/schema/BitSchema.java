package net.sprd.bitcompact.schema;

public interface BitSchema<T> {
    
    public int getBitCount(byte[] data, int bitOffset);
    
    public int getBitCount(T t);
    

}
