import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.GZIPOutputStream;

public class Compressor {
   public static void compressFile(File file) throws IOException {
      String fileDirect = file.getParent();

      try (FileInputStream fis = new FileInputStream(file);
            FileOutputStream fos = new FileOutputStream(fileDirect + "/CompressedFile.gz");
            GZIPOutputStream gzip = new GZIPOutputStream(fos)) {

         byte[] buffer = new byte[1024];
         int len;
         while ((len = fis.read(buffer)) != -1) {
            gzip.write(buffer, 0, len);
         }
      }
   }

   public static void main(String[] args) {
      File path = new File("C:/compressor_decompressor/Testfile");
      try {
         compressFile(path);
      } catch (IOException e) {
         e.printStackTrace();
      }
   }
}
