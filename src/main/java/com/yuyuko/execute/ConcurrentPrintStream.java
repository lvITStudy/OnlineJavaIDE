package com.yuyuko.execute;

import java.io.*;
import java.nio.charset.Charset;

public class ConcurrentPrintStream extends PrintStream {
    private ThreadLocal<ByteArrayOutputStream> out = new ThreadLocal<>();
    private ThreadLocal<Boolean> trouble = new ThreadLocal<>();

    private static ConcurrentPrintStream concurrentPrintStream;

    static {
        concurrentPrintStream = new ConcurrentPrintStream(null);
    }

    private ConcurrentPrintStream(OutputStream stream) {
        super(stream, true, Charset.forName("UTF-8"));
    }

    @Override
    public String toString(){
        return out.get().toString();
    }

    public static ConcurrentPrintStream getInstance() {
        return concurrentPrintStream;
    }


    private void ensureOpen() throws IOException {
        if (out.get() == null)
            out.set(new ByteArrayOutputStream());
    }

    public void flush() {
        try {
            ensureOpen();
            out.get().flush();
        } catch (IOException x) {
            trouble.set(true);
        }
    }

    public void close() {
        try {
            out.get().close();
        } catch (IOException x) {
            trouble.set(true);
        }
        out.remove();
    }

    public boolean checkError() {
        if (out != null)
            flush();
        return trouble.get() != null ? trouble.get() : false;
    }

    protected void setError() {
        trouble.set(true);
    }

    protected void clearError() {
        trouble.remove();
    }

    public void write(int b) {
        try {
            ensureOpen();
            out.get().write(b);
            if ((b == '\n'))
                out.get().flush();
        }
        catch (InterruptedIOException x) {
            Thread.currentThread().interrupt();
        }
        catch (IOException x) {
            trouble.set(true);
        }
    }

    public void write(byte buf[], int off, int len) {
        try {
            ensureOpen();
            out.get().write(buf, off, len);
        }
        catch (InterruptedIOException x) {
            Thread.currentThread().interrupt();
        }
        catch (IOException x) {
            trouble.set(true);
        }
    }

    private void write(char buf[]) {
        try {
            ensureOpen();
            out.get().write(new String(buf).getBytes());
        }
        catch (InterruptedIOException x) {
            Thread.currentThread().interrupt();
        }
        catch (IOException x) {
            trouble.set(true);
        }
    }

    private void write(String s) {
        try {
            ensureOpen();
            out.get().write(s.getBytes());
        }
        catch (InterruptedIOException x) {
            Thread.currentThread().interrupt();
        }
        catch (IOException x) {
            trouble.set(true);
        }
    }

    private void newLine() {
        try {
            ensureOpen();
            out.get().write(System.lineSeparator().getBytes());
        }
        catch (InterruptedIOException x) {
            Thread.currentThread().interrupt();
        }
        catch (IOException x) {
            trouble.set(true);
        }
    }

    public void print(boolean b) {
        write(b ? "true" : "false");
    }

    public void print(char c) {
        write(String.valueOf(c));
    }

    public void print(int i) {
        write(String.valueOf(i));
    }

    public void print(long l) {
        write(String.valueOf(l));
    }

    public void print(float f) {
        write(String.valueOf(f));
    }

    public void print(double d) {
        write(String.valueOf(d));
    }

    public void print(char s[]) {
        write(s);
    }


    public void print(String s) {
        if (s == null) {
            s = "null";
        }
        write(s);
    }

    public void print(Object obj) {
        write(String.valueOf(obj));
    }

    public void println() {
        newLine();
    }

    public void println(boolean x) {
        print(x);
        newLine();
    }

    public void println(char x) {
        print(x);
        newLine();
    }

    public void println(int x) {
        print(x);
        newLine();
    }

    public void println(long x) {
        print(x);
        newLine();
    }

    public void println(float x) {
        print(x);
        newLine();
    }

    public void println(double x) {
        print(x);
        newLine();
    }

    public void println(char x[]) {
        print(x);
        newLine();
    }

    public void println(String x) {
        print(x);
        newLine();
    }

    public void println(Object x) {
        String s = String.valueOf(x);
        print(s);
        newLine();
    }
}
