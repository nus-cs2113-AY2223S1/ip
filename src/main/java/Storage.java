import duke.Task;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    public String filePath;

    public Storage(String filePath){
        this.filePath = filePath;
    }

    public ArrayList<Task> load() throws FileNotFoundException {
        ArrayList<Task> newTaskList = new ArrayList<Task>();
        File f = new File(filePath);
        Scanner s = new Scanner(f);
        while (s.hasNext()){
            String nextInstruction = s.nextLine();
            String[] instSplit = nextInstruction.split(" \\| ");
            String deformattedInst;
            boolean isDone = instSplit[1].equals("0") ? false : true;
            switch (instSplit[0]){
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
