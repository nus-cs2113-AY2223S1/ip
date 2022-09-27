import duke.Deadline;
import duke.Event;
import duke.Task;
import duke.ToDo;

import java.util.ArrayList;
import java.util.Arrays;

public class TaskList {
    public ArrayList<Task> taskList;

    public TaskList(){
        taskList = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> loadedList){
        taskList = new ArrayList<>(loadedList);
    }

    public static void addTodo(ArrayList<Task> taskList, String[] userInputSplit, boolean isDone) throws EmptyDescriptionException {
        if (userInputSplit.length < 2){
            throw new EmptyDescriptionException();
        }
        String[] inputArrayWithoutType = Arrays.copyOfRange(userInputSplit,1, userInputSplit.length);
        String description = String.join(" ", inputArrayWithoutType);
        ToDo newToDo = new ToDo(description);
        newToDo.setIsDone(isDone);
        taskList.add(newToDo);
        Task.numOfTasks ++;
        Ui.printEchoInput(newToDo);
    }

    public static void addEvent(ArrayList<Task> taskList, String[] userInputSplit, boolean isDone) throws StringIndexOutOfBoundsException{

        String[] inputArrayWithoutType = Arrays.copyOfRange(userInputSplit, 1, userInputSplit.length);
        String inputWithoutType = String.join(" ", inputArrayWithoutType);
        if (inputWithoutType.length() < 3){
            throw new StringIndexOutOfBoundsException();
        }
        String description = inputWithoutType.split(" /at ")[0];
        String time = inputWithoutType.split(" /at ")[1];
        Event newEvent = new Event(description, time);
        newEvent.setIsDone(isDone);
        taskList.add(newEvent);
        Task.numOfTasks ++;
        Ui.printEchoInput(newEvent);
    }
    // add exception

    public static void addDeadline(ArrayList<Task> taskList, String[] userInputSplit, boolean isDone) {
        String[] inputArrayWithoutType = Arrays.copyOfRange(userInputSplit,1, userInputSplit.length);
        String inputWithoutType = String.join(" ", inputArrayWithoutType);
        String description = inputWithoutType.split(" /by ")[0];
        String deadline = inputWithoutType.split(" /by ")[1];
        Deadline newDeadline = new Deadline(description, deadline);
        taskList.add(newDeadline);
        newDeadline.setIsDone(isDone);
        Task.numOfTasks ++;
        Ui.printEchoInput(newDeadline);
    }
    // add exception

    public static void findTask(ArrayList<Task> taskList, String[] userInputSplit){
        String[] contentSpilt = Arrays.copyOfRange(userInputSplit,1, userInputSplit.length);
        String Content = String.join(" ", contentSpilt);
        System.out.println("    ____________________________________________________________");
        System.out.println("     Here are the matching tasks in your list:");
        int index = 1;
        for (int i=0; i<Task.numOfTasks; i++){
            Task currTask = taskList.get(i);
            if (currTask.getDescription().contains(Content)){
                System.out.println("     " + index + "." + currTask);
                index ++;
            }
        }
        System.out.println("    ____________________________________________________________");
    }

    public static void deleteTask(ArrayList<Task> taskList, String[] userInputSplit) {
        int deleteIndex = Integer.parseInt(userInputSplit[1]) - 1;
        System.out.println("    ____________________________________________________________");
        System.out.println("     Noted. I've removed this task:");
        System.out.println(String.format("       %s", taskList.get(deleteIndex).toString()));
        taskList.remove(deleteIndex);
        Task.numOfTasks --;
        System.out.println("     Now you have " + Task.numOfTasks + " tasks in the list.");
        System.out.println("    ____________________________________________________________");
    }

    public static void printTaskList(ArrayList<Task> taskList, int count){
        System.out.println("    ____________________________________________________________");
        System.out.println("     Here are the tasks in your list:");
        for (int i=0; i<count; i++){
            Task currTask = taskList.get(i);

            int index = i + 1;
            System.out.println("     " + index + "." + currTask.toString());
        }
        System.out.println("    ____________________________________________________________");
    }

    public static void markTask(ArrayList<Task> taskList, String[] userInputSplit) {
        int markDoneIndex = Integer.parseInt(userInputSplit[1]) - 1;
        taskList.get(markDoneIndex).setIsDone(true);
        Ui.printSetDoneMessage(taskList.get(markDoneIndex));
    }

    public static void unmarkTask(ArrayList<Task> taskList, String[] userInputSplit) {
        int markNotDoneIndex = Integer.parseInt(userInputSplit[1]) - 1;
        taskList.get(markNotDoneIndex).setIsDone(false);
        Ui.printSetNotDoneMessage(taskList.get(markNotDoneIndex));
    }


}
