package duke;

import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private String filePath;
    private File data;

    public Storage(String filePath) {
        this.filePath = filePath;
        createFile();
    }

    public void createFile() {
        this.data = new File(filePath);
        try {
            data.createNewFile();
            System.out.println("File have been created.");
        } catch (Exception e) {
            System.out.println("File creation failed with exception.");
        }
        //this.data = f;
    }

    public void load(ArrayList<Task> tasks) {
        try {
            if (data.length() != 0) {
                readFile(tasks);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
    }

    public void readFile(ArrayList<Task> tasks) throws FileNotFoundException {
        Scanner s = new Scanner(data);
        while (s.hasNext()) {
            String line = s.nextLine();
            String[] parts = line.split("/");
            decodeLine(parts, tasks);
        }
    }

    public void decodeLine(String[] parts, ArrayList<Task> tasks) {
        String taskType = parts[0];
        String taskState = parts[1];
        String taskDescription = parts[2];
        String taskAdditional = "invalid";
        if (parts.length > 3) {
            taskAdditional = parts[3];
        }

        if (taskType.equals("T")) {
            tasks.add(new Todo(taskDescription));
        } else if (taskType.equals("D")) {
            tasks.add(new Deadline(taskDescription, taskAdditional));
        } else if (taskType.equals("E")) {
            tasks.add(new Event(taskDescription, taskAdditional));
        }
        boolean isDone = true;
        if (taskState.equals(" ")) {
            isDone = false;
        }
        int taskIndex = tasks.size() - 1;
        tasks.get(taskIndex).setCompletion(isDone);
    }

    public void addToFile(Task t, int index) {
        String state;
        if (t.getStatus().equals("[X]")) {
            state = "X";
        } else {
            state = " ";
        }
        String type = t.getTaskType().substring(1, 2);
        String addedInfo = t.getAddedInfo();
        if (addedInfo.length() > 6) {
            int i = t.getAddedInfo().indexOf(")");
            addedInfo = t.getAddedInfo().substring(6, i);
        }
        try {
            writeToFile(type + "/" + state + "/" + t.getDescription() + "/" + addedInfo, index + 1);
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

    public void writeToFile(String textToAdd, int n) throws IOException {
        FileWriter fw = new FileWriter(filePath, true);
        if (n == 1) {
            fw.write(textToAdd);
        } else {
            fw.write(System.lineSeparator() + textToAdd);
        }
        fw.close();
    }

    public void updateFile(ArrayList<Task> tasks) {
        try {
            editionToFile(tasks);
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

    public void editionToFile(ArrayList<Task> tasks) throws IOException {
        new FileWriter(filePath, false).close();
        int n = 0;
        for (Task t : tasks) {
            addToFile(t, n);
            n += 1;
        }
    }
}