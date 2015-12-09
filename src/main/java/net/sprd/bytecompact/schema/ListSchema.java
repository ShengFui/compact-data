package net.sprd.bytecompact.schema;

import java.util.ArrayList;
import java.util.List;

import net.sprd.bytecompact.ByteStorage;
import net.sprd.compact.ValueSize;

public class ListSchema<T, U> extends ObjectSchema<List<T>, List<U>>{
    
    protected ObjectSchema<T, U> itemSchema;
    
    public ListSchema(ObjectSchema<T, U> itemSchema) {
        this.itemSchema=itemSchema;
    }
    
    @Override
    protected int getObjectByteCount(byte[] data, int offset) {
        int byteCount = 4;
        int len = ByteStorage.getInt(data, offset);
        for (int i=0;i<len;i++) {
            byteCount +=itemSchema.getByteCount(data, offset+byteCount);
        }
        return byteCount;
    }
    
    @Override
    protected int getObjectByteCount(List<T> list) {
        int byteCount = 4;
        int len = list.size();
        for (int i=0;i<len;i++) {
            byteCount +=itemSchema.getByteCount(list.get(i));
        }
        return byteCount;
    }

    @Override
    protected ValueSize<List<U>> getObject(byte[] data, int offset) {
        int byteCount = 4;
        int len = ByteStorage.getInt(data, offset);
        List<U> list = new ArrayList<U>(len);
        for (int i=0;i<len;i++) {
            ValueSize<U> itemSize = itemSchema.get(data, offset+byteCount);
            list.add(itemSize.getValue());
            byteCount +=itemSize.getByteCount();
        }
        return new ValueSize<List<U>>(list,byteCount);
    }

    @Override
    protected int setObject(byte[] data, int offset, List<T> list) {
        int byteCount = 4;
        ByteStorage.setInt(data, offset, list.size());
        for (int i=0;i<list.size();i++) {
            byteCount +=itemSchema.set(data, offset+byteCount, list.get(i));
        }
        return byteCount;
    }

}
