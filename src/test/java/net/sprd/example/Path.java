package net.sprd.example;

import java.util.Arrays;
import java.util.List;

import net.sprd.bitcompact.BitStorage;
import net.sprd.bitcompact.schema.BitSchema;
import net.sprd.bitcompact.schema.BitSchemas;
import net.sprd.bitcompact.schema.ClassBitSchema;
import net.sprd.compact.AbstractCompactData;

public class Path extends AbstractCompactData {
    
    public static ClassBitSchema<Path> SCHEMA = new ClassBitSchema<Path>(new BitSchema[]{
            BitSchemas.STRING, // pathname
            Vector.SCHEMA, //center
            BitSchemas.LIST(Vector.SCHEMA) //list of vectors
    }){

        @Override
        public Path create() {
            return new Path();
        }
    };
    
    public Path() {
        
    }
        
    public String getPathname() {
        return SCHEMA.getValue(this, 0);
    }
    
    public Vector getCenter() {
        return SCHEMA.getValue(this, 1);
    }
    
    public List<Vector> getPoints() {
        return SCHEMA.getValue(this, 2);
    }
    
    
    
    public String toString() {
        return "Path '"+getPathname()+"' (center"+getCenter()+", points:"+getPoints()+")";
    }
    
    public static void main(String[] args) {
        Path path =  Path.SCHEMA.createObject(new Object[]{"River",new Object[]{10.3,97.5,21.0},Arrays.asList(new Object[]{1.0,2.1,3.6},new Object[]{80.6,34.2,13.7})});
        System.out.println(path);
        System.out.println(path.getData().length+" bytes");
        BitStorage.print(path.getData());
    }

}
