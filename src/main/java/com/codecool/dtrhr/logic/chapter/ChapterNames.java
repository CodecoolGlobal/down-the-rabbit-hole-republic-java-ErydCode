package com.codecool.dtrhr.logic.chapter;

import java.util.ArrayList;
import java.util.List;

public class ChapterNames {

    public List<String> chapterName(String[] bookLines) {
        List<String> trimChapterName = new ArrayList<>();
        int startChapter = getStartChapter(bookLines);
        int endChapter = getEndChapter(bookLines, startChapter);
        for (int i = startChapter; i < endChapter; i++) {
            trimChapterName.add(bookLines[i].trim());
        }
        return trimChapterName;
    }

    public int getStartChapter(String[] bookLines) {
        int startChapter = 0;
        int startContents = 0;
        for (int i = 0; i < bookLines.length; i++) {
            String actualLine = bookLines[i];
            if (actualLine.equals("Contents")) {
                startContents = i;
            }
            if (startContents != 0) {
                break;
            }
        }
        for (int i = startContents + 1; i < bookLines.length; i++) {
            if (!bookLines[i].equals("\n")) {
                startChapter = i + 1;
            }
            if (startChapter != 0) {
                break;
            }
        }
        return startChapter;
    }

    public int getEndChapter(String[] bookLines, int startChapter) {
        int endChapter = 0;
        for (int i = startChapter; i < bookLines.length; i++) {
            if (bookLines[0].contains(bookLines[i])) {
                endChapter = i;
            }
            if (endChapter != 0) {
                break;
            }
        }
        return endChapter;
    }
}