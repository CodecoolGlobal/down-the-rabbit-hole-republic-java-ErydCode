package com.codecool.dtrhr.logic.chapter;

import com.codecool.dtrhr.logic.chapter.findChapterIndex.ChapterNameDifferentLines;
import com.codecool.dtrhr.logic.chapter.findChapterIndex.ChapterNameSameLine;
import com.codecool.dtrhr.logic.chapter.findChapterIndex.FindChapterStartAndEnd;

import java.util.ArrayList;
import java.util.List;

public class ChapterContent {
    private final ChapterNameDifferentLines chapterNameDifferentLines = new ChapterNameDifferentLines();
    private final ChapterNameSameLine chapterNameSameLine = new ChapterNameSameLine();
    private final List<FindChapterStartAndEnd> methods = List.of(chapterNameDifferentLines, chapterNameSameLine);

    public List<String> getChapterContent(String[] bookLines, int startStory, List<String> chapterNames) {
        List<String> story = new ArrayList<>();
        List<Integer> startChapter = new ArrayList<>();
        List<Integer> endChapter = new ArrayList<>();
        getStartAndEndIndexOfChapters(bookLines, startStory, chapterNames, startChapter, endChapter);
        buildStoryList(bookLines, chapterNames, story, startChapter, endChapter);
        return story;
    }

    private void buildStoryList(String[] bookLines, List<String> chapterNames, List<String> story, List<Integer> startChapter, List<Integer> endChapter) {
        for (int i = 0; i < chapterNames.size(); i++) {
            StringBuilder chapterContent = new StringBuilder();
            int skipNotUsedLines = 2;
            for (int z = 0; z < 4; z++) {
                if (bookLines[startChapter.get(i) + skipNotUsedLines].equals("")) {
                    skipNotUsedLines++;
                }
            }
            for (int y = startChapter.get(i) + skipNotUsedLines; y < endChapter.get(i); y++) {
                chapterContent.append(bookLines[y]).append("\n");
            }
            story.add(chapterContent.toString());
        }
    }

    private void getStartAndEndIndexOfChapters(String[] bookLines, int startStory, List<String> chapterNames, List<Integer> startChapter, List<Integer> endChapter) {
        int index = 0;
        while (startChapter.size() != chapterNames.size() && endChapter.size() != chapterNames.size()) {
            startChapter.clear();
            endChapter.clear();
            methods.get(index).getIndexOfStartEnd(bookLines, startStory, chapterNames, startChapter);
            methods.get(index).getIndexOfStartEnd(bookLines, startStory, chapterNames, endChapter);
            index++;
        }
        endChapter.add(bookLines.length);
        endChapter.remove(0);
    }
}