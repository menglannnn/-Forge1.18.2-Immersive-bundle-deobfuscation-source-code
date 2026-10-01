package abductionmod.content;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.Locale;
import org.apache.commons.lang3.StringUtils;

public class am {
  public static String readFile(String paramString) {
    Charset charset = StandardCharsets.UTF_8;
    String str = null;
    try {
      str = new String(Files.readAllBytes((new File(paramString)).toPath()), charset);
    } catch (IOException iOException) {
      throw new RuntimeException(iOException);
    } 
    return str;
  }
  
  public static void copyFile(String paramString1, String paramString2) {
    try {
      (new File(paramString2)).getParentFile().mkdirs();
      Files.copy((new File(paramString1)).toPath(), (new File(paramString2)).toPath(), new CopyOption[] { StandardCopyOption.REPLACE_EXISTING });
    } catch (IOException iOException) {
      throw new RuntimeException(iOException);
    } 
  }
  
  public static void writeContentToFile(String paramString1, String paramString2) {
    try {
      (new File(paramString1)).getParentFile().mkdirs();
      Files.write((new File(paramString1)).toPath(), paramString2.getBytes(), new java.nio.file.OpenOption[0]);
    } catch (IOException iOException) {
      throw new RuntimeException(iOException);
    } 
  }
  
  public static void addLineAfterLine(String paramString1, String paramString2, String paramString3) {
    String str = readFile(paramString1);
    if (str.contains(paramString3))
      return; 
    str = StringUtils.replaceOnce(str, paramString2, paramString2 + "\n" + paramString2);
    writeContentToFile(paramString1, str);
  }
  
  public static String camelToUnderscore(String paramString) {
    return paramString.replaceAll(String.format("%s|%s|%s", new Object[] { "(?<=[A-Z])(?=[A-Z][a-z])", "(?<=[^A-Z])(?=[A-Z])", "(?<=[A-Za-z])(?=[^A-Za-z])" }), "_").toLowerCase(Locale.ROOT);
  }
}


/* Location:              C:\Users\小亮傻逼\Desktop\[Forge1.18.2]沉浸式捆绑V1.2.56.jar!\abductionmod\content\am.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */