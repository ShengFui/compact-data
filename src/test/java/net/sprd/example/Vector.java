package net.sprd.example;

import net.sprd.bitcompact.schema.BitSchema;
import net.sprd.bitcompact.schema.BitSchemas;
import net.sprd.bitcompact.schema.ClassBitSchema;
import net.sprd.compact.AbstractCompactData;

public class Vector extends AbstractCompactData {
    
    public static ClassBitSchema<Vector> SCHEMA = new ClassBitSchema<Vector>(new BitSchema[]{
            BitSchemas.FLOATING(10, 10.0), //x
            BitSchemas.FLOATING(10, 10.0), //y
            BitSchemas.FLOATING(10, 10.0) //z
    }){

        @Override
        public Vector create() {
            return new Vector();
        }
    };
    
    public Vector() {
        
    }
        
    
    public double getX() {
        return SCHEMA.getDouble(this, 0);
    }
    
    public double getY() {
        return SCHEMA.getDouble(this, 1);
    }
    
    public double getZ() {
        return SCHEMA.getDouble(this, 2);
    }
    
    public String toString() {
        return "("+getX()+","+getY()+","+getZ()+")";
    }
    
    public static void main(String[] args) {
        Vector vector =  Vector.SCHEMA.createObject(new Object[]{10.3,97.5,21.0});
        System.out.println(vector);
        System.out.println(vector.getData().length+" bytes");
    }

}
