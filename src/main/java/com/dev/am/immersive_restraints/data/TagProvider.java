/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.commons.io.IOUtils
 *  org.apache.commons.lang3.StringUtils
 */
package com.dev.am.immersive_restraints.data;

import com.dev.am.immersive_restraints.config.NiceNTightSettings;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;

public class TagProvider {
    public static boolean isValidImageUrl(String string) {
        try {
            URL uRL = new URL(string);
            String string2 = uRL.getPath();
            String string3 = uRL.getQuery();
            String string4 = ".*\\.(png|jpg|jpeg|gif)(\\?.*)?$";
            Pattern pattern = Pattern.compile(string4, 2);
            Matcher matcher = pattern.matcher(string2);
            return matcher.matches();
        }
        catch (MalformedURLException malformedURLException) {
            return false;
        }
    }

    public static boolean isWhitelistedHost(String string2) {
        try {
            URL uRL = new URL(string2);
            return Arrays.stream(StringUtils.deleteWhitespace((String)((String)c_0.allowedHosts.get())).split(",")).anyMatch(string -> string.equals(uRL.getHost()));
        }
        catch (MalformedURLException malformedURLException) {
            return false;
        }
    }

    public static String getTextFromUrl(String string) {
        try {
            URL uRL = new URL(string);
            URLConnection uRLConnection = uRL.openConnection();
            Pattern pattern = Pattern.compile("text/html;\\s+charset=([^\\s]+)\\s*");
            Matcher matcher = pattern.matcher(uRLConnection.getContentType());
            String string2 = matcher.matches() ? matcher.group(1) : "UTF-8";
            return IOUtils.toString((InputStream)uRLConnection.getInputStream(), (String)string2);
        }
        catch (Exception exception) {
            return "";
        }
    }
}

