package duke;

import java.util.ArrayList;

public class DukeController {
    protected ArrayList<Task> taskList = new ArrayList<Task>(0);
    private final String GREETING_PART_ONE = "Hello! I'm Duke!";
    private final String GREETING_PART_TWO = "What can I do for you?";
    private final String GOODBYE = "Bye. Hope to see you again soon!";
    private final String NUMBER_OF_TASKS_MESSAGE = "You now have %d tasks";
    private final String TASK_ADDED_SUCCESSFULLY_MESSAGE = "Got it. I've added this task:";
    private final String EMPTY_TASK_LIST_MESSAGE = "You have no tasks in your list currently!";
    private final String LISTING_TASKS_MESSAGE = "Here are the tasks in your list:";
    private final String UPDATE_TASK_SUCCESS = "Nice! I've %sed this task:";
    private final String ILLEGAL_INDEX_ERROR = ":( %d is out of bounds of the task list";
    private final String UPDATE_TASK_FAILURE = ":( This task is already %sed";
    private final String DELETE_TASK_SUCCESS = "Nice! I've deleted this task:";
    private String userInput;

    private final InputParser inputParser = new InputParser();

    public void getUserInput(){
        userInput = inputParser.readInput();
    }
    public void handleUserInput(){
        try {
            String[] parsedInput = inputParser.parseInput(userInput);
            String userCommand = parsedInput[0];
            switch(userCommand){
            case "bye":
                printGoodbye();
                Duke.exitDuke();
                return;
            case "list":
                printTaskList();
                break;
            case "todo":
            case "deadline":
            case "event":
                try {
                    String[] details = inputParser.parseTaskInformation(parsedInput[1], userCommand);
                    Task newTask = generateTask(userCommand, details);
                    addTask(newTask);
                } catch (IllegalInputException e) {
                    System.out.println(e.getErrorMessage());
                    printNewLine();
                }
                break;
            case "delete":
            case "mark":
            case "unmark":
                try {
                    int taskIndex = inputParser.parseTaskIndex(parsedInput[1], userCommand);
                    if (userCommand.equals("mark") || userCommand.equals("unmark")) {
                        updateTaskStatus(taskIndex, userCommand);
                    } else {
                        deleteTask(taskIndex);
                    }
                } catch (IllegalInputException e){
                    System.out.println(e.getErrorMessage());
                    printNewLine();
                }
                break;
            default:
                printNewLine();
            }
        } catch (IllegalInputException e) {
            System.out.println(e.getErrorMessage());
            printNewLine();

        }
    }
    public void printTaskList(){
        if (taskList.isEmpty()){
            System.out.println(EMPTY_TASK_LIST_MESSAGE);
            printNewLine();
        } else {
            System.out.println(LISTING_TASKS_MESSAGE);
            for (int i = 0; i < taskList.size(); i++) {
                int listIndex = i + 1;
                Task taskToPrint = taskList.get(i);
                System.out.print(listIndex + ".");
                taskToPrint.printTask();
            }
            printNewLine();
        }
    }

    public Task generateTask(String taskType, String[] details){
        switch (taskType){
        case "todo":
            return new ToDo(details[0]);
        case "deadline":
            return new Deadline(details[0], details[1]);
        case "event":
            return new Event(details[0], details[1]);
        default:
            return null;
        }
    }
    public void addTask(Task task){
        System.out.println(TASK_ADDED_SUCCESSFULLY_MESSAGE);
        task.printTask();
        taskList.add(task);
        printNumberOfTasks();
        printNewLine();
    }

    public void printNumberOfTasks(){
        System.out.println(String.format(NUMBER_OF_TASKS_MESSAGE, taskList.size()));
    }
    public void printNewLine(){
        for (int i = 0; i < 60; i++) {
            System.out.print('_');
        }
        System.out.println();
    }

    public void printWelcome(){
        printNewLine();
        System.out.println(GREETING_PART_ONE);
        System.out.println(GREETING_PART_TWO);
        printNewLine();
    }

    public void printGoodbye(){
        System.out.println(GOODBYE);
        printNewLine();
    }

    public void updateTaskStatus(int index, String userCommand) throws IllegalInputException{
        if (index > taskList.size() || index < 1){
            throw new IllegalInputException(String.format(ILLEGAL_INDEX_ERROR,index));
        }
        index--;
        boolean isDone = userCommand.equals("mark");
        Task task = taskList.get(index);
        if (isDone == task.isDone()){
            task.printTask();
            throw new IllegalInputException(String.format(UPDATE_TASK_FAILURE,userCommand));
        }
        task.setDone(isDone);
        System.out.println(String.format(UPDATE_TASK_SUCCESS, userCommand));
        task.printTask();
        printNewLine();
    }

    public void deleteTask(int index) throws IllegalInputException{
        if (index > taskList.size() || index < 1){
            throw new IllegalInputException(String.format(ILLEGAL_INDEX_ERROR,index));
        }
        index--;
        System.out.println(String.format(DELETE_TASK_SUCCESS));
        taskList.get(index).printTask();
        taskList.remove(index);
        printNewLine();
    };

    

}
