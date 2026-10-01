package abductionmod.content;

import com.dev.am.Abduction;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.FileAttribute;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.ScriptableObject;

public class bs {
  public static final HashMap<String, String> gagScripts = new HashMap<>();
  
  public static final HashMap<String, aR> toolMaterials = new HashMap<>();
  
  public static void initGagFiles(ResourceManager paramResourceManager) {
    try {
      File file = new File(gagTalksDir());
      if (!file.exists() || file.length() == 0L)
        moveOut(paramResourceManager, gagTalksDir()); 
      readGagJs();
    } catch (Exception exception) {
      Abduction.LOGGER.error(exception.getMessage());
    } 
  }
  
  public static void initToolPropertiesFiles(ResourceManager paramResourceManager) {
    File file = new File(toolDir());
    if (!file.exists() || file.length() == 0L)
      moveOut(paramResourceManager, toolDir()); 
    readFileTools();
  }
  
  private static void readFileTools() {
    toolMaterials.clear();
    File file = new File(toolDir());
    File[] arrayOfFile = file.listFiles();
    if (arrayOfFile != null)
      for (File file1 : arrayOfFile) {
        try {
          HashMap<Object, Object> hashMap = new HashMap<>();
          String str1 = Files.readString(Path.of(file1.getAbsolutePath(), new String[0]));
          JsonObject jsonObject1 = JsonParser.parseString(str1).getAsJsonObject();
          String str2 = jsonObject1.get("item").getAsString();
          JsonObject jsonObject2 = jsonObject1.getAsJsonObject("properties");
          float f1 = jsonObject2.get("scissoring").getAsFloat();
          float f2 = jsonObject2.get("cutting").getAsFloat();
          float f3 = jsonObject2.get("metal_filing").getAsFloat();
          float f4 = jsonObject2.get("metal_cutting").getAsFloat();
          float f5 = jsonObject2.get("fire").getAsFloat();
          hashMap.put(aR.a.SCISSORING, Float.valueOf(f1));
          hashMap.put(aR.a.CUTTING, Float.valueOf(f2));
          hashMap.put(aR.a.METAL_FILING, Float.valueOf(f3));
          hashMap.put(aR.a.METAL_CUTTING, Float.valueOf(f4));
          hashMap.put(aR.a.FIRE, Float.valueOf(f5));
          toolMaterials.put(str2, new aR((HashMap)hashMap));
        } catch (IOException iOException) {
          System.err.println("Failed to read file: " + file1.getName());
        } 
      }  
  }
  
  public static void moveOut(ResourceManager paramResourceManager, String paramString) {
    try {
      List list = paramResourceManager.m_6540_(paramString, paramString -> true).stream().toList();
      for (ResourceLocation resourceLocation : list) {
        if (!resourceLocation.m_135827_().equals("am") || !resourceLocation.m_135815_().contains(paramString + "/"))
          continue; 
        Files.createDirectories(Paths.get(resourceLocation.m_135815_(), new String[0]).getParent(), (FileAttribute<?>[])new FileAttribute[0]);
        Files.copy(paramResourceManager.m_142591_(resourceLocation).m_6679_(), Paths.get(resourceLocation.m_135815_(), new String[0]), new CopyOption[] { StandardCopyOption.REPLACE_EXISTING });
      } 
    } catch (IOException iOException) {
      throw new RuntimeException(iOException);
    } 
  }
  
  public static String gagTalksDir() {
    return "gagscripts";
  }
  
  public static String toolDir() {
    return "untyingtooltypes";
  }
  
  private static void readGagJs() {
    gagScripts.clear();
    File file = new File(gagTalksDir());
    File[] arrayOfFile = file.listFiles();
    for (File file1 : arrayOfFile) {
      if (file1.isFile() && file1.getName().endsWith(".js")) {
        String str1 = file1.getName().replaceFirst("[.][^.]+$", "");
        String str2 = null;
        try {
          str2 = new String(Files.readAllBytes(Paths.get(file1.getAbsolutePath(), new String[0])));
        } catch (IOException iOException) {
          throw new RuntimeException(iOException);
        } 
        gagScripts.put(str1, str2);
      } 
    } 
    for (Map.Entry<String, String> entry : gagScripts.entrySet()) {
      String str = (String)entry.getValue();
      try {
        gagScripts.put((String)entry.getKey(), resolveImports(str));
      } catch (IOException iOException) {
        throw new RuntimeException(iOException);
      } 
    } 
    bm.debugLog(String.valueOf(gagScripts));
  }
  
  public static String transformWithGagStyle(String paramString1, String paramString2) {
    Context context = Context.enter();
    try {
      context.setLanguageVersion(120);
      String str = gagScripts.get(paramString1);
      if (str == null)
        return paramString2; 
      str = str + "\ntextHolder.setText(modifyText(''+textHolder.getText()));";
      a a = new a(paramString2);
      ScriptableObject scriptableObject = context.initStandardObjects();
      ScriptableObject.putProperty((Scriptable)scriptableObject, "textHolder", Context.javaToJS(a, (Scriptable)scriptableObject));
      Object object = context.evaluateString((Scriptable)scriptableObject, str, "EvaluationScript", 1, null);
      return a.getText();
    } catch (Exception exception) {
      bm.debugLog(exception.getMessage());
    } finally {
      Context.exit();
    } 
    return paramString2;
  }
  
  private static String resolveImports(String paramString) {
    StringBuilder stringBuilder = new StringBuilder();
    BufferedReader bufferedReader = new BufferedReader(new StringReader(paramString));
    String str;
    while ((str = bufferedReader.readLine()) != null) {
      String[] arrayOfString = str.replace(";", "").split(" ");
      if (arrayOfString.length == 2 && arrayOfString[0].equals("import")) {
        String str1 = arrayOfString[1];
        if (gagScripts.containsKey(str1)) {
          stringBuilder.append(resolveImports(gagScripts.get(str1)).replace("//!hidden", "")).append("\n");
          continue;
        } 
        Abduction.LOGGER.error("‼️ Cannot import " + str1);
        continue;
      } 
      stringBuilder.append(str).append("\n");
    } 
    return stringBuilder.toString();
  }
  
  public static class a {
    String text;
    
    public a(String param1String) {
      setText(param1String);
    }
    
    public String getText() {
      return this.text;
    }
    
    public void setText(String param1String) {
      this.text = param1String;
    }
  }
}


/* Location:              C:\Users\小亮傻逼\Desktop\[Forge1.18.2]沉浸式捆绑V1.2.56.jar!\abductionmod\content\bs.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */