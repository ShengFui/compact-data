package net.sprd.compact.schema;

public class NullSchema extends FixedObjectSchema<Object>{
    
    public NullSchema() {
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
