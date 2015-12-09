package net.sprd.bitcompact;

import net.sprd.compact.ValueSize;

public class BitStorage {
    
    public static boolean getBoolean(byte[] data, int bitOffset) {
        return getInt(data, bitOffset, 1)!=0;
    }
    
    public static void setBoolean(byte[] data, int bitOffset, boolean value) {
        BitStorage.setInt(data, bitOffset, 1, value?1:0);
    }
    
    public static int getInt(byte[] data, int bitOffset, int bitCount) {
        int byteOffset = bitOffset>>3;
        int byteBitOffset = bitOffset&7;
        
        int lastBit = bitOffset+bitCount-1;
        int lastByteOffset = lastBit>>3;
        int lastByteBitOffset = lastBit&7;
        
        int bytePos = byteOffset; //current byte position
        int bitPos = 0; //current bit count of all used bytes
        int value=0;//result value
        while(bytePos<=lastByteOffset) {
            int byteValue = data[bytePos] & 255;
            int addBitPos = 8; //bitcount of current byte
            //check for first byte
            if (bytePos == byteOffset && byteBitOffset>0) {
                byteValue = byteValue >> byteBitOffset;
                addBitPos -= byteBitOffset;
            }
            //check for last byte
            if (bytePos == lastByteOffset && lastByteBitOffset!=7) {
                int upBitOffset = lastByteBitOffset;
//                addBitPos -=(7 - lastByteBitOffset);  // not necessary because of lastByte
                if (bytePos == byteOffset && byteBitOffset>0) { //check first = last byte
                    upBitOffset -=byteBitOffset;
                }
                byteValue = ((byteValue<<(7-upBitOffset)) & 255) >>(7-upBitOffset);
            }
            value = value | (byteValue <<bitPos);
            bitPos += addBitPos;
            bytePos++;
        }
        return value;
    }
    
    
    public static void setInt(byte[] data, int bitOffset, int bitCount, int value) {
        int byteOffset = bitOffset>>3;
        int byteBitOffset = bitOffset&7;
        
        int lastBit = bitOffset+bitCount-1;
        int lastByteOffset = lastBit>>3;
        int lastByteBitOffset = lastBit&7;
        
        int bytePos = byteOffset; //current byte position
        while(bytePos<=lastByteOffset) {
            int currentByteValue = 0;
            int byteValue = value & 255;
            int addBitPos = 8; //bitcount of current byte
            //check for first byte
            if (bytePos == byteOffset && byteBitOffset>0) {
                byteValue = (byteValue << byteBitOffset) & 255;
                currentByteValue = ((data[bytePos]<< (8-byteBitOffset)) & 255) >> (8-byteBitOffset);
                addBitPos -= byteBitOffset;
            }

            data[bytePos] = (byte)(currentByteValue | byteValue);
            value = value >> addBitPos;
            bytePos++;
        }
        
    }
    
    public static long getLong(byte[] data, int bitOffset, int bitCount) {
        int byteOffset = bitOffset>>3;
        int byteBitOffset = bitOffset&7;
        
        int lastBit = bitOffset+bitCount-1;
        int lastByteOffset = lastBit>>3;
        int lastByteBitOffset = lastBit&7;
        
        int bytePos = byteOffset; //current byte position
        int bitPos = 0; //current bit count of all used bytes
        long value=0;//result value
        while(bytePos<=lastByteOffset) {
            long byteValue = data[bytePos] & 255;
            int addBitPos = 8; //bitcount of current byte
            //check for first byte
            if (bytePos == byteOffset && byteBitOffset>0) {
                byteValue = byteValue >> byteBitOffset;
                addBitPos -= byteBitOffset;
            }
            //check for last byte
            if (bytePos == lastByteOffset && lastByteBitOffset!=7) {
                int upBitOffset = lastByteBitOffset;
//                addBitPos -=(7 - lastByteBitOffset);  // not necessary because of lastByte
                if (bytePos == byteOffset && byteBitOffset>0) { //check first = last byte
                    upBitOffset -=byteBitOffset;
                }
                byteValue = ((byteValue<<(7-upBitOffset)) & 255) >>(7-upBitOffset);
            }
            value = value | (byteValue <<bitPos);
            bitPos += addBitPos;
            bytePos++;
        }
        return value;
    }
    
    
    public static void setLong(byte[] data, int bitOffset, int bitCount, long value) {
        int byteOffset = bitOffset>>3;
        int byteBitOffset = bitOffset&7;
        
        int lastBit = bitOffset+bitCount-1;
        int lastByteOffset = lastBit>>3;
        int lastByteBitOffset = lastBit&7;
        
        int bytePos = byteOffset; //current byte position
        while(bytePos<=lastByteOffset) {
            int currentByteValue = 0;
            int byteValue = (int) (value & 255);
            int addBitPos = 8; //bitcount of current byte
            //check for first byte
            if (bytePos == byteOffset && byteBitOffset>0) {
                byteValue = (byteValue << byteBitOffset) & 255;
                currentByteValue = ((data[bytePos]<< (8-byteBitOffset)) & 255) >> (8-byteBitOffset);
                addBitPos -= byteBitOffset;
            }

            data[bytePos] = (byte)(currentByteValue | byteValue);
            value = value >> addBitPos;
            bytePos++;
        }
        
    }
    
    public static final int numberStringTermination = 15;
    
    public static int setNumberString(byte[] data, int bitOffset, String value) {
        int i=0;
        int offset = bitOffset;
        for (i = 0;i<value.length();i++) {
            int numberValue = ((value.charAt(i)-'0') & 15);
            setInt(data, offset, 4, numberValue);
            offset += 4;
        }
        setInt(data, offset, 4, numberStringTermination);
        offset += 4;
        return offset-bitOffset;
    }
    
    public static ValueSize<String> getNumberString(byte[] data, int bitOffset) {
        StringBuilder sb = new StringBuilder();
        int offset = bitOffset;
        int val =0;
        do {
            val = getInt(data, offset, 4);
            offset += 4;
            if (val<numberStringTermination) {
                sb.append((char)('0'+(val&15)));
            }
        } while (val < 15);
        return new ValueSize<String>(sb.toString(), offset-bitOffset);
    }
    
    
    public static void print(byte[] data) {
        for(byte b:data) {
            print(b);
        }
        System.out.println();
    }
    public static void print(byte data) {
        print01(data&128);
        print01(data&64);
        print01(data&32);
        print01(data&16);
        print01(data&8);
        print01(data&4);
        print01(data&2);
        print01(data&1);
        System.out.print(" ");
    }
    
    public static void print01(int data) {
        System.out.print((data==0?"0":"1"));
    }

}
