package net.sprd.compact;

import java.util.Arrays;
import java.util.List;

import net.sprd.compact.schema.ByteCaches;
import net.sprd.compact.schema.ClassSchema;
import net.sprd.compact.schema.Schema;
import net.sprd.compact.schema.Schemas;

public class MyCompactData extends AbstractCompactData{
    
    public static ClassSchema<MyCompactData> SCHEMA = new ClassSchema<MyCompactData>(new Schema[]{
            Schemas.INT, 
            Schemas.ASCII(2),
            Schemas.LONG, 
            Schemas.DOUBLE, 
            Schemas.STRING, 
            MySubData.SCHEMA,
            Schemas.LIST(Schemas.NUMBERSTRING),
            Schemas.STRING, 
            Schemas.NUMBERSTRING,
            Schemas.BOOL}){

                @Override
                public MyCompactData create() {
                    return new MyCompactData();
                }
    };
    
    public MyCompactData() {
        
    }
    
    public int getId() {
        return SCHEMA.getInt(this, 0);
    }
    
    public String getCountryCode() {
        return SCHEMA.getValue(this,1);
    }
    public long getVersion() {
        return SCHEMA.getLong(this, 2);
    }
    
    public double getWeight() {
        return SCHEMA.getDouble(this, 3);
    }
    
    public String getDescription() {
        return SCHEMA.getValue(this, 4);
    }
    
    public MySubData getSubData() {
        return SCHEMA.getValue(this, 5);
    }
    
    public List<String> getPrintTypeIds() {
        return SCHEMA.getValue(this, 6);
    }
    
    public String getName() {
        return SCHEMA.getValue(this, 7);
    }
    
    public String getCurrencyId() {
        return SCHEMA.getValue(this, 8);
    }
    
    public boolean isValid() {
        return SCHEMA.getBoolean(this, 9);
    }
    
    public static void main(String[] args) {
//        MySubData sd = MySubData.SCHEMA.createObject(new Object[]{"213", Math.PI});
//        System.out.println(sd.getId());
//        System.out.println(sd.getWeight());
        
        MyCompactData d =  SCHEMA.createObject(new Object[]{Integer.MAX_VALUE, "de", Long.MAX_VALUE, Double.MAX_VALUE, "testBla und Blabla!", new Object[]{"213", Math.PI}, Arrays.asList(new String[]{"1", "200"}), "Horst Meyer", "200", true});
        MyCompactData d2 = SCHEMA.createObject(new Object[]{Integer.MAX_VALUE, "de", Long.MAX_VALUE, Double.MAX_VALUE, "testBla und Blabla!", new Object[]{"213", Math.PI}, Arrays.asList(new String[]{"1", "200"}), "Horst Meyer", "200", true});
        MyCompactData d3 = SCHEMA.createObject(new Object[]{Integer.MAX_VALUE, "de", Long.MAX_VALUE, Double.MAX_VALUE, "testBla und Blabla!", new Object[]{"213", Math.PI}, Arrays.asList(new String[]{"1", "200"}), "Horst Meyer", "200", true});
        
        System.out.println(d.getData().length);
        
        System.out.println(d.getId());
        System.out.println(d.getCountryCode());
        System.out.println(d.getVersion());
        System.out.println(d.getWeight());
        System.out.println(d.getDescription());
        System.out.println(d.getName());
        System.out.println(d.getCurrencyId());
        System.out.println(d.isValid());
        System.out.println(d.getSubData().getId());
        System.out.println(d.getSubData().getWeight());
        System.out.println(d.getPrintTypeIds());
        
//        System.out.println(d2.getId());
//        System.out.println(d2.getCountryCode());
//        System.out.println(d2.getVersion());
//        System.out.println(d2.getWeight());
//        System.out.println(d2.getDescription());
//        System.out.println(d2.getName());
//        System.out.println(d2.getCurrencyId());
//        System.out.println(d2.isValid());
//        System.out.println(d2.getSubData().getId());
//        System.out.println(d2.getSubData().getWeight());
        
        System.out.println("byte[] Count: "+ByteCaches.getInstance().size());
    }

}
