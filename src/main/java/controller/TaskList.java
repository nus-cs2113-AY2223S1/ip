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
        System.out.println("default constructor");
        tasks = new ArrayList<Task>();
    }

    public TaskList(File f) {
        tasks = new ArrayList<Task>();

        try {
            Scanner fileScanner = new Scanner(f);
        
            while (fileScanner.hasNext()) {
                String lineInput = fileScanner.nextLine();
                String[] words = lineInput.split("\\|", 4);

                switch (words[0]) {
                case "T":
                    Todo newTodo = new Todo(words[2]);
                    if (words[1].equals("Y")) {
                        newTodo.markDone();
                    }
                    tasks.add(newTodo);
                    break;

                case "E":
                    Event newEvent = new Event(words[2], words[3]);
                    if (words[1].equals("Y")) {
                        newEvent.markDone();
                    }
                    tasks.add(newEvent);
                    break;

                case "D":
                    Deadline newDeadline = new Deadline(words[2], words[3]);
                    if (words[1].equals("Y")) {
                        newDeadline.markDone();
                    }
                    tasks.add(newDeadline);
                    break;

                default:
                    break;
                }
            }
            fileScanner.close();

        } catch (FileNotFoundException e) {
            System.out.println("error accessing file");
        } 
    }

    public ArrayList<Task> getTaskArray(){
        // will pass by reference so the original one will be modified
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
}
