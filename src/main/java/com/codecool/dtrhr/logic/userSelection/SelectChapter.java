package com.codecool.dtrhr.logic.userSelection;

import com.codecool.dtrhr.logic.BookService;
import com.codecool.dtrhr.ui.Display;
import com.codecool.dtrhr.ui.Input;
import com.codecool.dtrhr.ui.menuMapProvidor.MapChapterNames;

import java.util.Map;

public class SelectChapter {
    private final Display display;
    private final Input input;
    private final BookService bookService;
    private String selectedChapterName;

    public SelectChapter(Display display, Input input, BookService bookService) {
        this.display = display;
        this.input = input;
        this.bookService = bookService;
    }

    public String getContent() {
        MapChapterNames mapChapterNames = new MapChapterNames();
        Map<Integer, String> chapters = mapChapterNames.chapterNames(bookService.getChapterName());
        display.printSubtitle("Which chapter do you want to read?");
        display.printMenu(chapters);
        int selectedChapter = input.getIntUserInput(chapters);
        display.printTitle(display.convertFileName(bookService.getSelectedFileName()));
        selectedChapterName = chapters.get(selectedChapter);
        display.printSubtitle(selectedChapterName);
        return bookService.getChapterContent().get(selectedChapter - 1);
    }

    public String getSelectedChapterName() {
        return selectedChapterName;
    }
}