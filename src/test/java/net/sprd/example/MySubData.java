package net.sprd.example;

import net.sprd.bytecompact.schema.ClassSchema;
import net.sprd.bytecompact.schema.Schema;
import net.sprd.bytecompact.schema.Schemas;
import net.sprd.compact.AbstractCompactData;

public class MySubData extends AbstractCompactData{
    
    static ClassSchema<MySubData> SCHEMA = new ClassSchema<MySubData>(new Schema[]{
            Schemas.NUMBERSTRING, 
            Schemas.DOUBLE}){

                @Override
                public MySubData create() {
                    return new MySubData();
                }
    };
    
    
    public MySubData() {
    }
    
    public String getId() {
        return SCHEMA.getValue(this, 0);
    }
    
    public double getWeight() {
        return SCHEMA.getDouble(this, 1);
    }
    
    
    
}
