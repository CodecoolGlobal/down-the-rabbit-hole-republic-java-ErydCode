package com.codecool.dtrhr.logic;

import com.codecool.dtrhr.logic.chapter.ChapterContent;
import com.codecool.dtrhr.logic.chapter.ChapterNames;

import java.util.List;

public class BookService {
    private final String selectedBook;
    private final String selectedFileName;
    private final ChapterNames chapterNames = new ChapterNames();
    private final ChapterContent chapterContent = new ChapterContent();

    public BookService(String selectedBook, String selectedFileName) {
        this.selectedBook = selectedBook;
        this.selectedFileName = selectedFileName;
    }

    public List<String> getChapterName() {
        return chapterNames.chapterName(getBookLines());
    }

    public List<String> getChapterContent() {
        return chapterContent.getChapterContent(getBookLines(), chapterNames.getEndChapter(getBookLines(), chapterNames.getStartChapter(getBookLines())), getChapterName());
    }

    private String[] getBookLines() {
        return selectedBook.split("\n");
    }

    public String getSelectedFileName() {
        return selectedFileName;
    }
}