import java.util.ArrayList;

public class DukeController {
    protected ArrayList<Task> taskList = new ArrayList<Task>(0);
    private final int FIRST_LEVEL_INDENT = 1;
    private final int SECOND_LEVEL_INDENT = 2;
    private String userInput;
    private final InputParser inputParser = new InputParser();

    public void getUserInput(){
        userInput = inputParser.readInput();
    }
    public void handleUserInput(){
        String[] parsedInput = inputParser.parseInput(userInput);
        String userCommand = parsedInput[0];
        boolean isDone = false;
        switch(userCommand){
        case "list":
            printTaskList();
            break;
        case "todo":
        case "deadline":
        case "event":
            String[] details = inputParser.parseTaskInformation(parsedInput[1]);
            Task newTask = generateTask(userCommand, details);
            addTask(newTask);
            break;
        case "bye":
            printGoodbye();
            break;
        case "mark":
            isDone = true;
        case "unmark":
            int taskIndex = inputParser.parseTaskIndex(parsedInput[1]);
            updateTaskStatus(taskIndex,isDone);
            break;
        default:
            printNewLine();
        }
    }
    public void printTaskList(){
        printIndentation(FIRST_LEVEL_INDENT);
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < taskList.size(); i++){
            int listIndex = i+1;
            Task taskToPrint = taskList.get(i);
            printIndentation(FIRST_LEVEL_INDENT);
            System.out.print(listIndex+".");
            taskToPrint.printTask();
        }
        printNewLine();
    }

    public Task generateTask(String taskType, String[] details){
        switch (taskType){
        case "todo":
            return new ToDo(details[0]);
        case "deadline":
            return new Deadline(taskType, details[1]);
        case "event":
            return new Event(taskType, details[1]);
        default:
            return null;
        }
    }
    public void addTask(Task task){
        printIndentation(FIRST_LEVEL_INDENT);
        System.out.println("Got it. I've added this task:");
        printIndentation(SECOND_LEVEL_INDENT);
        task.printTask();
        taskList.add(task);
        printIndentation(FIRST_LEVEL_INDENT);
        printNumberOfTasks();
        printNewLine();
    }

    public void printNumberOfTasks(){
        System.out.println("You now have " + taskList.size() + " tasks");
    }
    public void printNewLine(){
        for (int i = 0; i < 60; i++) {
            System.out.print('_');
        }
        System.out.println();
    }

    public void printWelcome(){
        printNewLine();
        printIndentation(FIRST_LEVEL_INDENT);
        System.out.println("Hello! I'm Duke");
        printIndentation(FIRST_LEVEL_INDENT);
        System.out.println("What can I do for you?");
        printNewLine();
    }

    public void printGoodbye(){
        printIndentation(FIRST_LEVEL_INDENT);
        System.out.println("Bye. Hope to see you again soon!");
        printNewLine();
    }

    public void updateTaskStatus(int index, boolean isDone){
        index--;
        Task task = taskList.get(index);
        task.updateStatus(isDone);
        printIndentation(FIRST_LEVEL_INDENT);
        if (isDone){
            System.out.println("Nice! I've marked this task as done:");
        } else {
            System.out.println("OK, I've marked this task as not done yet:");
        }
        printIndentation(SECOND_LEVEL_INDENT);
        task.printTask();
        printNewLine();
    }

    public void printIndentation(int spaces){
        for (int i = 0; i < spaces; i++){
            System.out.print(' ');
        }
    }

}
