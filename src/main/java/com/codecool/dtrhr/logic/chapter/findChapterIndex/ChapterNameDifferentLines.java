package com.codecool.dtrhr.logic.chapter.findChapterIndex;

import java.util.List;

public class ChapterNameDifferentLines implements FindChapterStartAndEnd {

    @Override
    public void getIndexOfStartEnd(String[] bookLines, int startStory, List<String> chapterNames, List<Integer> startEndChapter) {
        for (int i = startStory; i < bookLines.length; i++) {
            for (String chapterName : chapterNames) {
                String[] splitChapterName = chapterName.trim().split(" ");
                if (bookLines[i].trim().contains(splitChapterName[0] + " " + splitChapterName[1])) {
                    startEndChapter.add(i);
                }
            }
        }
    }
}