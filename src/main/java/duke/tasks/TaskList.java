package duke.tasks;

import duke.DukeException;
import duke.ui.Ui;

import java.util.ArrayList;

public class TaskList {
    public static Ui ui = new Ui ();
    public static ArrayList<Task> taskList = new ArrayList<>();

    public void markDone (ArrayList<Task> taskList, int taskNumber) throws DukeException{

        ui.printOutputs("Okiii... This task has been marked as done");
        taskList.get(taskNumber).markAsDone();
        ui.printOutputs((taskList.get(taskNumber)).description);
        ui.printDashLine();
    }

    public void markUnDone(ArrayList<Task> taskList, int taskNumber) throws DukeException{
        ui.printOutputs("Okiii... This task has been marked as not done yet");
        taskList.get(taskNumber).markAsUndone();
        ui.printOutputs((taskList.get(taskNumber)).description);
        ui.printDashLine();
    }


    public void addTodo(ArrayList<Task> taskList, String task){
        taskList.add(new Todo(task));
        ui.printOutputs("Got it. I have added this task:");
        ui.printOutputs("Now you have " + taskList.size() + " tasks left");
        ui.printDashLine();
    }

    public void addDeadline(ArrayList<Task> taskList, String task, String deadline) {
        taskList.add(new Deadline(task, deadline));
        ui.printOutputs("Got it. I have added this task:");
        ui.printOutputs("Now you have " + taskList.size() + " tasks left");
        ui.printDashLine();

    }

    public void addEvent(ArrayList<Task> taskList, String task, String time){
        taskList.add(new Event(task, time));
        ui.printOutputs("Got it. I have added this task:");
        ui.printOutputs("Now you have " + taskList.size() + " tasks left");
        ui.printDashLine();
    }

    public void deleteTask(ArrayList<Task> taskList, int taskNumber){
        ui.printOutputs("Okiii... This task has been deleted: ");
        ui.printOutputs((taskList.get(taskNumber)).description);
        taskList.remove(taskNumber);
        ui.printOutputs("Now you have " + taskList.size() + " tasks left");
        ui.printDashLine();

    }

    public void listTasks (ArrayList<Task> taskList){
        ui.printOutputs("Here are the tasks in your list:");
        for (int i = 0; i < taskList.size(); i++) {
            ui.printOutputs(Integer.toString(i + 1) + " " + (taskList.get(i)).toString());
        }
        ui.printDashLine();
    }

    public void findTasks (ArrayList <Task> taskList, String keyword){
        ArrayList<Task> tasksFound = new ArrayList<>();
        ui.printOutputs("Here are the matching tasks in your list");
        for (int i = 0; i < taskList.size(); i++) {
            if (taskList.get(i).description.contains(keyword)){
                ui.printOutputs(Integer.toString(i + 1) + " " + (taskList.get(i)).toString());
            }
        }
        ui.printDashLine();
    }

}
