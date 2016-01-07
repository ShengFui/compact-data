package net.sprd.compact;

public class AbstractCompactData implements CompactData{
    
    protected byte[] data;
    protected int offset;
    protected int[] fieldOffsets;
    
    public AbstractCompactData() {
    }
    
    public void init(byte[] data, int offset) {
        this.offset = offset;
        setData(data);
    }
    
    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int[] getFieldOffsets() {
        return fieldOffsets;
    }

    public void setFieldOffsets(int[] fieldOffsets) {
        this.fieldOffsets = fieldOffsets;
    }

}
