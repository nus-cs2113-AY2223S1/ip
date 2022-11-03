import java.util.ArrayList;

/**
 * contains the task list and all the operations it has to do with it
 */
public class TaskList {
    Ui ui = new Ui();
    String taskString;
    static ArrayList<Task> todoList = new ArrayList<>();

    public TaskList(ArrayList<Task> todoList) {
        this.todoList = todoList;
    }

    /**
     * prints all the tasks in the list
     */
    public void printTasks() {
        ui.printLines();
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < todoList.size(); i++) {
            System.out.println("\t" + (i + 1) + ") " + todoList.get(i).toString());
        }
        ui.printLines();
    }

    /**
     * marks a task as done
     * @param markedValue
     */
    public void markAsDone(int markedValue) {
        todoList.get(markedValue).markAsDone();
        ui.printLines();
        System.out.println("Nice! I've marked this task as done:");
        printMarkedTask(markedValue);
        ui.printLines();
    }

    /**
     * prints the currently marked tasks
     * @param markedValue
     */
    public void printMarkedTask(int markedValue) {
        System.out.println(todoList.get(markedValue).getStatusIcon() + todoList.get(markedValue).getDescription());
    }

    /**
     * marks a task as undone
     * @param markedValue
     */
    public void markAsUndone(int markedValue) {
        todoList.get(markedValue).unMark();
        ui.printLines();
        System.out.println("OK, I've marked this task as not done yet:");
        printUnmarkedTask(markedValue);
        ui.printLines();
    }

    /**
     * prints the unmarked task
     * @param markedValue
     */
    public void printUnmarkedTask(int markedValue) {
        System.out.println(todoList.get(markedValue).getStatusIcon() + todoList.get(markedValue).getDescription());
    }

    /**
     * adds a new tasks to the list
     * @param newTask
     */
    public void addNewTask(Task newTask) {
        todoList.add(newTask);
        ui.printLines();
        System.out.println("Got it. I've added this task: ");
        getListSize();

        taskString = todoList.size() == 1 ? " task " : " tasks ";

        System.out.println("Now you have " + todoList.size() + taskString + "in the list.");
        ui.printLines();
    }

    /**
     * gets the list size
     */
    public void getListSize() {
        System.out.println(todoList.get(todoList.size() - 1).toString());
    }

    /**
     * prints the removed task
     * @param markedValue
     */
    public void printRemovedTask(int markedValue) {
        System.out.println(todoList.get(markedValue).toString());
    }

    /**
     * deletes a task from the list
     * @param markedValue
     */
    public void deleteFromList(int markedValue) {
        ui.printLines();
        System.out.println("Noted. I've removed this task: ");
        printRemovedTask(markedValue);

        taskString = todoList.size() == 2 ? " task " : " tasks ";

        System.out.println("Now you have " + (todoList.size() - 1) + taskString + "in the list.");
        ui.printLines();
        todoList.remove(markedValue);
    }

    /**
     * finds the tasks that match the description
     * @param userInputNewValue
     */
    public void findTasks(String userInputNewValue) {
        int counter = 1;
        System.out.println("I have found these tasks:");
        for (Task currentTask: todoList){
            String currentDescription = currentTask.getDescription();
            if (currentDescription.contains(userInputNewValue.trim())){
                System.out.println(counter + "." + currentTask.toString());
                counter++;
            }
        }
    }
}
