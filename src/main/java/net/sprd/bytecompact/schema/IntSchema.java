package net.sprd.bytecompact.schema;

public abstract class IntSchema extends SimpleSchema<Integer>{
    
    public IntSchema(int byteCount) {
        super(byteCount);
    }

    public abstract int getInt(byte[] data, int offset);
    
    public abstract int setInt(byte[] data, int offset, int value);
    

}
