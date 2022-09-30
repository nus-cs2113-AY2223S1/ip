package duke.tasks;

import duke.DukeException;
import duke.ui.Ui;

import java.util.ArrayList;

/**
 * Represents and manages all the tasks that the user has added.
 */
public class TaskList {
    public static Ui ui = new Ui ();
    public static ArrayList<Task> taskList = new ArrayList<>();


    /**
     * Marks the task at the given index from the taskList as done.
     *
     * @param taskList the arraylist that stores all the tasks that the user has inputted.
     * @param taskNumber the index of the task that the user wants to mark as done.
     */
    public void markDone (ArrayList<Task> taskList, int taskNumber){

        ui.printOutputs("Okiii... This task has been marked as done");
        taskList.get(taskNumber).markAsDone();
        ui.printOutputs((taskList.get(taskNumber)).description);
        ui.printDashLine();
    }

    /**
     * Marks the task at the given index from the taskList as undone.
     *
     * @param taskList the arraylist that stores all the tasks that the user has inputted.
     * @param taskNumber the index of the task that the user wants to mark as not done.
     */
    public void markUnDone(ArrayList<Task> taskList, int taskNumber){
        ui.printOutputs("Okiii... This task has been marked as not done yet");
        taskList.get(taskNumber).markAsUndone();
        ui.printOutputs((taskList.get(taskNumber)).description);
        ui.printDashLine();
    }

    /**
     * adds a new Event to the taskList.
     *
     * @param taskList the arraylist that stores all the tasks that the user has inputted.
     * @param task the description of the new todo task.
     *
     */

    public void addTodo(ArrayList<Task> taskList, String task){
        Todo t = new Todo(task);
        taskList.add(t);
        ui.printOutputs("Got it. I have added this task:");
        ui.printOutputs(t.toString());
        ui.printOutputs("Now you have " + taskList.size() + " tasks left");
        ui.printDashLine();
    }

    /**
     * adds a new Deadline to the taskList.
     *
     * @param taskList the arraylist that stores all the tasks that the user has inputted.
     * @param task the description of the new deadline.
     * @param deadline the time of the new deadline task.
     *
     */
    public void addDeadline(ArrayList<Task> taskList, String task, String deadline) {
        Deadline d = new Deadline(task, deadline);
        taskList.add(d);
        ui.printOutputs("Got it. I have added this task:");
        ui.printOutputs(d.toString());
        ui.printOutputs("Now you have " + taskList.size() + " tasks left");
        ui.printDashLine();

    }

    /**
     * adds a new Event to the taskList.
     *
     * @param taskList the arraylist that stores all the tasks that the user has inputted.
     * @param task the description of the new event.
     * @param time the time of the new event.
     *
     */
    public void addEvent(ArrayList<Task> taskList, String task, String time){
        Event e = new Event(task, time);
        taskList.add(e);
        ui.printOutputs("Got it. I have added this task:");
        ui.printOutputs(e.toString());
        ui.printOutputs("Now you have " + taskList.size() + " tasks left");
        ui.printDashLine();
    }

    /**
     * Deletes the task at the given index from the taskList.
     *
     * @param taskList the arraylist that stores all the tasks that the user has inputted.
     * @param taskNumber the index of the task that the user wants delete.
     */
    public void deleteTask(ArrayList<Task> taskList, int taskNumber){
        ui.printOutputs("Okiii... This task has been deleted: ");
        ui.printOutputs((taskList.get(taskNumber)).toString());
        taskList.remove(taskNumber);
        ui.printOutputs("Now you have " + taskList.size() + " tasks left");
        ui.printDashLine();

    }

    /**
     * Lists all the tasks in the taskList.
     *
     * @param taskList the arraylist that stores all the tasks that the user has inputted.
     *
     */
    public void listTasks (ArrayList<Task> taskList){
        ui.printOutputs("Here are the tasks in your list:");
        for (int i = 0; i < taskList.size(); i++) {
            ui.printOutputs((i + 1) + " " + (taskList.get(i)).toString());
        }
        ui.printDashLine();
    }

    /**
     * Finds the tasks which contain the keyword from the user's input.
     * Checks if each task in the taskList contains the keyword and print out the ones that do.
     *
     * @param taskList the arraylist that stores all the tasks that the user has inputted.
     * @param keyword the word to be searched in the tasks.
     *
     */
    public void findTasks (ArrayList <Task> taskList, String keyword){
        ui.printOutputs("Here are the matching tasks in your list");
        for (int i = 0; i < taskList.size(); i++) {
            if (taskList.get(i).description.contains(keyword)){
                ui.printOutputs((i + 1) + " " + (taskList.get(i)).toString());
            }
        }
        ui.printDashLine();
    }

}
