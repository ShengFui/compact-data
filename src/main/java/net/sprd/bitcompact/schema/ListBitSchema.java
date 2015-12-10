package net.sprd.bitcompact.schema;

import java.util.ArrayList;
import java.util.List;

import net.sprd.bitcompact.BitStorage;
import net.sprd.compact.ValueSize;

public class ListBitSchema<T, U> extends ObjectBitSchema<List<T>, List<U>>{
    
    protected ObjectBitSchema<T, U> itemSchema;
    
    public ListBitSchema(ObjectBitSchema<T, U> itemSchema) {
        this.itemSchema=itemSchema;
    }
    
    @Override
    protected int getObjectBitCount(byte[] data, int offset) {
        int bitCount = 32;
        int len = BitStorage.getInt(data, offset,32);
        for (int i=0;i<len;i++) {
            bitCount +=itemSchema.getBitCount(data, offset+bitCount);
        }
        return bitCount;
    }
    
    @Override
    protected int getObjectBitCount(List<T> list) {
        int bitCount = 32;
        int len = list.size();
        for (int i=0;i<len;i++) {
            bitCount +=itemSchema.getBitCount(list.get(i));
        }
        return bitCount;
    }

    @Override
    protected ValueSize<List<U>> getObject(byte[] data, int offset) {
        int bitCount = 32;
        int len = BitStorage.getInt(data, offset,32);
        List<U> list = new ArrayList<U>(len);
        for (int i=0;i<len;i++) {
            ValueSize<U> itemSize = itemSchema.get(data, offset+bitCount);
            list.add(itemSize.getValue());
            bitCount +=itemSize.getSize();
        }
        return new ValueSize<List<U>>(list,bitCount);
    }

    @Override
    protected int setObject(byte[] data, int offset, List<T> list) {
        int bitCount = 32;
        BitStorage.setInt(data, offset,32, list.size());
        for (int i=0;i<list.size();i++) {
            bitCount +=itemSchema.set(data, offset+bitCount, list.get(i));
        }
        return bitCount;
    }

}
