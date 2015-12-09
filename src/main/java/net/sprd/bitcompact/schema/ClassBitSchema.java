package net.sprd.bitcompact.schema;

import net.sprd.compact.ByteCaches;
import net.sprd.compact.CompactData;
import net.sprd.compact.ValueSize;

public abstract class ClassBitSchema<C extends CompactData> extends ObjectBitSchema<Object[], C>{
    
    protected final BitSchema[] types;
    
    public ClassBitSchema(BitSchema[] types) {
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
                offset += types[i].getBitCount(data, offset);
                fieldOffsets[i+1] = offset;
            }
            compactData.setFieldOffsets(fieldOffsets);
        }
        return fieldOffsets[index];
    }
    
    public boolean getBoolean(C compactData, int index) {
        int offset = getOffset(compactData, index);
        return ((BooleanBitSchema)types[index]).getBoolean(compactData.getData(), offset);
    }
    
    public void setBoolean(C compactData, int index, boolean value) {
        int offset = getOffset(compactData, index);
        ((BooleanBitSchema)types[index]).setBoolean(compactData.getData(), offset, value);
    }
    
    public int getInt(C compactData, int index) {
        int offset = getOffset(compactData, index);
        return ((IntBitSchema)types[index]).getInt(compactData.getData(), offset);
    }
    
    public void setInt(C compactData, int index, int value) {
        int offset = getOffset(compactData, index);
        ((IntBitSchema)types[index]).setInt(compactData.getData(), offset, value);
    }
    
    public long getLong(C compactData, int index) {
        int offset = getOffset(compactData, index);
        return ((LongBitSchema)types[index]).getLong(compactData.getData(), offset);
    }
    
    public void setLong(C compactData, int index, long value) {
        int offset = getOffset(compactData, index);
        ((LongBitSchema)types[index]).setLong(compactData.getData(), offset, value);
    }
    
    public double getDouble(C compactData, int index) {
        int offset = getOffset(compactData, index);
        return ((DoubleBitSchema)types[index]).getDouble(compactData.getData(), offset);
    }
    
    public void setDouble(C compactData, int index, double value) {
        int offset = getOffset(compactData, index);
        ((DoubleBitSchema)types[index]).setDouble(compactData.getData(), offset, value);
    }
    
    public <T> T getValue(C compactData, int index) {
        int offset = getOffset(compactData, index);
        if (types[index] instanceof ObjectBitSchema) {
            return ((ObjectBitSchema<?,T>)types[index]).get(compactData.getData(), offset).getValue();
        } else if (types[index] instanceof FixedObjectBitSchema) {
            return ((FixedObjectBitSchema<T>)types[index]).get(compactData.getData(), offset).getValue();
        } else {
            System.out.println("not valid schema "+types[index]);
            return null;
        }
    }
    
    public <T> void setValue(C compactData, int index, T value) {
        int offset = getOffset(compactData, index);
        if (types[index] instanceof ObjectBitSchema) {
            ((ObjectBitSchema<T,?>)types[index]).set(compactData.getData(), offset, value);
        } else if (types[index] instanceof FixedObjectBitSchema) {
            ((FixedObjectBitSchema<T>)types[index]).set(compactData.getData(), offset, value);
        } else {
            System.out.println("not valid schema "+types[index]);
        }
    }

    protected int getObjectBitCount(Object[] objects) {
        int byteCount = 0;
        if (objects != null) {
            for (int i = 0; i < objects.length; i++) {
                BitSchema type = types[i];
                int c = type.getBitCount(objects[i]);
                byteCount += c;
            }
        }
        return byteCount;
    }

    public int setData(byte[] data, int offset, Object[] objects) {
        int byteCount = offset;
        if (objects != null) {
            for (int i = 0; i < objects.length; i++) {
                BitSchema type = types[i];
                if (type instanceof ObjectBitSchema) {
                    byteCount += ((ObjectBitSchema)type).set(data, byteCount, objects[i]);
                } else if (type instanceof FixedObjectBitSchema) {
                    byteCount += ((FixedObjectBitSchema)type).set(data, byteCount, objects[i]);
                } else if (type instanceof BooleanBitSchema) {
                    byteCount += ((BooleanBitSchema)type).setBoolean(data, byteCount, ((Boolean)objects[i]).booleanValue());
                } else if (type instanceof IntBitSchema) {
                    byteCount += ((IntBitSchema)type).setInt(data, byteCount, ((Number)objects[i]).intValue());
                } else if (type instanceof AbstractDoubleBitSchema) {
                    byteCount += ((AbstractDoubleBitSchema)type).setDouble(data, byteCount, ((Number)objects[i]).doubleValue());
                } else if (type instanceof LongBitSchema) {
                    byteCount += ((LongBitSchema)type).setLong(data, byteCount, ((Number)objects[i]).longValue());
                } else {
                    System.out.println("not supported schemaType: "+type);
                }
            }
        }
        return byteCount-offset;
    }

    public byte[] createData(Object[] objects) {
        byte[] data=new byte[getBitCount(objects)];
        setData(data, 0, objects);
        return ByteCaches.getInstance().getCachedData(data);
    }
    
    public C createObject(Object[] objects){
        C c = create();
        c.init(createData(objects), 0);
        return c;
    }

    protected int getObjectBitCount(byte[] data, int offset) {
        int byteCount = 0;
        for (int i = 0; i < types.length; i++) {
            BitSchema type = types[i];
            int c = type.getBitCount(data, offset+byteCount);
            byteCount += c;
        }
        return byteCount;
    }

    @Override
    protected ValueSize<C> getObject(byte[] data, int offset) {
        C t = create();
        t.init(data, offset);
        int byteCount = getBitCount(data, offset);
        return new ValueSize<C>(t, byteCount);
    }

    @Override
    protected int setObject(byte[] data, int offset, Object[] objects) {
        return setData(data, offset, objects);
    }


}
