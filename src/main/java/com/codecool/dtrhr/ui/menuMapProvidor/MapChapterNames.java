package com.codecool.dtrhr.ui.menuMapProvidor;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class MapChapterNames {
    public Map<Integer, String> chapterNames(List<String> getChapterName) {
        Map<Integer, String> chapters = new TreeMap<>();
        int count = 0;
        for (String chapter : getChapterName) {
            if (!chapter.isEmpty()) {
                count++;
                chapters.put(count, chapter);
            }
        }
        return chapters;
    }
}