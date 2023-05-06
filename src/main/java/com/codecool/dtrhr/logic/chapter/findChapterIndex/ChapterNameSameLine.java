package com.codecool.dtrhr.logic.chapter.findChapterIndex;

import java.util.List;

public class ChapterNameSameLine implements FindChapterStartAndEnd {

    @Override
    public void getIndexOfStartEnd(String[] bookLines, int startStory, List<String> chapterNames, List<Integer> startEndChapter) {
        for (int i = startStory; i < bookLines.length; i++) {
            for (String chapterName : chapterNames) {
                if (bookLines[i].trim().equals(chapterName)) {
                    startEndChapter.add(i);
                }
            }
        }
    }
}