package net.sprd.bitcompact.schema;

import net.sprd.bitcompact.BitStorage;
import net.sprd.compact.schema.ValueSize;

public abstract class ObjectBitSchema<T, U> implements BitSchema<T>{
    
    @Override
    public final int getBitCount(byte[] data, int offset) {
        boolean notNull= BitStorage.getBoolean(data, offset);
            if (notNull){
               return getObjectByteCount(data, offset+1)+1;
            } else {
                return 1;
        }
    }
    
    @Override
    public final int getBitCount(T value) {
        if (value == null) {
            return 1;
        } else {
            return getObjectByteCount(value)+1;
        }
    }
    
    public final ValueSize<U> get(byte[] data, int offset) {
        boolean notNull= BitStorage.getBoolean(data, offset);
        if (notNull) {
            ValueSize<U> valueSize = getObject(data, offset+1);
            return new ValueSize<U>(valueSize.getValue(), valueSize.getByteCount()+1);
        } else {
            return new ValueSize<U>(null, 1);
        }
    }
    
    public final int set(byte[] data, int offset, T value) {
        if (value == null) {
            BitStorage.setBoolean(data, offset, false);
            return 1;
        } else {
            BitStorage.setBoolean(data, offset, true);
            return setObject(data, offset+1, value) + 1;
        }
    }
    
    protected abstract int getObjectByteCount(byte[] data, int offset);
    
    protected abstract int getObjectByteCount(T t);
    
    protected abstract ValueSize<U> getObject(byte[] data, int offset);
    
    protected abstract int setObject(byte[] data, int offset, T value);
    

}
