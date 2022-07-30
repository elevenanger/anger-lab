package io;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author Anger
 * created on 2022/7/30
 * 自定义 io 装饰器
 */
public class LowerCaseInputStream extends FilterInputStream {

    public LowerCaseInputStream(InputStream in) {
        super(in);
    }

    /**
     * 将字符转换成小写
     * @return 小写字符
     */
    @Override
    public int read() throws IOException {
        int c = in.read();
        return (c == -1 ? c : Character.toLowerCase(c));
    }

    @Override
    public int read(byte[] b, int off, int len) throws IOException {
        int result = in.read(b, off, len);
        for (int i = off; i < off + result; i++) {
            b[i] = (byte) Character.toLowerCase((char) b[i]);
        }
        return result;
    }
}
