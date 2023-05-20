package gg.jte.output;

import gg.jte.TemplateOutput;

import java.io.IOException;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

/**
 * UTF-8 template output, that buffers the entire output into a byte array.
 * You may want to use this class over {@link Utf8ByteOutput}, if all you need is a byte array containing the output.
 * In this case it will be faster than storing chunks of byte arrays, just to convert them to a byte array in the end.
 *
 * CAUTION: You must enable {@link gg.jte.TemplateEngine#setBinaryStaticContent(boolean)}, otherwise this class won't provide any benefits over {@link StringOutput}!
 */
public final class Utf8ByteArrayOutput extends Writer implements TemplateOutput {

    private byte[] buffer;
    private int position;

    public Utf8ByteArrayOutput() {
        this(8 * 1024);
    }

    public Utf8ByteArrayOutput(int initialCapacity) {
        buffer = new byte[initialCapacity];
    }

    @Override
    public Writer getWriter() {
        return this;
    }

    @Override
    public void writeContent(String value) {
        writeBinaryContent(value.getBytes(StandardCharsets.UTF_8));
    }

    @Override
    public void writeBinaryContent(byte[] value) {
        ensureCapacity(position + value.length);
        System.arraycopy(value, 0, buffer, position, value.length);
        position += value.length;
    }

    @Override
    public void write(String value) {
        writeBinaryContent(value.getBytes(StandardCharsets.UTF_8));
    }

    @Override
    public void write(char[] cbuf, int off, int len) throws IOException {
        writeContent(new String(cbuf, off, len));
    }

    @Override
    public void flush() {
        // nothing to do
    }

    @Override
    public void close() {
        // nothing to do
    }

    public byte[] toByteArray() {
        return Arrays.copyOf(buffer, position);
    }

    private void ensureCapacity(int minCapacity) {
        if (buffer.length < minCapacity) {
            buffer = Arrays.copyOf(buffer, Math.max(minCapacity, 2 * buffer.length));
        }
    }
}
