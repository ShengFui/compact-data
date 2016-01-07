package net.sprd.bitcompact.schema;

import java.util.ArrayList;
import java.util.List;

import net.sprd.compact.Creator;

public class BitSchemas {

    public static BooleanBitSchema BOOL = new BooleanBitSchema();

    public static IntBitSchema BYTE = new IntBitSchema(8, 0);

    public static IntBitSchema INT(int bitCount) {
        return new IntBitSchema(bitCount, 0);
    }

    public static IntBitSchema INT_OFFSET(int bitCount, int absOffset) {
        return new IntBitSchema(bitCount, absOffset);
    }

    public static LongBitSchema LONG(int bitCount) {
        return new LongBitSchema(bitCount, 0);
    }

    public static LongBitSchema LONG_OFFSET(int bitCount, int absOffset) {
        return new LongBitSchema(bitCount, absOffset);
    }

    public static DoubleBitSchema DOUBLE = new DoubleBitSchema();

    public static FloatBitSchema FLOAT = new FloatBitSchema();

    public static FloatingBitSchema FLOATING(int bitCount, double factor, double minValue) {
        return new FloatingBitSchema(bitCount, factor, minValue);
    }

    public static NullBitSchema NULL = new NullBitSchema();

    public static <T> ConstantBitSchema<T> CONSTANT(T value) {
        return new ConstantBitSchema<T>(value);
    }

    public static StringBitSchema STRING = new StringBitSchema();
    public static NumberStringBitSchema NUMBERSTRING = new NumberStringBitSchema();

    public static EnumStringBitSchema ENUM(String[] values) {
        return new EnumStringBitSchema(values);
    }

    public static <T, U> ObjectBitSchema<List<T>, List<U>> LIST(final ObjectBitSchema<T, U> itemSchema) {
        return new ListBitSchema<T, U>(itemSchema);
    }

    public static <T> List<Object> getListObjects(Creator<T> creator, List<? extends T> data) {
        List<Object> objects = new ArrayList<>(data.size());
        for (T t : data) {
            objects.add(creator.create(t));
        }
        return objects;
    }

}
