package net.sprd.compact;

import net.sprd.compact.schema.ClassSchema;
import net.sprd.compact.schema.Schema;
import net.sprd.compact.schema.Schemas;

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
