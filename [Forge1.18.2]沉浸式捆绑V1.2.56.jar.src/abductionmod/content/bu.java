package abductionmod.content;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.lang3.StringUtils;

public class bu {
  public static boolean isValidImageUrl(String paramString) {
    try {
      URL uRL = new URL(paramString);
      String str1 = uRL.getPath();
      String str2 = uRL.getQuery();
      String str3 = ".*\\.(png|jpg|jpeg|gif)(\\?.*)?$";
      Pattern pattern = Pattern.compile(str3, 2);
      Matcher matcher = pattern.matcher(str1);
      return matcher.matches();
    } catch (MalformedURLException malformedURLException) {
      return false;
    } 
  }
  
  public static boolean isWhitelistedHost(String paramString) {
    try {
      URL uRL = new URL(paramString);
      return Arrays.<String>stream(StringUtils.deleteWhitespace((String)c.allowedHosts.get()).split(",")).anyMatch(paramString -> paramString.equals(paramURL.getHost()));
    } catch (MalformedURLException malformedURLException) {
      return false;
    } 
  }
}


/* Location:              C:\Users\小亮傻逼\Desktop\[Forge1.18.2]沉浸式捆绑V1.2.56.jar!\abductionmod\content\bu.class
 * Java compiler version: 17 (61.0)
 * JD-Core Version:       1.1.3
 */