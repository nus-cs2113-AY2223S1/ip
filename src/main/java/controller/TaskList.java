package controller;

import tasks.Deadline;
import tasks.Task;
import tasks.Event;
import tasks.Todo;

import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<Task>();
    }

    public TaskList(File f) {
        tasks = new ArrayList<Task>();
        final String SPLITTER = "\\|";
        final String TODO_IDENTIFIER = "T";
        final String EVENT_IDENTIFIER = "E";
        final String DEADLINE_IDENTIFIER = "D";
        final String YES = "Y";

        // read in past saved data
        try {
            Scanner fileScanner = new Scanner(f);
        
            while (fileScanner.hasNext()) {
                String lineInput = fileScanner.nextLine();
                String[] words = lineInput.split(SPLITTER, 4);
                Task newTask = new Task();

                switch (words[0]) {
                case TODO_IDENTIFIER:
                    newTask = new Todo(words[2]);
                    tasks.add(newTask);
                    break;

                case EVENT_IDENTIFIER:
                    newTask = new Event(words[2], words[3]);
                    tasks.add(newTask);
                    break;

                case DEADLINE_IDENTIFIER:
                    newTask = new Deadline(words[2], words[3]);
                    tasks.add(newTask);
                    break;

                default:
                    break;
                }

                if (words[1].equals(YES)) {
                    newTask.markDone();
                }
            }
            fileScanner.close();

        } catch (FileNotFoundException e) {
            System.out.println("error accessing file");
        } 
    }

    public ArrayList<Task> getTaskArray(){
        // will pass by reference
        return this.tasks;
    }

    public void listTasks() {
        String line = "\t------------------------\n";
        System.out.println(line);
        for (int i = 0; i < this.tasks.size(); i++) {
            int j = i + 1;
            System.out.println("\t"+ j + ". " + this.tasks.get(i) + "\n");
        }
        System.out.println(line);
    }

    public void addTask(Task latest) {
        this.tasks.add(latest);
    }

    public void deleteTask(int index) {
        this.tasks.remove(index);
    }

}
