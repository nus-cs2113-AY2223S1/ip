package Tasks;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import Exception.DataCorruptedException;

public class TaskList {
    private final String FILE_PATH = "./data/data.md";
    private ArrayList<Task> inputLists = new ArrayList<Task>();

    public ArrayList<Task> getInputLists() {
        return inputLists;
    }

    public String getItemFromList(int n) {
        String output = "\t";
        output += inputLists.get(n - 1).getCompleteDescription();
        return output;
    }

    public String getCompleteList() {
        String output = "";
        for (int i = 0; i < inputLists.size(); i++) {
            output += Integer.toString(i + 1) + ". " + getItemFromList(i + 1);
        }
        return output;
    }

    public int addTaskToList(String input, TaskType type, boolean isCompleted, boolean toSave) {
        Task newItem;

        if (type == TaskType.EVENT) {
            int indexOfTime = input.indexOf("/at");
            String description = input.substring(0, indexOfTime).strip();
            String time = input.substring(indexOfTime + "/at ".length()).strip();
            newItem = new Event(description, time, isCompleted);
            if (toSave) {
                try {
                    appendTaskToFile(newItem);
                } catch (IOException e) {
                    System.out.println("Something went wrong: " + e.getMessage());
                }
            }
        } else if (type == TaskType.DEADLINE) {
            int indexOfTime = input.indexOf("/by");
            String description = input.substring(0, indexOfTime).strip();
            String time = input.substring(indexOfTime + "/by ".length()).strip();
            newItem = new Deadline(description, time, isCompleted);
            if (toSave) {
                try {
                    appendTaskToFile(newItem);
                } catch (IOException e) {
                    System.out.println("Something went wrong: " + e.getMessage());
                }
            }
        } else if (type == TaskType.TODO) {
            newItem = new Todo(input, isCompleted);
            if (toSave) {
                try {
                    appendTaskToFile(newItem);
                } catch (IOException e) {
                    System.out.println("Something went wrong: " + e.getMessage());
                }
            }
        } else {
            newItem = new Task(input, isCompleted);
        }
        inputLists.add(newItem);
        return inputLists.size() - 1;
    }

    public void deleteTaskFromList(int n) {
        inputLists.remove(n - 1);
    }

    public void markCompleted(int n, boolean bool) {
        inputLists.get(n - 1).setCompleted(bool);
    }

    public int getTaskListSize() {
        return inputLists.size();
    }

    public void addMarkdownHeader() throws IOException {
        FileWriter fw = new FileWriter(FILE_PATH);
        fw.write("|Task Type | Done | Description | Time |\n"
                + "|----------|------|-------------|------|\n");
        fw.close();
    }


    public void updateWholeFile() throws IOException {
        addMarkdownHeader();
        for (Task task : inputLists) {
            appendTaskToFile(task);
        }
    }

    public void appendTaskToFile(Task task) throws IOException {
        FileWriter fw = new FileWriter(FILE_PATH, true);
        fw.write(toMarkdown(task));
        fw.close();
    }

    private String toMarkdown(Task task) {
        String type = task.getClass().getSimpleName();
        boolean bool = task.hasCompleted();
        String description = task.getTaskName();
        String time = "";
        if (!type.equals("Todo")) {
            time = task.getTime();
        }
        return "| " + type + " | " + String.valueOf(bool) + " | " + description + " | " + time + " |\n";
    }
}
