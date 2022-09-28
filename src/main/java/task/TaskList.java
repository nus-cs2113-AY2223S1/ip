package task;

import storage.Storage;

import java.io.IOException;
import java.util.ArrayList;

public class TaskList {

    private static ArrayList<Task> tasks;
    private static int taskCount = 0;
    private static final String LINE_DIVIDER = "-------------------------------------";
    private static final String ROGER_MESSAGE = "Got it. I have added this task:";

    public TaskList(ArrayList<Task> tasks) {
        TaskList.tasks = tasks;
        TaskList.taskCount = tasks.size();
    }

    public static int getTaskCount() {
        return taskCount;
    }

    public void addTask(Task task) {
        tasks.add(task);
        taskCount++;
    }


    public void markTarget(int target) {
        tasks.get(target - 1).setDone(true);
    }

    public void unmarkTarget(int target) {
        tasks.get(target - 1).setDone(false);
    }

    public static void printList() {
        System.out.println("Here are a list of your tasks: ");
        int index = 1;
        for (Task task : tasks) {
            System.out.println(index + ". " + task);
            index++;
        }
        System.out.println(LINE_DIVIDER);
    }

    public void deleteTask(int index) {
        tasks.remove(index - 1);
        taskCount--;
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public Task getTask(int index) {
        return tasks.get(index);
    }

    public void storeTask(Task task, Storage storage) {
        try {
            storage.writeToFile(task);
        } catch (IOException e) {
            System.out.println("File not found/missing directory.");
        }
    }

    public void rewriteFile(Storage storage) {
        try {
            storage.deleteContent();
            for (Task task : tasks) {
                storage.writeToFile(task);
            }
        } catch (IOException e) {
            System.out.println("File is not found/missing directory.");
        }
    }
}
