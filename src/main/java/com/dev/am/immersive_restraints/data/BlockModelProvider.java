/*
 * Decompiled with CFR 0.152.
 */
package com.dev.am.immersive_restraints.data;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.nio.file.attribute.FileAttribute;

public class BlockModelProvider {
    private static final Path folderName = Paths.get("abuction", new String[0]);

    public static void writeToFile(String string, String string2) {
        try {
            Path path = Paths.get(folderName + "/" + string2, new String[0]);
            if (!Files.exists(folderName, new LinkOption[0])) {
                Files.createDirectories(folderName, new FileAttribute[0]);
            }
            Files.write(path, string.getBytes(), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        }
        catch (IOException iOException) {
            System.err.println("An error occurred while writing to the file: " + iOException.getMessage());
        }
    }
}

