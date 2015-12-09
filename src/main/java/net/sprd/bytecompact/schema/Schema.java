package net.sprd.bytecompact.schema;

public interface Schema<T> {
    
    public int getByteCount(byte[] data, int offset);
    
    public int getByteCount(T t);
    

}
