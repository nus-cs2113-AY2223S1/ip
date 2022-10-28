package StoragePackage;

import TaskPackage.*;

import java.io.File;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;

/*
Storage reads and writes Tasks from and into a txt file.
 */
public class Storage {
    private static String filePath;
    public Storage(String filePath){
        this.filePath = filePath;
    }
    private static void writeToFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(textToAdd);
        fw.close();
    }
    /*
    takes in TaskList as an input and converts into String fileContent which is saved into .txt file
     */
    public static void saveTasks(TaskList tasksToSave) {
        String fileContent = "";
        for (int i = 0; i < tasksToSave.getTasks().size(); i++) {
            fileContent += (tasksToSave.getTasks().get(i).toString());
            fileContent += "\n";
        }
        try {
            writeToFile(filePath, fileContent);
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

    /*
    Raw file content is converted into TaskList. This is done by repeatedly calling
    decodeSingleTask for each line in the .txt file
     */
    public static TaskList retrieveTasks(){
        TaskList tasks = new TaskList();
        File f = new File(filePath);
        try {
            Scanner s = new Scanner(f);
            while (s.hasNext()) {
                String next = s.nextLine();
                tasks.getTasks().add(decodeSingleTask(next));
            }
        }catch (FileNotFoundException e){
            System.out.println("file not found");
        }
        return tasks;
    }

    /*
    A single String line from the .txt file is converted into a single Task.
     */
    private static Task decodeSingleTask(String next){
        char TaskType = next.charAt(1);
        boolean isDone = (next.charAt(4) == 'X');
        Task newElement = new Task("");
        if(TaskType == 'T'){
            String description = next.substring(next.lastIndexOf("]") + 2);
            newElement = new Todo(description);
        } else if(TaskType == 'E'){
            String description = next.substring(next.lastIndexOf("]") + 2, next.indexOf("(at:") - 1);
            String at = next.substring(next.indexOf("(at:") + 5, next.indexOf(")"));
            newElement = new Event(description, at);
        } else if(TaskType == 'D'){
            String description = next.substring(next.lastIndexOf("]") + 2, next.indexOf("(by:") - 1);
            String by = next.substring(next.indexOf("(by:") + 5, next.indexOf(")"));
            newElement = new Deadline(description, by);
        }
        if(isDone){
            newElement.markAsDone();
        }
        return newElement;
    }
}
