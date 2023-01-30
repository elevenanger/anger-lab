package cn.anger.file;

import java.io.File;

/**
 * @author : anger
 */
public class FileUtil {
    private FileUtil() {}
    private static final int BYTE = 1024;
    public static final String COMMON_DIR = "/Users/" + System.getProperty("user.name") + "/data/";

    public static long getFileSizeBytes(String filePath) {
        File file = new File(filePath);
        return file.length();
    }

   public static double getFileSizeKiloBytes(String filePath) {
        return (double) getFileSizeBytes(filePath) / BYTE;
   }

   public static double getFileSizeMegaBytes(String filePath) {
        return getFileSizeKiloBytes(filePath) / BYTE;
   }

   public static double getFileSizeGigaByte(String filePath) {
        return getFileSizeMegaBytes(filePath) / BYTE;
   }

}
