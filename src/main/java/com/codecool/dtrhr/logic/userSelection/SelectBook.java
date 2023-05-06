package com.codecool.dtrhr.logic.userSelection;

import com.codecool.dtrhr.io.Read;
import com.codecool.dtrhr.ui.Display;
import com.codecool.dtrhr.ui.Input;
import com.codecool.dtrhr.ui.menuMapProvidor.MapFilesInformation;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public class SelectBook {
    private final MapFilesInformation mapFilesInformation;
    private final Display display;
    private final Input input;
    private final String inputPath;
    private final String outputPath;
    private String fileName;

    public SelectBook(MapFilesInformation mapFilesInformation, Display display, Input input, String inputPath, String outputPath) {
        this.mapFilesInformation = mapFilesInformation;
        this.display = display;
        this.input = input;
        this.inputPath = inputPath;
        this.outputPath = outputPath;
    }

    public String getBook() throws IOException {
        Map<Integer, String> books = mapFilesInformation.fromFolder(inputPath);
        createFileForEachBook(books);
        display.printTitle("Which book do you want to read");
        display.printMenu(books);
        Read read = new Read(display);
        String book = read.fileReturnString(books, inputPath, input.getIntUserInput(books));
        fileName = read.getFileName();
        return book;
    }

    private void createFileForEachBook(Map<Integer, String> books) {
        for (Map.Entry<Integer, String> book : books.entrySet()) {
            File file = new File(outputPath + "/" + display.getFileNameWithoutTxt(book.getValue()));
            file.mkdir();
        }
    }

    public String getFileName() {
        return fileName;
    }
}