package net.sprd.bitcompact.schema;

import net.sprd.bitcompact.BitStorage;
import net.sprd.compact.ValueSize;

public class EnumStringBitSchema extends FixedObjectBitSchema<String>{
    
    protected String[] values;
    
    public EnumStringBitSchema(String[] values) {
        super((int) Math.ceil(Math.log(values.length)/Math.log(2)));
        this.values=values;
    }
    @Override
    public ValueSize<String> get(byte[] data, int offset) {
        int index = BitStorage.getInt(data, offset, bitCount);
        if (index == -1) {
            return new ValueSize<String>(null,bitCount);
        } else {
            return new ValueSize<String>(values[index],bitCount);
        }
    }
    
    protected int getIndex(String value) {
        for(int i =0;i<values.length;i++) {
            if (values[i].equals(value)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int set(byte[] data, int offset, String value) {
        int index = getIndex(value);
        BitStorage.setInt(data, offset, bitCount, index);
        return bitCount;
    }


}
