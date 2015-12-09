package net.sprd.bitcompact.schema;

import net.sprd.compact.schema.ValueSize;

public class NullBitSchema extends FixedObjectBitSchema<Object>{
    
    public NullBitSchema() {
        super(0);
    }
    @Override
    public ValueSize<Object> get(byte[] data, int offset) {
        return new ValueSize<Object>(null,0);
    }
    
    @Override
    public int set(byte[] data, int offset, Object value) {
        return 0;
    }


}
