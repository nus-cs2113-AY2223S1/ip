package duke.task;

import duke.exceptions.AccessTaskOutOfBoundsException;
import duke.exceptions.EmptyDescriptionException;
import duke.exceptions.MissingTaskNumberException;
import duke.exceptions.UnknownCommandException;

public class TaskManager {
    private Task[] tasks = new Task[100];
    private int tasksCount = 0;

    public void printAcknowledgement() {
        String acknowledgement = "____________________________________________________________\n"
                + "Got it. I've added this task: \n"
                + " " + tasks[tasksCount].toString();
        System.out.println(acknowledgement);
        this.tasksCount++;
        System.out.println("Now you have " + this.tasksCount + " task(s) in the list.");
        System.out.println("____________________________________________________________");
    }

    public String assembleTaskDescription(String[] text) {
        String description = text[1]; //initialise with first word of task
        for (int i = 2; i < text.length; i++) { //start loop from second word of task
            description = description + " " + text[i];
        }
        return description;
    }

    public void handleTaskWithTime(String type, String description) {
        String[] text = description.split(" ");
        String task = text[0]; //initialise with first word of task
        int indexOfTime = 0;
        for (int i = 1; i < text.length; i++) { //start loop from second word of task
            if (text[i].contains("/")) {
                indexOfTime = i;
                break;
            }
            task = task + " " + text[i];
        }
        indexOfTime++;
        String time = "";
        for (int i = indexOfTime; i < text.length; i++) {
            time = time + " " + text[i];
        }
        if (type.equals("deadline")) {
            tasks[tasksCount] = new Deadline(task, time);
        } else if (type.equals("event")) {
            tasks[tasksCount] = new Event(task, time);
        }
    }

    public void handleTask(String type, String description) {
        if (type.equals("todo")) {
            tasks[tasksCount] = new Todo(description);
        } else {
            this.handleTaskWithTime(type, description);
        }
        this.printAcknowledgement();
    }
    public void addTask(String type, String curr) throws EmptyDescriptionException, UnknownCommandException {
        if (!(type.equals("todo") || type.equals("deadline") || type.equals("event"))) { //type given is invalid
            throw new UnknownCommandException();
        } else {
            String[] text = curr.split(" ");
            if (text.length == 1) { //description is empty
                throw new EmptyDescriptionException();
            } else {
                String description = assembleTaskDescription(text);
                handleTask(type, description);
            }
        }
    }

    public void handleInput(String curr) {
        String[] text = curr.split(" ");
        String type = text[0];
        if (type.equals("mark")) {
            try {
                this.handleMarkAsDone(curr);
            } catch (MissingTaskNumberException e) {
                e.printMissingTaskNumberError();
            }
        } else if (type.equals("unmark")) {
            try {
                this.handleMarkAsUndone(curr);
            } catch (MissingTaskNumberException e) {
                e.printMissingTaskNumberError();
            }
        } else {
            try {
                this.addTask(type, curr);
            } catch (EmptyDescriptionException e) {
                e.printEmptyDescriptionError();
            } catch (UnknownCommandException e){
                e.printUnknownCommandError();
            }
        }
    }

    public void printNumberOfTasks() {
        System.out.println("You currently have " + this.tasksCount + " task(s) in your list.\n"
                + "    ____________________________________________________________");
    }
    public void listTasks(){
        System.out.println("____________________________________________________________");
        System.out.println("Here are the tasks in your list:");
        for(int i = 0; i < tasksCount; i++){
            System.out.println((i+1) + "." + tasks[i].toString());
        }
        System.out.println("____________________________________________________________");
    }

    public void handleMarkAsDone(String curr) throws MissingTaskNumberException {
        String[] text = curr.split(" ");
        if (text.length == 1) { //did not specify task number
            throw new MissingTaskNumberException();
        } else {
            int taskNumber = Integer.parseInt(text[1]);
            try {
                markAsDone(taskNumber);
            } catch (AccessTaskOutOfBoundsException e) {
                e.printAccessTaskOutOfBoundsError();
                printNumberOfTasks();
            }
        }
    }

    public void markAsDone(int taskNumber) throws AccessTaskOutOfBoundsException {
        if (taskNumber > this.tasksCount || taskNumber < 0) { //task specified is out of bounds
            throw new AccessTaskOutOfBoundsException();
        } else {
            int taskIndex = taskNumber - 1;
            tasks[taskIndex].isDone = true;
            System.out.println("Nice! I've marked this task as done:");
            System.out.println(tasks[taskIndex].toString());
            System.out.println("____________________________________________________________");
        }
    }

    public void handleMarkAsUndone(String curr) throws MissingTaskNumberException {
        String[] text = curr.split(" ");
        if (text.length == 1) { //did not specify task number
            throw new MissingTaskNumberException();
        } else {
            int taskNumber = Integer.parseInt(text[1]);
            try {
                markAsUndone(taskNumber);
            } catch (AccessTaskOutOfBoundsException e) {
                e.printAccessTaskOutOfBoundsError();
                printNumberOfTasks();
            }
        }
    }

    public void markAsUndone(int taskNumber) throws AccessTaskOutOfBoundsException{
        if (taskNumber > this.tasksCount || taskNumber < 0) { //task specified is out of bounds
            throw new AccessTaskOutOfBoundsException();
        } else {
            int taskIndex = taskNumber - 1;
            tasks[taskIndex].isDone = false;
            System.out.println("OK, I've marked this task as not done yet:");
            System.out.println(tasks[taskIndex].toString());
            System.out.println("____________________________________________________________");
        }
    }
}
