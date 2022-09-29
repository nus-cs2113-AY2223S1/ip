package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Represents the different operations that are able to be performed to the tasks list.
 */
public class TaskList {
    public String command;
    public String filePath;
    public ArrayList<Task> tasks;
    public int numberOfTasks;

    public String[] words;
    public String firstWord;

    UI ui = new UI();

    /**
     * Initializes the TaskList object.
     *
     * @param command User's input.
     * @param filePath Name of the file used to save tasks.
     * @param tasks Array used to store all the tasks.
     * @param numberOfTasks Number of tasks currently in the list.
     */
    public TaskList(String command, String filePath, ArrayList<Task> tasks, int numberOfTasks) {
        this.command = command;
        this.filePath = filePath;
        this.tasks = tasks;
        this.numberOfTasks = numberOfTasks;

        words = command.split(" ");
        firstWord = words[0];
    }

    /**
     * Get the updated tasks array used to store all the current tasks.
     *
     * @return Array used to store all the tasks.
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Get the updated number of tasks.
     *
     * @return Number of tasks currently in the list.
     */
    public int getNumberOfTasks() {
        return numberOfTasks;
    }

    /**
     * Creates a Task object and gives it an instance of Todo, Event, or Deadline
     * according to the user's input. Updates the text file to append a line corresponding to that Task object.
     *
     * @param command User's input.
     * @return Task object corresponding to the task created.
     * @throws DukeException If the description of the task is empty.
     */
    public Task createTask(String command) throws DukeException{
        //Handle empty description exception
        if (words.length == 1) {
            throw new DukeException();
        }

        Task newTask = new Task("");

        switch (firstWord) {
        case "todo" :
            newTask = new Todo(command.substring(5));
            break;
        case "deadline" :
            String deadlineWords[] = command.substring(8).split("/");
            newTask = new Deadline(deadlineWords[0].trim(), deadlineWords[1].substring(3));
            break;
        case "event" :
            String eventWords[] = command.substring(6).split("/");
            newTask = new Event(eventWords[0].trim(), eventWords[1].substring(3));
            break;
        }
        return newTask;
    }

    /**
     * Adds a Task object to the list of current tasks.
     *
     * @param currentTask Task object to be added to the list.
     * @param isReadFromFile Boolean to determine whether to print the UI message.
     *                       True if Task is read from file.
     *                       False if it has just been added by the user.
     */
    public void addTask(Task currentTask, boolean isReadFromFile) {
        tasks.add(currentTask);
        numberOfTasks++;

        if (!isReadFromFile) {
            ui.printDivider();
            System.out.println("\tGot it! (๑˃ᴗ˂)ﻭ I've added this task:");
            System.out.print("\t  ");
            System.out.println(currentTask);
            System.out.println("\tNow you have " + numberOfTasks + " task(s) in the list! 凸(￣ヘ￣)");
            System.out.println("");
            ui.printDivider();
        }
    }

    /**
     * Removes a Task object corresponding to the specified index from the list of current tasks.
     * Updates the text file to remove the line that corresponds to that Task object.
     *
     * @param index Position of the task to be removed from the list.
     */
    public void removeTask(int index) {
        try {
            Task removedTask = tasks.get(index);
            tasks.remove(index);
            numberOfTasks--;

            ui.printDivider();
            System.out.println("\tNoted! (๑˃ᴗ˂)ﻭ I've removed this task:");
            System.out.print("\t  ");
            System.out.println(removedTask);
            System.out.println("\tNow you have " + numberOfTasks + " task(s) in the list! 凸(￣ヘ￣)");
            System.out.println("");
            ui.printDivider();
        } catch (IndexOutOfBoundsException e) {
            ui.printIndexError();
        }
    }

    /**
     * Displays the list of current tasks to the user.
     */
    public void printList() {
        ui.printDivider();
        System.out.println("\tHere are the tasks in your list:");
        for (int i = 0; i < numberOfTasks; i++) {
            System.out.print("\t" + (i + 1) + ".");
            System.out.println(tasks.get(i));
        }
        System.out.println();
        ui.printDivider();
    }

    /**
     * Marks a certain Task object as done. Updates the text file to flag the line corresponding to that Task
     * object as done.
     *
     * @param index Position of the task to be marked as done.
     * @param isReadFromFile Boolean to determine whether to print the UI message.
     *                       True if Task is read from file.
     *                       False if it has just been added by the user.
     */
    public void markAsDone(String index, boolean isReadFromFile) {
        Storage storage = new Storage(filePath, tasks, numberOfTasks);

        try {
            Task currentTask = tasks.get(Integer.parseInt(index) - 1);
            currentTask.markAsDone();
            storage.rewriteFile();

            if (!isReadFromFile) {
                ui.printDivider();
                System.out.println("\tNice! (〃＾▽＾〃) I've marked this task as done:");
                System.out.println("\t  [" + currentTask.getStatusIcon() + "] " + currentTask.description);
                System.out.println("\tWell done completing your task, friend! (ﾉ◕ヮ◕)ﾉ*:･ﾟ✧");
                System.out.println("");
                ui.printDivider();
            }
        } catch (NullPointerException e) {
            ui.printIndexError();
        } catch (IOException e) {
            ui.printLoadingError();
        }
    }

    /**
     * Marks a certain Task object as undone. Updates the text file to flag the line corresponding to that Task
     * object as undone.
     *
     * @param index Position of the task to be marked as undone.
     * @param isReadFromFile Boolean to determine whether to print the UI message.
     *                       True if Task is read from file.
     *                       False if it has just been added by the user.
     */
    public void markAsUndone(String index, boolean isReadFromFile) {
        Storage storage = new Storage(filePath, tasks, numberOfTasks);

        try {
            Task currentTask = tasks.get(Integer.parseInt(index) - 1);
            currentTask.markAsUndone();
            storage.rewriteFile();

            if (!isReadFromFile) {
                ui.printDivider();
                System.out.println("\tOK, I've marked this task as not done yet:");
                System.out.println("\t  [" + currentTask.getStatusIcon() + "] " + currentTask.description);
                System.out.println("\tYou better stop procrastinating, friend.. (╥﹏╥)");
                System.out.println("");
                ui.printDivider();
            }
        } catch (NullPointerException e) {
            ui.printIndexError();
        } catch (IOException e) {
            ui.printLoadingError();
        }
    }

    /**
     * Find a task in the current tasks list that contains the text specified by the user.
     *
     * @param taskSubstring Substring specified by user.
     */
    public void findTask(String taskSubstring) {
        int currentIndex = 1;

        ui.printDivider();
        System.out.println("\t  Here are the matching tasks in your list:");

        for (Task task : tasks) {
            if (task.description.contains(taskSubstring)) {
                System.out.println("\t  " + currentIndex + "." + task);
                currentIndex++;
            }
        }

        ui.printDivider();
    }
}
