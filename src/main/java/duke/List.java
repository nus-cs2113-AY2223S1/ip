package duke;

import duke.task.Task;

public class List {
    public static final int arraySize = 100;
    private static int amountOfItems = 0;
    private static Task[] tasks;
    public static final String deadline = "deadline";
    public static final String todo = "todo";
    public static final String event = "event";
    public static final String by = "/by";
    public static final String at = "/at";
    public static final String space = " ";
    public static final String emptyString = "";

    public List() {
        tasks = new Task[arraySize];
    }

    public Task getTaskFromList(int index){
        return tasks[index];
    }


    public int getListSize() {
        return amountOfItems;
    }

    public void addTask(String input) {
        try{
            translateTask(input);
            printTaskAdded();
        } catch (DukeException e){
            Message.printError();
        }
    }

    public void printList() {
        Message.printHorizontalLine();
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < amountOfItems; i++) {
            System.out.print(i + 1 + ". ");
            System.out.println(tasks[i]);
        }
        Message.printHorizontalLine();
    }



    public void markItemDone(int i) {
        tasks[i - 1].markDone();
    }

    public void unmarkItemDone(int i) {
        tasks[i - 1].unmarkDone();
    }

    public void translateTask(String input) throws DukeException {
        String[] divideByFirstSpace = input.split(space, 2);
        if (divideByFirstSpace.length < 2 || divideByFirstSpace[1].equals(emptyString)){
            throw new DukeException();
        }
        switch (divideByFirstSpace[0]) {
            case deadline: {
                try {
                    String[] details = divideByFirstSpace[1].split(by);
                    AddDeadline(details[0].strip(),details[1].strip());
                } catch (ArrayIndexOutOfBoundsException e){
                    throw new DukeException();
                }
                break;
            }
            case event: {
                try {
                    String[] details = divideByFirstSpace[1].split(at);
                    AddEvent(details[0].strip(), details[1].strip());
                } catch (ArrayIndexOutOfBoundsException e){
                    throw new DukeException();
                }
                break;

            }
            case todo:
                AddTodo(divideByFirstSpace[1].strip());
                break;
            default:
                throw new DukeException();
        }

    }

    public static void AddDeadline(String description,String byDescription) throws DukeException {
        if (description.equals(emptyString) || byDescription.equals(emptyString)){
            throw new DukeException();
        }
        tasks[amountOfItems] = new duke.task.Deadline(description,byDescription);
        amountOfItems++;

    }

    public static void AddEvent(String description,String atDescription) throws DukeException {
        if (description.equals(emptyString) || atDescription.equals(emptyString)){
            throw new DukeException();
        }
        tasks[amountOfItems] = new duke.task.Event(description,atDescription);
        amountOfItems++;

    }

    public static void AddTodo(String description) {
        tasks[amountOfItems] = new duke.task.Todo(description);
        amountOfItems++;
    }

    public void printTaskAdded() {
        Message.printHorizontalLine();
        System.out.println("Got it. I've added this task:");
        System.out.println(tasks[amountOfItems - 1]);
        System.out.println("Now you have " + (amountOfItems) + " tasks in the list.");
        Message.printHorizontalLine();
    }

}
