package net.sprd.compact;

public interface CompactData {

    byte[] getData();
    int[] getFieldOffsets();
    void setFieldOffsets(int[] offsets);
    int getOffset();
    void init(byte[] data, int offset);
}
