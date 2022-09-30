package storage;

import parser.Parser;
import taskType.Task;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * The class allow an application to load data from hard dist into program and save data from
 * program into hard disk. If the input file does not exist, create a new file to store the
 * program result.
 * */
public class Storage {
    public static final int isDoneIndex = 1;
    public static final int typeIndex = 0;
    public String filePath;

    public Storage(String filePath){
        this.filePath = filePath;
    }

    /**
     * Load the tasks stored in the file into a new taskList.
     * @return taskList which contains tasks stored in the file.
     * @throws FileNotFoundException if the file can not be found.
     */
    public ArrayList<Task> load() throws FileNotFoundException {
        ArrayList<Task> newTaskList = new ArrayList<Task>();
        File f = new File(filePath);
        try {
            if (!f.exists()) {
                f.createNewFile();
            }
        } catch(IOException e) {
            System.out.println("Can not create file");
        }

        Scanner s = new Scanner(f);
        while (s.hasNext()){
            String nextInstruction = s.nextLine();
            String[] instSplit = nextInstruction.split(" \\| ");
            String deformattedInst;
            boolean isDone = instSplit[isDoneIndex].equals("0") ? false : true;
            switch (instSplit[typeIndex]){
                case "T":
                    deformattedInst = String.format("todo %s", instSplit[2]);
                    break;
                case "D":
                    deformattedInst = String.format("deadline %s /by %s", instSplit[2], instSplit[3]);
                    break;
                case "E":
                    deformattedInst = String.format("event %s /at %s", instSplit[2], instSplit[3]);
                    break;
                default:
                    deformattedInst = "Wrong input";
            }

            Parser.implementUserInstruction(newTaskList, deformattedInst, isDone);
        }

        return newTaskList;
    }

    /**
     * Save all tasks in the taskList into the file.
     * @param taskList the taskList which the content is about to be stored into the file.
     * @param filePath the relative path given the location of the file.
     * @throws IOException if task can not be stored into the file.
     */
    public static void saveTasks(ArrayList<Task> taskList, String filePath) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write("");
        fw.close();
        String formattedResult;
        FileWriter fwa = new FileWriter(filePath, true);
        for (Task task : taskList){
            formattedResult = Parser.formatTaskInfo(task);
            fwa.write(formattedResult);
        }

        fwa.close();
    }
}
