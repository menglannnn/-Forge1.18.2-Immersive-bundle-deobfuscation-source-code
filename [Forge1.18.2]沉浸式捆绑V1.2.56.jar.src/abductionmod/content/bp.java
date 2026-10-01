package abductionmod.content;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.nio.file.attribute.FileAttribute;

public class bp {
  private static final Path folderName = Paths.get("abuction", new String[0]);
  
  public static void writeToFile(String paramString1, String paramString2) {
    try {
      Path path = Paths.get("" + folderName + "/" + folderName, new String[0]);
      if (!Files.exists(folderName, new java.nio.file.LinkOption[0]))
        Files.createDirectories(folderName, (FileAttribute<?>[])new FileAttribute[0]); 
      Files.write(path, paramString1.getBytes(), new OpenOption[] { StandardOpenOption.CREATE, StandardOpenOption.APPEND });
    } catch (IOException iOException) {
      System.err.println("An error occurred while writing to the file: " + iOException.getMessage());
    } 
  }
}


/* Location:              C:\Users\小亮傻逼\Desktop\[Forge1.18.2]沉浸式捆绑V1.2.56.jar!\abductionmod\content\bp.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */