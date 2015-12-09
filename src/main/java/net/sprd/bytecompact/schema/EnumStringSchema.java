package net.sprd.bytecompact.schema;

import net.sprd.bytecompact.ByteStorage;
import net.sprd.compact.ValueSize;

public class EnumStringSchema extends FixedObjectSchema<String>{
    
    protected String[] values;
    
    public EnumStringSchema(String[] values) {
        super(1);
        this.values=values;
    }
    @Override
    public ValueSize<String> get(byte[] data, int offset) {
        byte index = ByteStorage.getByte(data, offset);
        if (index == -1) {
            return new ValueSize<String>(null,1);
        } else {
            return new ValueSize<String>(values[index & 255],1);
        }
    }
    
    protected byte getIndex(String value) {
        for(byte i =0;i<values.length;i++) {
            if (values[i].equals(value)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int set(byte[] data, int offset, String value) {
        byte index = getIndex(value);
        ByteStorage.setByte(data, offset, index);
        return 1;
    }


}
