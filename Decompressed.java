import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.GZIPInputStream;

public class Decompressed {
    public static void decompressFile(File compressedFile) throws IOException {
        String fileDirect = compressedFile.getParent();

        try (FileInputStream fis = new FileInputStream(compressedFile);
                GZIPInputStream gzip = new GZIPInputStream(fis);
                FileOutputStream fos = new FileOutputStream(fileDirect + "/DecompressedFile")) {

            byte[] buffer = new byte[1024];
            int len;
            while ((len = gzip.read(buffer)) != -1) {
                fos.write(buffer, 0, len);
            }
        }
    }

    public static void main(String[] args) {
        File compressedFilePath = new File("C:/compressor_decompressor/CompressedFile.gz");
        try {
            decompressFile(compressedFilePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
