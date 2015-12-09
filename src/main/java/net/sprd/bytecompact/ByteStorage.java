package net.sprd.bytecompact;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;

import net.sprd.compact.ValueSize;

public class ByteStorage {
    
    public static boolean getBoolean(byte[] data, int offset) {
        return data[offset]!=0;
    }
    
    public static void setBoolean(byte[] data, int offset, boolean value) {
        data[offset]=(byte) (value?-1:0);
    }
    
    public static byte getByte(byte[] data, int offset) {
        return data[offset];
    }
    
    public static void setByte(byte[] data, int offset, byte value) {
        data[offset]=value;
    }
    
    public static int getInt(byte[] data, int offset) {
        return (data[offset] & 255) | (data[offset+1] & 255)<<8 | (data[offset+2] & 255)<<16 | (data[offset+3] & 255)<<24;
    }
    
    public static void setInt(byte[] data, int offset, int value) {
        data[offset]=(byte) (value);
        data[offset+1]=(byte) ((value>>8) & 255);
        data[offset+2]=(byte) ((value>>16) & 255);
        data[offset+3]=(byte) ((value>>24) & 255);
    }
    
    public static String getAsciiString(byte[] data, int offset, int length) {
        char[] chars = new char[length];
        for (int i = 0;i<length;i++) {
            if (data[offset+i] == 0) {
                break;
            }
            chars[i] = (char)data[offset+i];
        }
        return new String(chars);
    }
    
    public static void setAsciiString(byte[] data, int offset, int length, String value) {
        for (int i = 0;i<length;i++) {
            if (i>= value.length()) {
                data[offset+i] = 0;
                break;
            } else {
                data[offset+i] = (byte)value.charAt(i);
            }
        }
    }
    
    public static ValueSize<String> getNumberString(byte[] data, int offset) {
        int stringSize = getNumberStringByteCount(data, offset);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i<stringSize-1; i++) {
            int b = data[offset+i];
            sb.append((char)('0'+(b&15)));
            int b2 = (b>>4) & 15;
            if (b2<15) {
                sb.append((char)('0'+(b2&15)));
            }
        }
        return new ValueSize<String>(sb.toString(), stringSize);
    }
    
    public static int getNumberStringByteCount(byte[] data, int offset) {
        for (int i = offset;i < data.length;i++) {
            if (data[i] == -1) {
                return i-offset+1;
            }
        }
        return 0;
    }
    
    public static int getNumberStringByteCount(String value) {
        int len = value.length();
        if ((len & 1) == 1) {
            return  value.length()/2 + 2;
        } else {
            return value.length()/2 +1;
        }
    }
    
    public static int setNumberString(byte[] data, int offset, String value) {
        int i=0;
        for (i = 0;i*2<value.length();i++) {
            byte b = (byte)((value.charAt(i*2)-'0') & 15);
            if (i*2+1<value.length()) {
                b = (byte) (b | (((byte)((value.charAt(i*2+1)-'0') & 15))<<4));
            } else {
                b = (byte)(b | (15<<4));
            }
            data[offset+i] = b;
        }
        data[offset+i] = -1;
        return i+1;
    }
    
    public static ValueSize<String> getString(byte[] data, int offset) {
        int stringSize = getStringByteCount(data, offset);
        int index = stringSize + offset;
        String value = stringSize > 0 ? new String(Arrays.copyOfRange(data, offset, index-1), StandardCharsets.UTF_8): "";
        return new ValueSize<String>(value, stringSize);
    }
    
    public static int getStringByteCount(byte[] data, int offset) {
        for (int i = offset;i < data.length;i++) {
            if (data[i] == 0) {
                return i-offset+1;
            }
        }
        return 0;
    }
    
    public static int getStringByteCount(String value) {
        if (value == null || value.isEmpty()) {
            return 1;
        }
        return value.getBytes(StandardCharsets.UTF_8).length+1;
    }
    
    public static int setString(byte[] data, int offset, String value) {
        if (value == null) {
            data[offset] = 0;
            return 1;
        }
        byte[] b = value.getBytes(StandardCharsets.UTF_8);
        for (int i = 0;i<b.length;i++) {
           data[offset+i] = b[i];
        }
        data[offset+b.length] = 0;
        return b.length+1;
    }
    public static long getLong(byte[] data, int offset) {
        return (long) (data[offset] & 255)| (long) (data[offset+1] & 255)<<8 | (long) (data[offset+2]& 255)<<16 | (long) (data[offset+3] & 255)<<24 | (long) (data[offset+4] & 255)<<32 | (long) (data[offset+5] & 255)<<40 | (long) (data[offset+6] & 255)<<48 | (long) (data[offset+7] & 255) <<56;
    }
    
    public static void setLong(byte[] data, int offset, long value) {
        data[offset]=(byte) value;
        data[offset+1]=(byte) (value>>8);
        data[offset+2]=(byte) (value>>16);
        data[offset+3]=(byte) (value>>24);
        data[offset+4]=(byte) (value>>32);
        data[offset+5]=(byte) (value>>40);
        data[offset+6]=(byte) (value>>48);
        data[offset+7]=(byte) (value>>56);
    }
    
    public static double getDouble(byte[] data, int offset) {
        long longValue = getLong(data, offset);
        return Double.longBitsToDouble(longValue);
    }
    
    public static void setDouble(byte[] data, int offset, double value) {
        long longValue = Double.doubleToRawLongBits(value);
        setLong(data, offset, longValue);
    }

}
