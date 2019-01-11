
import java.io.*;

/**
 * @author liteng
 */
public class FileUtil {
    public static final String DEFAULT_CHARSET = "utf-8";

    private static final int DEFAULT_BUFFER_SIZE = 8192;

    public static boolean exists(String filePath) {
        File file = new File(filePath);
        return exists(file);
    }

    public static boolean exists(File file) {
        if (file != null && file.exists()) {
            return true;
        }

        return false;
    }

    public static boolean createFileNotExists(String filePath) {
        File file = new File(filePath);
        return createFileNotExists(file);
    }

    public static boolean createFileNotExists(File file) {
        if (exists(file)) {
            return true;
        }

        File parentDir = file.getParentFile();

        if (parentDir != null && !exists(parentDir)) {
            parentDir.mkdirs();
        }

        try {
            return file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static String readFile(String filePath) {
        File file = new File(filePath);
        return readFile(file);
    }

    public static String readFile(File file) {
        InputStreamReader isr = null;
        BufferedReader bufferedReader = null;
        StringBuilder builder = new StringBuilder();
        try {
            isr = new InputStreamReader(new FileInputStream(file), DEFAULT_CHARSET);
            bufferedReader = new BufferedReader(isr, DEFAULT_BUFFER_SIZE);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                bufferedReader.readLine();
                builder.append(line);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            close(bufferedReader);
        }
        return builder.toString();
    }


    public static void writeFile(String content, String filePath) {
        writeFile(content, filePath, false);
    }

    public static void writeFile(String content, File file) {
        writeFile(content, file, false);
    }


    public static void writeFile(String content, String filePath, boolean append) {
        File file = new File(filePath);
        writeFile(content, file, append);
    }

    public static void writeFile(String content, File file, boolean append) {
        FileWriter fileWriter = null;
        BufferedWriter bufferedWriter = null;

        try {
            fileWriter = new FileWriter(file, append);
            bufferedWriter = new BufferedWriter(fileWriter, DEFAULT_BUFFER_SIZE);
            bufferedWriter.write(content);
            bufferedWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            close(fileWriter);
            close(bufferedWriter);
        }
    }

    public static boolean deleteFile(String filePath) {
        File file = new File(filePath);
        return deleteFile(file);
    }

    public static boolean deleteFile(File file) {
        if (exists(file)) {
            return file.delete();
        }
        // 如果文件不存在则认为已经删成功
        return true;
    }


    public static boolean deleteDir(String dirPath) {
        File dir = new File(dirPath);
        return deleteDir(dir);
    }

    public static boolean deleteDir(File dir) {
        // 如果不存在也认为删除成功
        if (!exists(dir)) {
            return true;
        }

        if (dir.isDirectory()) {
            File[] children = dir.listFiles();
            if (children != null) {
                for (File child : children) {
                    if (child.isFile()) {
                        child.delete();
                    } else if (child.isDirectory()) {
                        deleteDir(child);
                    }
                }
            }
        }
        // 先将文件夹里的文件删除掉，最后删除空文件夹
        return dir.delete();
    }


    public static void close(Reader reader) {
        try {
            if (reader != null) {
                reader.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void close(Writer writer) {
        try {
            if (writer != null) {
                writer.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
