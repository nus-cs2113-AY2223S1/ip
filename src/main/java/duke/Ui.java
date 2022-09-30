package duke;

import java.util.ArrayList;

public class Ui {
    private final String GREETING_PART_ONE = "Hello! I'm Duke!";
    private final String GREETING_PART_TWO = "What can I do for you?";
    private final String GOODBYE = "Bye. Hope to see you again soon!";
    private final String NUMBER_OF_TASKS_MESSAGE = "You now have %d tasks";
    private final String TASK_ADDED_SUCCESSFULLY_MESSAGE = "Got it. I've added this task:";
    private final String EMPTY_TASK_LIST_MESSAGE = "You have no tasks in your list currently!";
    private final String LISTING_TASKS_MESSAGE = "Here are the tasks in your list:";
    private final String UPDATE_TASK_SUCCESS = "Nice! I've %sed this task:";
    private final String DELETE_TASK_SUCCESS = "Nice! I've deleted this task:";
    private final String LOAD_FILE_SUCCESS = "Nice! I've loaded your tasks from previous sessions!";
    private final String CREATE_DIRECTORY_FAILED = "Please create an empty directory named data in the folder containing ur .jar file!";
    public void printAddTaskSuccess(Task task){
        System.out.println(TASK_ADDED_SUCCESSFULLY_MESSAGE);
        task.printTask();
    }
    public void printTaskCount(int numberOfTasks){
        System.out.println(String.format(NUMBER_OF_TASKS_MESSAGE, numberOfTasks));
        printNewLine();
    }
    public void printTaskListEmpty(){
        System.out.println(EMPTY_TASK_LIST_MESSAGE);
        printNewLine();
    }
    public void printTaskListItems(ArrayList<Task> taskList){
        System.out.println(LISTING_TASKS_MESSAGE);
        for (int i = 0; i < taskList.size(); i++) {
            int listIndex = i + 1;
            Task taskToPrint = taskList.get(i);
            System.out.print(listIndex + ".");
            taskToPrint.printTask();
        }
        printNewLine();
    }
    public void printUpdateTaskSuccess(Task task, String userCommand){
        System.out.println(String.format(UPDATE_TASK_SUCCESS, userCommand));
        task.printTask();
        printNewLine();
    }
    public void printExceptionMessage(Exception e){
        System.out.println(e.getMessage());
        printNewLine();
    }
    public void printDeleteTaskSuccess(Task task){
        System.out.println(String.format(DELETE_TASK_SUCCESS));
        task.printTask();
    }
    public void printLoadFileSuccess(){
        System.out.println(LOAD_FILE_SUCCESS);
        printNewLine();
    }
    public void printCreateDirectoryFailure(){
        System.out.println(CREATE_DIRECTORY_FAILED);
        printNewLine();
    }
    public void printWelcome(){
        printNewLine();
        System.out.println(GREETING_PART_ONE);
        System.out.println(GREETING_PART_TWO);
        printNewLine();
    }
    public void printNewLine(){
        for (int i = 0; i < 60; i++) {
            System.out.print('_');
        }
        System.out.println();
    }
    public void printSearchResults(ArrayList<Task> results, String keyword){
        if (results.size() == 0){
            System.out.println("No task with description containing " + keyword + " is found");
            printNewLine();
        } else {
            System.out.println("Here are the search results for tasks with description containing " +keyword);
            for (Task task: results){
                task.printTask();
            }
            printNewLine();
        }
    }
    public void printGoodbye(){
        System.out.println(GOODBYE);
        printNewLine();
    }
}
