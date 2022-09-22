package duke.util;

import java.util.ArrayList;

public class Ui {

    private final String LINE_DIVIDER = "-------------------";
    ArrayList<String> uiBuffer;


    public Ui() {
        uiBuffer = new ArrayList<>();
    }

    public void printUi() {
        for(String line: uiBuffer) {
            System.out.println(line);
        }
    }

    public void showLine() {
        System.out.println(LINE_DIVIDER);
    }

}
