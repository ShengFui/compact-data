package net.sprd.compact.schema;

import java.util.ArrayList;
import java.util.List;

import net.sprd.compact.ByteStorage;

public class Schemas {
    
    public static BooleanSchema BOOL = new BooleanSchema();
    
    public static ByteSchema BYTE = new ByteSchema();
    
    public static IntSchema INT = new IntSchema(4){
        @Override
        public int getInt(byte[] data, int offset) {
            return ByteStorage.getInt(data, offset);
        }
        @Override
        public int setInt(byte[] data, int offset, int value) {
            ByteStorage.setInt(data, offset, value);
            return this.byteCount;
        }
    };
    
    public static LongSchema LONG = new LongSchema(8){
        @Override
        public long getLong(byte[] data, int offset) {
            return ByteStorage.getLong(data, offset);
        }
        @Override
        public int setLong(byte[] data, int offset, long value) {
            ByteStorage.setLong(data, offset, value);
            return this.byteCount;
        }
    };
    
    public static DoubleSchema DOUBLE = new DoubleSchema(8){
        @Override
        public double getDouble(byte[] data, int offset) {
            return ByteStorage.getDouble(data, offset);
        }
        @Override
        public int setDouble(byte[] data, int offset, double value) {
            ByteStorage.setDouble(data, offset, value);
            return this.byteCount;
        }
    };
    
    public static NullSchema NULL = new NullSchema();
    
    public static<T> ConstantSchema<T> CONSTANT(T value) {
        return new ConstantSchema<T>(value);
    }
    
    public static StringSchema STRING = new StringSchema();
    public static NumberStringSchema NUMBERSTRING = new NumberStringSchema();
    
    public static EnumStringSchema ENUM(String[] values) {
        return new EnumStringSchema(values);
    }
    
    public static FixedObjectSchema<String> ASCII(int byteCount){
        return new FixedObjectSchema<String>(byteCount) {
            @Override
            public ValueSize<String> get(byte[] data, int offset) {
                return new ValueSize<String>(ByteStorage.getAsciiString(data, offset, this.byteCount), this.byteCount);
            }
            @Override
            public int set(byte[] data, int offset, String value) {
                ByteStorage.setAsciiString(data, offset, this.byteCount, value);
                return this.byteCount;
            }
        };
    }
    
    
    public static <T, U> ObjectSchema<List<T>, List<U>> LIST(final ObjectSchema<T, U> itemSchema){
        return new ListSchema<T,U>(itemSchema);
    }
    
    public static <T> List<Object> getListObjects(Creator<T> creator, List<? extends T> data) {
        List<Object> objects = new ArrayList<>(data.size());
        for (T t:data) {
            objects.add(creator.create(t));
        }
        return objects;
    }

}
