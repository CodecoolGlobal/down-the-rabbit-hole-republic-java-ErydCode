package com.codecool.dtrhr.logic.chapter.findChapterIndex;

import java.util.List;

public interface FindChapterStartAndEnd {

    void getIndexOfStartEnd(String[] bookLines, int startStory, List<String> chapterNames, List<Integer> startEndChapter);
}