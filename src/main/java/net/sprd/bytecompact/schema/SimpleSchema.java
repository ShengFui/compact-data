package net.sprd.bytecompact.schema;

public class SimpleSchema<T> implements Schema<T>{
    
    protected int byteCount;

    public SimpleSchema(int byteCount) {
        this.byteCount=byteCount;
    }
    
    public int getByteCount(byte[] data, int offset) {
        return byteCount;
    }
    
    public int getByteCount(T t) {
        return byteCount;
    }
    

}
