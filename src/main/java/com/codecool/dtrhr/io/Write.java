package com.codecool.dtrhr.io;

import com.codecool.dtrhr.logic.BookService;
import com.codecool.dtrhr.logic.userSelection.SelectBook;
import com.codecool.dtrhr.logic.userSelection.SelectChapter;
import com.codecool.dtrhr.ui.Display;
import com.codecool.dtrhr.ui.menuMapProvidor.MapFilesInformation;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Write {
    private final Display display;
    private final MapFilesInformation mapFilesInformation;
    private final BookService bookService;
    private final SelectChapter selectChapter;
    private final SelectBook selectBook;

    public Write(Display display, MapFilesInformation mapFilesInformation, BookService bookService, SelectChapter selectChapter, SelectBook selectBook) {
        this.display = display;
        this.mapFilesInformation = mapFilesInformation;
        this.bookService = bookService;
        this.selectChapter = selectChapter;
        this.selectBook = selectBook;
    }

    public void saveChapterContentAsTxt(String path) throws IOException {
        String fileNameWithoutTxt = display.getFileNameWithoutTxt(bookService.getSelectedFileName());
        String outputBookFolderPath = path + "/" + fileNameWithoutTxt;
        Map<Integer, String> chapterFiles = mapFilesInformation.fromFolder(outputBookFolderPath);
        while (chapterFiles.size() + 1 < bookService.getChapterName().size()) {
            chapterFiles = mapFilesInformation.fromFolder(outputBookFolderPath);
            String selectChapterContent = selectChapter.getContent();
            boolean isFileExisting = isFileExisting(fileNameWithoutTxt, chapterFiles);
            if (!isFileExisting) {
                display.message(selectChapterContent.trim());
                display.printEndLines();
                writeFile(outputBookFolderPath, fileNameWithoutTxt, selectChapterContent);
            }
        }
        selectBook.getBook();
    }

    private boolean isFileExisting(String fileNameWithoutTxt, Map<Integer, String> chapterFiles) {
        boolean isFileExisting = false;
        for (Map.Entry<Integer, String> item : chapterFiles.entrySet()) {
            if (item.getValue().equals(generateFileName(fileNameWithoutTxt))) {
                isFileExisting = true;
            } else {
                display.errorMessage("This chapter file already exists. Please choose another chapter.");
            }
        }
        return isFileExisting;
    }

    private void writeFile(String outBookFolderPath, String fileNameWithoutTxt, String selectChapterContent) {
        File file = new File(outBookFolderPath + "/" + generateFileName(fileNameWithoutTxt));
        FileWriter fileWriter = null;
        BufferedWriter bufferedWriter = null;
        try {
            fileWriter = new FileWriter(file);
            bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(selectChapterContent);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                assert bufferedWriter != null;
                bufferedWriter.close();
                fileWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private String generateFileName(String fileNameWithoutTxt) {
        String convertedFileName = "";
        if (selectChapter.getSelectedChapterName() != null) {
            List<String> splitFileName = new ArrayList<>(List.of(selectChapter.getSelectedChapterName().split(" ")));
            int indexes = splitFileName.size();
            for (int i = indexes; i > 0; i--) {
                splitFileName.remove("");
            }
            String joinFileName = String.join("-", splitFileName);
            convertedFileName = fileNameWithoutTxt + "-" + joinFileName
                    .replace(".", "").replace("’", "").replace("?", "");
        }
        return convertedFileName + ".txt";
    }
}