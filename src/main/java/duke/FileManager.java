package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.IOException;
import java.io.FileWriter;



public class FileManager {
    public static final String filename = "duke.txt";
    public static final String event = "E";
    public static final String todo = "T";
    public static final String deadline = "D";
    public static final String divider = " | ";
    public static final String dividerSplit = " \\| ";
    private static final File file = new File(filename);


    public static void OpenOrCreateFile() throws DukeException {
        try {
            file.createNewFile();
        } catch (IOException e){
            throw new DukeException();
        }
    }

    public static void uploadDataToList(List list) throws DukeException {
        try{
            Scanner reader = new Scanner(file);
            while (reader.hasNextLine()) {
                String data = reader.nextLine();
                translateLineFromFile(data,list);
            }
        } catch (FileNotFoundException e){
            throw new DukeException();
        }
    }

    private static void markIfDone(List list,String isDone) throws DukeException {
        if (isDone.equals("1")){
            list.markItemDone(list.getListSize());
        }
        if (!isDone.equals("1") && !isDone.equals("0")){
            throw new DukeException();
        }

    }
    public static void translateLineFromFile(String line, List list) throws DukeException {
        String[] words = line.split(dividerSplit);
        switch (words[0]){
            case todo:{
                List.AddTodo(words[2]);
                break;
            }
            case event: {
                List.AddEvent(words[2], words[3]);
                break;
            }
            case deadline: {
                List.AddDeadline(words[2],words[3]);
                break;
            }
            default: {
                throw new DukeException();
            }
        }
        markIfDone(list,words[1]);
    }

    public static void saveListToFile(List list) throws DukeException {
        try {
            FileWriter fw = new FileWriter(filename);
            for (int i = 0; i < list.getListSize(); i++) {
                fw.write(list.getTaskFromList(i).getFileFormat()+System.lineSeparator());
            }
            fw.close();
        } catch (IOException e){
            throw new DukeException();
        }

    }

}
