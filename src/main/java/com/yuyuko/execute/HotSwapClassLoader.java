package com.yuyuko.execute;

public class HotSwapClassLoader extends ClassLoader {
    protected HotSwapClassLoader() {
        super(HotSwapClassLoader.class.getClassLoader());
    }

    public Class<?> loadByte(byte[] classByte) {
        return defineClass(null, classByte, 0, classByte.length);
    }


}
