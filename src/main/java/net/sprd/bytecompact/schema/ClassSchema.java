package net.sprd.bytecompact.schema;

import java.util.HashMap;
import java.util.List;

import net.sprd.compact.ByteCaches;
import net.sprd.compact.CompactData;
import net.sprd.compact.ValueSize;

public abstract class ClassSchema<C extends CompactData> extends ObjectSchema<Object[], C>{
    
    protected final Schema[] types;
    
    public ClassSchema(Schema[] types) {
        this.types= types;
    }
    
    public abstract C create();
    
    public int getOffset(C compactData, int index) {
        int[] fieldOffsets = compactData.getFieldOffsets();
        if (fieldOffsets == null) {
            byte[] data = compactData.getData();
            int offset = compactData.getOffset();
            fieldOffsets=new int[types.length+1];
            fieldOffsets[0] = offset;
            for (int i=0;i<types.length; i++) {
                offset += types[i].getByteCount(data, offset);
                fieldOffsets[i+1] = offset;
            }
            compactData.setFieldOffsets(fieldOffsets);
        }
        return fieldOffsets[index];
    }
    
    public boolean getBoolean(C compactData, int index) {
        int offset = getOffset(compactData, index);
        return ((BooleanSchema)types[index]).getBoolean(compactData.getData(), offset);
    }
    
    public void setBoolean(C compactData, int index, boolean value) {
        int offset = getOffset(compactData, index);
        ((BooleanSchema)types[index]).setBoolean(compactData.getData(), offset, value);
    }
    
    public int getInt(C compactData, int index) {
        int offset = getOffset(compactData, index);
        return ((IntSchema)types[index]).getInt(compactData.getData(), offset);
    }
    
    public void setInt(C compactData, int index, int value) {
        int offset = getOffset(compactData, index);
        ((IntSchema)types[index]).setInt(compactData.getData(), offset, value);
    }
    
    public long getLong(C compactData, int index) {
        int offset = getOffset(compactData, index);
        return ((LongSchema)types[index]).getLong(compactData.getData(), offset);
    }
    
    public void setLong(C compactData, int index, long value) {
        int offset = getOffset(compactData, index);
        ((LongSchema)types[index]).setLong(compactData.getData(), offset, value);
    }
    
    public double getDouble(C compactData, int index) {
        int offset = getOffset(compactData, index);
        return ((DoubleSchema)types[index]).getDouble(compactData.getData(), offset);
    }
    
    public void setDouble(C compactData, int index, double value) {
        int offset = getOffset(compactData, index);
        ((DoubleSchema)types[index]).setDouble(compactData.getData(), offset, value);
    }
    
    public <T> T getValue(C compactData, int index) {
        int offset = getOffset(compactData, index);
        if (types[index] instanceof ObjectSchema) {
            return ((ObjectSchema<?,T>)types[index]).get(compactData.getData(), offset).getValue();
        } else if (types[index] instanceof FixedObjectSchema) {
            return ((FixedObjectSchema<T>)types[index]).get(compactData.getData(), offset).getValue();
        } else {
            System.out.println("not valid schema "+types[index]);
            return null;
        }
    }
    
    public <T> void setValue(C compactData, int index, T value) {
        int offset = getOffset(compactData, index);
        if (types[index] instanceof ObjectSchema) {
            ((ObjectSchema<T,?>)types[index]).set(compactData.getData(), offset, value);
        } else if (types[index] instanceof FixedObjectSchema) {
            ((FixedObjectSchema<T>)types[index]).set(compactData.getData(), offset, value);
        } else {
            System.out.println("not valid schema "+types[index]);
        }
    }

    protected int getObjectByteCount(Object[] objects) {
        int byteCount = 0;
        if (objects != null) {
            for (int i = 0; i < objects.length; i++) {
                Schema type = types[i];
                int c = type.getByteCount(objects[i]);
                byteCount += c;
            }
        }
        return byteCount;
    }

    public int setData(byte[] data, int offset, Object[] objects) {
        int byteCount = offset;
        if (objects != null) {
            for (int i = 0; i < objects.length; i++) {
                Schema type = types[i];
                if (type instanceof ObjectSchema) {
                    byteCount += ((ObjectSchema)type).set(data, byteCount, objects[i]);
                } else if (type instanceof FixedObjectSchema) {
                    byteCount += ((FixedObjectSchema)type).set(data, byteCount, objects[i]);
                } else if (type instanceof BooleanSchema) {
                    byteCount += ((BooleanSchema)type).setBoolean(data, byteCount, ((Boolean)objects[i]).booleanValue());
                } else if (type instanceof IntSchema) {
                    byteCount += ((IntSchema)type).setInt(data, byteCount, ((Number)objects[i]).intValue());
                } else if (type instanceof DoubleSchema) {
                    byteCount += ((DoubleSchema)type).setDouble(data, byteCount, ((Number)objects[i]).doubleValue());
                } else if (type instanceof LongSchema) {
                    byteCount += ((LongSchema)type).setLong(data, byteCount, ((Number)objects[i]).longValue());
                } else {
                    System.out.println("not supported schemaType: "+type);
                }
            }
        }
        return byteCount-offset;
    }

    public byte[] createData(Object[] objects) {
        byte[] data=new byte[getByteCount(objects)];
        setData(data, 0, objects);
        return ByteCaches.getInstance().getCachedData(data);
    }
    
    public C createObject(Object[] objects){
        C c = create();
        c.init(createData(objects), 0);
        return c;
    }

    protected int getObjectByteCount(byte[] data, int offset) {
        int byteCount = 0;
        for (int i = 0; i < types.length; i++) {
            Schema type = types[i];
            int c = type.getByteCount(data, offset+byteCount);
            byteCount += c;
        }
        return byteCount;
    }

    @Override
    protected ValueSize<C> getObject(byte[] data, int offset) {
        C t = create();
        t.init(data, offset);
        int byteCount = getByteCount(data, offset);
        return new ValueSize<C>(t, byteCount);
    }

    @Override
    protected int setObject(byte[] data, int offset, Object[] objects) {
        return setData(data, offset, objects);
    }


}
