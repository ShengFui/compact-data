package net.sprd.bitcompact.schema;

import net.sprd.bitcompact.BitStorage;
import net.sprd.compact.ValueSize;

public class FloatArrayBitSchema extends ObjectBitSchema<float[], float[]>{
    
    protected AbstractDoubleBitSchema itemSchema;
    
    public FloatArrayBitSchema(AbstractDoubleBitSchema itemSchema) {
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
    protected int getObjectBitCount(float[] list) {
        int bitCount = 32;
        int len = list.length;
        bitCount+=itemSchema.bitCount * len;
        return bitCount;
    }

    @Override
    protected ValueSize<float[]> getObject(byte[] data, int offset) {
        int bitCount = 32;
        int len = BitStorage.getInt(data, offset,32);
        float[] list = new float[len];
        for (int i=0;i<len;i++) {
            double item = itemSchema.getDouble(data, offset+bitCount);
            list[i]=(float)item;
            bitCount +=itemSchema.bitCount;
        }
        return new ValueSize<float[]>(list,bitCount);
    }

    @Override
    protected int setObject(byte[] data, int offset, float[] list) {
        int bitCount = 32;
        BitStorage.setInt(data, offset,32, list.length);
        for (int i=0;i<list.length;i++) {
            bitCount +=itemSchema.setDouble(data, offset+bitCount, list[i]);
        }
        return bitCount;
    }

}
