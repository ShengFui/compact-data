package net.sprd.bitcompact.schema;

import net.sprd.bitcompact.BitStorage;
import net.sprd.compact.ValueSize;

public class DoubleArrayBitSchema extends ObjectBitSchema<double[], double[]>{
    
    protected AbstractDoubleBitSchema itemSchema;
    
    public DoubleArrayBitSchema(AbstractDoubleBitSchema itemSchema) {
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
    protected int getObjectBitCount(double[] list) {
        int bitCount = 32;
        int len = list.length;
        bitCount+=itemSchema.bitCount * len;
        return bitCount;
    }

    @Override
    protected ValueSize<double[]> getObject(byte[] data, int offset) {
        int bitCount = 32;
        int len = BitStorage.getInt(data, offset,32);
        double[] list = new double[len];
        for (int i=0;i<len;i++) {
            double item = itemSchema.getDouble(data, offset+bitCount);
            list[i]=item;
            bitCount +=itemSchema.bitCount;
        }
        return new ValueSize<double[]>(list,bitCount);
    }

    @Override
    protected int setObject(byte[] data, int offset, double[] list) {
        int bitCount = 32;
        BitStorage.setInt(data, offset,32, list.length);
        for (int i=0;i<list.length;i++) {
            bitCount +=itemSchema.setDouble(data, offset+bitCount, list[i]);
        }
        return bitCount;
    }

}
