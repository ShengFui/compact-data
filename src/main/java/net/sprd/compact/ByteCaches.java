package net.sprd.compact;

public class ByteCaches {
    
    protected static ByteCache INSTANCE;
    
    public static ByteCache getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new SimpleByteCache();
        }
        return INSTANCE;
    }
    
    public static void setByteCache(ByteCache byteCache) {
        INSTANCE = byteCache;
    }
    
}
