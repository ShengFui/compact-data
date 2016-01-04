package net.sprd.bitcompact.schema;

import net.sprd.bitcompact.BitStorage;
import net.sprd.compact.ValueSize;

public class IntArrayBitSchema extends ObjectBitSchema<int[], int[]>{
    
    protected IntBitSchema itemSchema;
    
    public IntArrayBitSchema(IntBitSchema itemSchema) {
        this.itemSchema=itemSchema;
    }
    
    public IntBitSchema getItemSchema() {
        return itemSchema;
    }

    public void setItemSchema(IntBitSchema itemSchema) {
        this.itemSchema = itemSchema;
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
    protected int getObjectBitCount(int[] list) {
        int bitCount = 32;
        int len = list.length;
        bitCount+=itemSchema.bitCount * len;
        return bitCount;
    }

    @Override
    protected ValueSize<int[]> getObject(byte[] data, int offset) {
        int bitCount = 32;
        int len = BitStorage.getInt(data, offset,32);
        int[] list = new int[len];
        for (int i=0;i<len;i++) {
            int item = itemSchema.getInt(data, offset+bitCount);
            list[i]=item;
            bitCount +=itemSchema.bitCount;
        }
        return new ValueSize<int[]>(list,bitCount);
    }

    @Override
    protected int setObject(byte[] data, int offset, int[] list) {
        int bitCount = 32;
        BitStorage.setInt(data, offset,32, list.length);
        for (int i=0;i<list.length;i++) {
            bitCount +=itemSchema.setInt(data, offset+bitCount, list[i]);
        }
        return bitCount;
    }

}
