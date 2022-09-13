package duke;
public class List {
    public static final int arraySize = 100;
    private static int amountOfItems = 0;
    private static duke.task.Task[] tasks;
    public static final String deadline = "deadline";
    public static final String todo = "todo";
    public static final String event = "event";
    public static final String by = " /by ";
    public static final String at = " /at ";
    public static final String space = " ";

    public List() {
        tasks = new duke.task.Task[arraySize];
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
        if (divideByFirstSpace.length < 2 || divideByFirstSpace[1].equals("")){
            throw new DukeException();
        }
        switch (divideByFirstSpace[0]) {
            case deadline: {
                String[] details = divideByFirstSpace[1].split(by);
                tasks[amountOfItems] = new duke.task.Deadline(details[0], details[1]);
                break;
            }
            case event: {
                String[] details = divideByFirstSpace[1].split(at);
                tasks[amountOfItems] = new duke.task.Event(details[0], details[1]);
                break;
            }
            case todo:
                tasks[amountOfItems] = new duke.task.Todo(divideByFirstSpace[1]);
                break;
            default:
                throw new DukeException();
        }
    }

    public void printTaskAdded() {
        Message.printHorizontalLine();
        System.out.println("Got it. I've added this task:");
        System.out.println(tasks[amountOfItems]);
        System.out.println("Now you have " + (amountOfItems + 1) + " tasks in the list.");
        Message.printHorizontalLine();
        amountOfItems++;
    }

}
