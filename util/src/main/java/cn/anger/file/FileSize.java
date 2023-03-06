package cn.anger.file;

import java.text.DecimalFormat;
import java.text.NumberFormat;

/**
 * @author : anger
 * 文件大小转换类
 */
public enum FileSize {

    BYTE(0, "B"),
    KILO_BYTE(10, "KB"),
    MEGA_BYTE(20, "MB"),
    GIGA_BYTE(30, "GB"),
    TERA_BYTE(40, "TB");

    private final int shiftBit;
    private final String abbr;

    FileSize(int shiftBit, String abbr) {
        this.shiftBit = shiftBit;
        this.abbr = abbr;
    }

    private static final NumberFormat formatter = new DecimalFormat(".00");

    public double toSize(long byteSize) {
        return (double) byteSize / (1 << shiftBit);
    }

    public static String toFixedString(long byteSize) {
        for (int i = 1; i < values().length; i++) {
            if (byteSize >> values()[i].shiftBit == 0)
                return formatter.format((double) byteSize / (1 << values()[i - 1].shiftBit))
                            .concat(values()[i - 1].abbr);
        }

        return byteSize + " B";
    }

}
