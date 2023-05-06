package com.codecool.dtrhr.ui.menuMapProvidor;

import java.io.File;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;

public class MapFilesInformation {
    public Map<Integer, String> fromFolder(String path) {
        Map<Integer, String> files = new TreeMap<>();
        File folder = new File(path);
        int count = 0;
        for (File file : Objects.requireNonNull(folder.listFiles())) {
            if (file.isFile()) {
                count++;
                files.put(count, file.getName());
            }
        }
        return files;
    }
}