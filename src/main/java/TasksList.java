import java.util.ArrayList;

public class TasksList {
    protected static ArrayList<Task> tasksList = new ArrayList<>();

    public int getTasksListSize() {
        return tasksList.size();
    }

    public void printHorizontalLine() {
        System.out.println("____________________________________________________________");
    }

    public void printAddTaskText(Task task) {
        System.out.println("Got it. I've added this task:");
        System.out.println(task.taskStatusWithDescriptionText());
        System.out.println("Now you have " + getTasksListSize() + " tasks in the list.");
        printHorizontalLine();
    }

    public void addToTasksList(Task task) {
        tasksList.add(task);
        printAddTaskText(task);
    }

    public void printTaskList() throws EmptyArgumentException {
        if (getTasksListSize() == 0) {
            System.out.println("List is empty!!");
            throw new EmptyArgumentException();
        } else {
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < getTasksListSize(); i++) {
                System.out.println((i+1) + "." + tasksList.get(i).taskStatusWithDescriptionText());
            }
        }
        printHorizontalLine();
    }

    public void markTask(int taskNumber, String command, boolean isDone) throws EmptyArgumentException {
        String previousIcon = tasksList.get(taskNumber).getStatusIcon();
        if (taskNumber == 0) {
            throw new EmptyArgumentException();
        }
        if (previousIcon == "X") {
            if (isDone) {
                System.out.println("This task has already been marked!");
            } else {
                System.out.println("OK, I've marked this task as not done yet:");
            }
        } else {
            if (!isDone) {
                System.out.println("This task has already been unmarked!");
            } else {
                System.out.println("Nice! I've marked this task as done:");
            }
        }
        tasksList.get(taskNumber).setDone(isDone);
        String newIcon = tasksList.get(taskNumber).getStatusIcon();
        System.out.println("[" + newIcon + "] " + tasksList.get(taskNumber).description);
        printHorizontalLine();
    }

}