package com.codecool.dtrhr;

import com.codecool.dtrhr.io.Write;
import com.codecool.dtrhr.logic.BookService;
import com.codecool.dtrhr.logic.userSelection.SelectBook;
import com.codecool.dtrhr.logic.userSelection.SelectChapter;
import com.codecool.dtrhr.ui.Display;
import com.codecool.dtrhr.ui.Input;
import com.codecool.dtrhr.ui.menuMapProvidor.MapFilesInformation;

import java.io.IOException;

public class Application {

    public static void main(String[] args) throws IOException {
        Display display = new Display();
        Input input = new Input(display);
        String inputPath = "src/main/resources/books/";
        String outputPath = "src/main/resources/booksChapter";
        MapFilesInformation mapFilesInformation = new MapFilesInformation();
        SelectBook selectBook = new SelectBook(mapFilesInformation, display, input, inputPath, outputPath);
        String selectedBook = selectBook.getBook();
        String selectedFileName = selectBook.getFileName();
        BookService bookService = new BookService(selectedBook, selectedFileName);
        SelectChapter selectChapter = new SelectChapter(display, input, bookService);
        Write write = new Write(display, mapFilesInformation, bookService, selectChapter, selectBook);
        write.saveChapterContentAsTxt(outputPath);
    }
}