package com.novotempo.endofyeartoast.utils;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.io.Reader;
import java.io.StringReader;
import java.io.Writer;

public class ReaderInputStream extends InputStream {
    private final Reader reader;
    private final Writer writer;
    private final PipedInputStream inPipe;
 
    public ReaderInputStream(Reader reader) throws IOException {
        this(reader, null);
    }
 
    public ReaderInputStream(final Reader reader, String encoding) throws IOException {
        synchronized (this) {
            this.reader = reader;
            inPipe = new PipedInputStream();
            OutputStream outPipe = new PipedOutputStream(inPipe);
            writer = (encoding == null) ? new OutputStreamWriter(outPipe) : new OutputStreamWriter(outPipe, encoding);
        }
        new Thread(new Copier(), "Copier").start();
    }
 
    public int read() throws IOException {
        return inPipe.read();
    }
 
    public int read(byte b[]) throws IOException {
        return inPipe.read(b);
    }
 
    public int read(byte b[], int off, int len) throws IOException {
        return inPipe.read(b, off, len);
    }
 
    public long skip(long n) throws IOException {
        return inPipe.skip(n);
    }
 
    public int available() throws IOException {
        return inPipe.available();
    }
 
    public synchronized void close() throws IOException {
        close(reader);
        close(writer);
        close(inPipe);
    }
 
    private static void close(Closeable cl) {
        try {
            cl.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
 
    private class Copier implements Runnable {
        public void run() {
            char[] buffer = new char[8192];
            try {
                while (true) {
                    int n;
                    synchronized (ReaderInputStream.this) {
                        n = reader.read(buffer);
                    }
                    if (n == -1)
                        break;
                    synchronized (ReaderInputStream.this) {
                        writer.write(buffer, 0, n);
                        writer.flush();
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                close(reader);
                close(writer);
            }
        }
    }
 
    @SuppressWarnings("resource")
    public static void main(String[] args) throws IOException {
        Reader r = new StringReader("The quick brown fox jumped over the lazy dog");
        InputStream in = new ReaderInputStream(r);
		Reader r2 = new InputStreamReader(in);
        for (int c; (c = r2.read()) != -1;)
            System.out.write(c);
        System.out.println();
    }
}