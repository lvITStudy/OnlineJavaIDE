package com.yuyuko.execute;

import java.io.Console;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.nio.channels.Channel;
import java.util.Properties;

/**
 * 将System修改为HackSystem
 */
public class HackSystem {
    public static final InputStream in = System.in;

    public static final PrintStream out = ConcurrentPrintStream.getInstance();

    public static final PrintStream err = out;

    public static String getBufferString() {
        return out.toString();
    }

    public static void closeBuffer() {
        out.close();
    }

    public static void setIn(InputStream in) {
        throw new SecurityException("Use hazardous method: System.setIn().");
    }

    public static void setOut(PrintStream out) {
        throw new SecurityException("Use hazardous method: System.setOut().");
    }

    public static void setErr(PrintStream err) {
        throw new SecurityException("Use hazardous method: System.setErr().");
    }

    public static Console console() {
        throw new SecurityException("Use hazardous method: System.console().");
    }

    public static Channel inheritedChannel() throws IOException {
        throw new SecurityException("Use hazardous method: System.inheritedChannel().");
    }

    public static
    void setSecurityManager(final SecurityManager s) {
        throw new SecurityException("Use hazardous method: System.setSecurityManager().");
    }

    public static SecurityManager getSecurityManager() {
        throw new SecurityException("Use hazardous method: System.getSecurityManager().");
    }

    public static long currentTimeMillis() {
        return System.currentTimeMillis();
    }

    public static long nanoTime() {
        return System.nanoTime();
    }

    public static void arraycopy(Object src, int srcPos, Object dest, int destPos, int length) {
        System.arraycopy(src, srcPos, dest, destPos, length);
    }

    public static int identityHashCode(Object x) {
        return System.identityHashCode(x);
    }

    public static Properties getProperties() {
        throw new SecurityException("Use hazardous method: System.getProperties().");
    }

    public static String lineSeparator() {
        return System.lineSeparator();
    }

    public static void setProperties(Properties props) {
        throw new SecurityException("Use hazardous method: System.setProperties().");
    }

    public static String getProperty(String key) {
        throw new SecurityException("Use hazardous method: System.getProperty().");
    }

    public static String getProperty(String key, String def) {
        throw new SecurityException("Use hazardous method: System.getProperty().");
    }

    public static String setProperty(String key, String value) {
        throw new SecurityException("Use hazardous method: System.setProperty().");
    }

    public static String clearProperty(String key) {
        throw new SecurityException("Use hazardous method: System.clearProperty().");
    }

    public static String getenv(String name) {
        throw new SecurityException("Use hazardous method: System.getenv().");
    }

    public static java.util.Map<String,String> getenv() {
        throw new SecurityException("Use hazardous method: System.getenv().");
    }

    public static void exit(int status) {
        throw new SecurityException("Use hazardous method: System.exit().");
    }

    public static void gc() {
        throw new SecurityException("Use hazardous method: System.gc().");
    }

    public static void runFinalization() {
        throw new SecurityException("Use hazardous method: System.runFinalization().");
    }

    @Deprecated
    public static void runFinalizersOnExit(boolean value) {
        throw new SecurityException("Use hazardous method: System.runFinalizersOnExit().");
    }

    public static void load(String filename) {
        throw new SecurityException("Use hazardous method: System.load().");
    }

    public static void loadLibrary(String libname) {
        throw new SecurityException("Use hazardous method: System.loadLibrary().");
    }

    public static String mapLibraryName(String libname) {
        throw new SecurityException("Use hazardous method: System.mapLibraryName().");
    }

}