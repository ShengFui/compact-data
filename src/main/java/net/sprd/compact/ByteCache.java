package net.sprd.compact;

public interface ByteCache {
    
    public byte[] getCachedData(byte[] data);
        
    public int size();

}
