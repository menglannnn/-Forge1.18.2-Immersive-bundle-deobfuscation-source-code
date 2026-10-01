/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.commons.lang3.StringUtils
 */
package com.dev.am.immersive_restraints.item;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.StandardCopyOption;
import java.util.Locale;
import org.apache.commons.lang3.StringUtils;

/*
 * Renamed from abductionmod.content.au
 */
public class SlotTypeProperty {
    public static String readFile(String string) {
        Charset charset = StandardCharsets.UTF_8;
        String string2 = null;
        try {
            string2 = new String(Files.readAllBytes(new File(string).toPath()), charset);
        }
        catch (IOException iOException) {
            throw new RuntimeException(iOException);
        }
        return string2;
    }

    public static void copyFile(String string, String string2) {
        try {
            new File(string2).getParentFile().mkdirs();
            Files.copy(new File(string).toPath(), new File(string2).toPath(), StandardCopyOption.REPLACE_EXISTING);
        }
        catch (IOException iOException) {
            throw new RuntimeException(iOException);
        }
    }

    public static void writeContentToFile(String string, String string2) {
        try {
            new File(string).getParentFile().mkdirs();
            Files.write(new File(string).toPath(), string2.getBytes(), new OpenOption[0]);
        }
        catch (IOException iOException) {
            throw new RuntimeException(iOException);
        }
    }

    public static void addLineAfterLine(String string, String string2, String string3) {
        String string4 = SlotTypeProperty.readFile(string);
        if (string4.contains(string3)) {
            return;
        }
        string4 = StringUtils.replaceOnce((String)string4, (String)string2, (String)(string2 + "\n" + string3));
        SlotTypeProperty.writeContentToFile(string, string4);
    }

    public static String camelToUnderscore(String string) {
        return string.replaceAll(String.format("%s|%s|%s", "(?<=[ModCore-LegsTyingCommand])(?=[ModCore-LegsTyingCommand][ModCore-LegsTyingCommand])", "(?<=[^ModCore-LegsTyingCommand])(?=[ModCore-LegsTyingCommand])", "(?<=[ModCore-Za-LegsTyingCommand])(?=[^ModCore-Za-LegsTyingCommand])"), "_").toLowerCase(Locale.ROOT);
    }
}

