/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.dev.am.immersive_restraints.item.VulnerabilityType
 *  com.google.gson.JsonObject
 *  com.google.gson.JsonParser
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.server.packs.resources.ResourceManager
 *  org.mozilla.javascript.Context
 *  org.mozilla.javascript.Scriptable
 *  org.mozilla.javascript.ScriptableObject
 */
package com.dev.am.immersive_restraints.data;

import com.dev.am.immersive_restraints.item.VulnerabilityType;
import com.dev.am.immersive_restraints.command.SetCollarNameCommand;
import com.dev.am.immersive_restraints.animation.AnimationId;
import com.dev.am.immersive_restraints.event.EntityEventHandler;
import com.dev.am.immersive_restraints.ImmersiveRestraints;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;
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

public class LanguageProvider {
    public static final HashMap<String, String> gagScripts = new HashMap();
    public static final HashMap<String, VulnerabilityType> toolMaterials = new HashMap();

    public static void initGagFiles(ResourceManager resourceManager) {
        try {
            File file = new File(LanguageProvider.gagTalksDir());
            if (!file.exists() || file.length() == 0L) {
                LanguageProvider.moveOut(resourceManager, LanguageProvider.gagTalksDir());
            }
            LanguageProvider.readGagJs();
        }
        catch (Exception exception) {
            Abduction.LOGGER.error(exception.getMessage());
        }
    }

    public static void initToolPropertiesFiles(ResourceManager resourceManager) {
        File file = new File(LanguageProvider.toolDir());
        if (!file.exists() || file.length() == 0L) {
            LanguageProvider.moveOut(resourceManager, LanguageProvider.toolDir());
        }
        LanguageProvider.readFileTools();
    }

    private static void readFileTools() {
        toolMaterials.clear();
        File file = new File(LanguageProvider.toolDir());
        File[] fileArray = file.listFiles();
        if (fileArray != null) {
            for (File file2 : fileArray) {
                try {
                    HashMap<VulnerabilityType.a, Float> hashMap = new HashMap<VulnerabilityType.a, Float>();
                    String string = Files.readString(Path.of(file2.getAbsolutePath(), new String[0]));
                    JsonObject jsonObject = JsonParser.parseString((String)string).getAsJsonObject();
                    String string2 = jsonObject.get("item").getAsString();
                    JsonObject jsonObject2 = jsonObject.getAsJsonObject("properties");
                    float f = jsonObject2.get("scissoring").getAsFloat();
                    float f2 = jsonObject2.get("cutting").getAsFloat();
                    float f3 = jsonObject2.get("metal_filing").getAsFloat();
                    float f4 = jsonObject2.get("metal_cutting").getAsFloat();
                    float f5 = jsonObject2.get("fire").getAsFloat();
                    hashMap.put(VulnerabilityType.Kind.SCISSORING, Float.valueOf(f));
                    hashMap.put(VulnerabilityType.Kind.CUTTING, Float.valueOf(f2));
                    hashMap.put(VulnerabilityType.Kind.METAL_FILING, Float.valueOf(f3));
                    hashMap.put(VulnerabilityType.Kind.METAL_CUTTING, Float.valueOf(f4));
                    hashMap.put(VulnerabilityType.Kind.FIRE, Float.valueOf(f5));
                    toolMaterials.put(string2, (VulnerabilityType)new bb(hashMap));
                }
                catch (IOException iOException) {
                    System.err.println("Failed to read file: " + file2.getName());
                }
            }
        }
    }

    public static void moveOut(ResourceManager resourceManager, String string2) {
        try {
            List list = resourceManager.listResources(string2, string -> true).stream().toList();
            for (ResourceLocation resourceLocation : list) {
                if (!resourceLocation.getNamespace().equals("immersive_restraints") || !resourceLocation.getPath().contains(string2 + "/")) continue;
                Files.createDirectories(Paths.get(resourceLocation.getPath(), new String[0]).getParent(), new FileAttribute[0]);
                Files.copy(resourceManager.getResource(resourceLocation).open(), Paths.get(resourceLocation.getPath(), new String[0]), StandardCopyOption.REPLACE_EXISTING);
            }
        }
        catch (IOException iOException) {
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
        File file = new File(LanguageProvider.gagTalksDir());
        File[] fileArray = file.listFiles();
        for (File file2 : fileArray) {
            if (!file2.isFile() || !file2.getName().endsWith(".js")) continue;
            String string = file2.getName().replaceFirst("[.][^.]+$", "");
            String string2 = null;
            try {
                string2 = new String(Files.readAllBytes(Paths.get(file2.getAbsolutePath(), new String[0])));
            }
            catch (IOException iOException) {
                throw new RuntimeException(iOException);
            }
            gagScripts.put(string, string2);
        }
        for (Map.Entry entry : gagScripts.entrySet()) {
            String string = (String)entry.getValue();
            try {
                gagScripts.put((String)entry.getKey(), LanguageProvider.resolveImports(string));
            }
            catch (IOException iOException) {
                throw new RuntimeException(iOException);
            }
        }
        EntityEventHandler.debugLog(String.valueOf(gagScripts));
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static String transformMessageWithGagStyle(String string, String string2) {
        Context context = Context.enter();
        try {
            context.setLanguageVersion(120);
            Object object = gagScripts.get(string);
            if (object == null) {
                String string3 = string2;
                return string3;
            }
            object = (String)object + "\ntextHolder.setText(modifyText(''+textHolder.getText()));";
            a a2 = new a(string2);
            ScriptableObject scriptableObject = context.initStandardObjects();
            ScriptableObject.putProperty((Scriptable)scriptableObject, (String)"textHolder", (Object)Context.javaToJS((Object)a2, (Scriptable)scriptableObject));
            Object object2 = context.evaluateString((Scriptable)scriptableObject, (String)object, "EvaluationScript", 1, null);
            String string4 = a2.getText();
            return string4;
        }
        catch (Exception exception) {
            EntityEventHandler.debugLog(exception.getMessage());
        }
        finally {
            Context.exit();
        }
        return string2;
    }

    private static String resolveImports(String string) {
        String string2;
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader bufferedReader = new BufferedReader(new StringReader(string));
        while ((string2 = bufferedReader.readLine()) != null) {
            String[] stringArray = string2.replace(";", "").split(" ");
            if (stringArray.length == 2 && stringArray[0].equals("import")) {
                String string3 = stringArray[1];
                if (gagScripts.containsKey(string3)) {
                    stringBuilder.append(LanguageProvider.resolveImports(gagScripts.get(string3)).replace("//!hidden", "")).append("\n");
                    continue;
                }
                Abduction.LOGGER.error("\u203c\ufe0f Cannot import " + string3);
                continue;
            }
            stringBuilder.append(string2).append("\n");
        }
        return stringBuilder.toString();
    }

    public static class a {
        String text;

        public a(String string) {
            this.setText(string);
        }

        public String getText() {
            return this.text;
        }

        public void setText(String string) {
            this.text = string;
        }
    }
}

