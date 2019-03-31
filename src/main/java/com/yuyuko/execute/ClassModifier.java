package com.yuyuko.execute;

/**
 * 修改Class文件的常量池常量
 */
public class ClassModifier {
    /**
     * Class文件中常量池的起始偏移
     */
    private static final int CONSTANT_POOL_COUNT_INDEX = 8;

    /**
     * 常量池中11种常量的长度
     */
    private static final int CONSTANT_Utf8_info = 1;

    private static final int[] CONSTANT_ITEM_LENGTH = {-1, -1, -1, 5, 5, 9, 9, 3, 3, 5, 5, 5, 5};

    private static final int u1 = 1;
    private static final int u2 = 2;

    private static final int CONSTANT_POOL_INDEX = CONSTANT_POOL_COUNT_INDEX + u2;

    private static int getConstantPoolCount(byte[] classByte) {
        return ByteUtils.bytes2Int(classByte, CONSTANT_POOL_COUNT_INDEX, u2);
    }

    public static byte[] modifyClassUTF8Constant(byte[] classByte, String oldStr, String newStr) {
        int cpc = getConstantPoolCount(classByte);
        int offset = CONSTANT_POOL_INDEX;
        for (int i = 0; i < cpc; ++i) {
            int tag = ByteUtils.bytes2Int(classByte, offset, u1);
            if (tag == CONSTANT_Utf8_info) {
                int len = ByteUtils.bytes2Int(classByte, offset + u1, u2);
                offset += u1 + u2;
                String str = ByteUtils.bytes2String(classByte, offset, len);
                if (str.equalsIgnoreCase(oldStr)) {
                    byte[] strBytes = ByteUtils.string2Bytes(newStr);
                    byte[] strLen = ByteUtils.int2Bytes(newStr.length(), u2);
                    classByte = ByteUtils.bytesReplace(classByte, offset, len, strBytes);
                    classByte = ByteUtils.bytesReplace(classByte, offset - u2, u2, strLen);
                    return classByte;
                } else
                    offset += len;
            } else
                offset += CONSTANT_ITEM_LENGTH[tag];
        }
        return classByte;
    }
}
