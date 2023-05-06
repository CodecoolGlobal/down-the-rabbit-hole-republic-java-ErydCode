package com.codecool.dtrhr.io;

import com.codecool.dtrhr.ui.Display;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;

public class Read {

    private final Display display;
    private String fileName;

    public Read(Display display) {
        this.display = display;
    }

    public String fileReturnString(Map<Integer, String> books, String path, int input) throws IOException {
        fileName = books.get(input);
        display.printTitle(display.convertFileName(fileName));
        String file = path + "/" + fileName;
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String content;
        StringBuilder output = new StringBuilder();
        while ((content = reader.readLine()) != null) {
            output.append(content).append("\n");
        }
        reader.close();
        return output.substring(1);
    }

    public String getFileName() {
        return fileName;
    }
}