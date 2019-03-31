package com.yuyuko.execute;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class JavaClassExecuter {
    public static String execute(byte[]classByte){
        byte[]modiBytes = ClassModifier.modifyClassUTF8Constant(classByte, "java/lang/System", "com/yuyuko/execute/HackSystem");
        HotSwapClassLoader classLoader = new HotSwapClassLoader();
        Class<?> clazz = classLoader.loadByte(modiBytes);
        try{
            Method method = clazz.getMethod("main", new Class[]{String[].class});
            method.invoke(null, (String)null);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.getCause().printStackTrace(HackSystem.err);
        }

        String res = HackSystem.getBufferString();
        HackSystem.closeBuffer();
        return res;
    }
}
