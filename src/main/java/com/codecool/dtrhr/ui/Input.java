package com.codecool.dtrhr.ui;

import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Input {
    private final Display display;
    private final Scanner scanner = new Scanner(System.in);

    public Input(Display display) {
        this.display = display;
    }

    public int getIntUserInput(Map<Integer, String> items) {
        display.printLines();
        int result = -1;
        while (result < 0 || result > items.size()) {
            Scanner scanner = new Scanner(System.in);
            try {
                display.inputMessage();
                result = scanner.nextInt();
            } catch (NoSuchElementException e) {
                display.errorMessage("Invalid input! Please enter a number from 1 to " + items.size());
            }
        }
        return result;
    }
}