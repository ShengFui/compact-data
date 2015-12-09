package net.sprd.compact.schema;

public interface ByteCache {
    
    public byte[] getCachedData(byte[] data);
        
    public int size();

}
