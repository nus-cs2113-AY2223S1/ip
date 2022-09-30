package duke;

import java.util.ArrayList;

/**
 * Class to handle user interface.
 */
public class Ui {
    private final String TASK_ADDED_SUCCESSFULLY_MESSAGE = "Got it. I've added this task:";
    private final String NUMBER_OF_TASKS_MESSAGE = "You now have %d tasks";
    private final String EMPTY_TASK_LIST_MESSAGE = "You have no tasks in your list currently!";
    private final String LISTING_TASKS_MESSAGE = "Here are the tasks in your list:";
    private String UPDATE_TASK_SUCCESS = "Nice! I've %sed this task:";
    private final String DELETE_TASK_SUCCESS = "Nice! I've deleted this task:";
    private final String GREETING_PART_ONE = "Hello! I'm Duke!";
    private final String GREETING_PART_TWO = "What can I do for you?";
    private final String NO_TASK_FOUND = "No task with description containing %s is found";
    private final String TASKS_FOUND = "Here are the search results for tasks with description containing %s";
    private final String GOODBYE = "Bye. Hope to see you again soon!";
    private final String SAVED_FILE_LOCATION_MESSAGE = "Your saved file is located at";

    /**
     * Prints success message after adding task and the added task.
     *
     * @param task Newly added task.
     */
    public void printAddTaskSuccess(Task task){
        System.out.println(TASK_ADDED_SUCCESSFULLY_MESSAGE);
        task.printTask();
    }

    public void printSavedFileLocation(String filePath){
        System.out.println(SAVED_FILE_LOCATION_MESSAGE + " " +filePath);
    }


    /**
     * Prints the number of tasks in taskList.
     *
     * @param numberOfTasks Number of tasks in taskList.
     */
    public void printTaskCount(int numberOfTasks){
        System.out.println(String.format(NUMBER_OF_TASKS_MESSAGE, numberOfTasks));
    }

    public void printTaskListEmpty(){
        System.out.println(EMPTY_TASK_LIST_MESSAGE);
    }

    /**
     * Iterates through and prints the tasks in taskList with index labels.
     *
     * @param taskList ArrayList of tasks to print.
     */
    public void printTaskListItems(ArrayList<Task> taskList){
        System.out.println(LISTING_TASKS_MESSAGE);
        for (int i = 0; i < taskList.size(); i++) {
            int listIndex = i + 1;
            Task taskToPrint = taskList.get(i);
            System.out.print(listIndex + ".");
            taskToPrint.printTask();
        }
    }

    /**
     * Prints success message after updating task and the updated task.
     *
     * @param task Updated task.
     * @param userCommand Update action.
     */
    public void printUpdateTaskSuccess(Task task, String userCommand){
        System.out.println(String.format(UPDATE_TASK_SUCCESS, userCommand));
        task.printTask();
    }

    public void printExceptionMessage(Exception e){
        System.out.println(e.getMessage());
    }

    /**
     * Prints success message after deleting task and the deleted task.
     *
     * @param task Deleted task.
     */
    public void printDeleteTaskSuccess(Task task){
        System.out.println(String.format(DELETE_TASK_SUCCESS));
        task.printTask();
    }
    public void printWelcome(){
        System.out.println(GREETING_PART_ONE);
        System.out.println(GREETING_PART_TWO);
    }
    public void printNewLine(){
        for (int i = 0; i < 60; i++) {
            System.out.print('_');
        }
        System.out.println();
    }

    /**
     * Prints the results found from taskList with task description containing keyword.
     * Prints no task found if results is empty.
     *
     * @param results Search result.
     * @param keyword Search keyword.
     */
    public void printSearchResults(ArrayList<Task> results, String keyword){
        if (results.size() == 0){
            System.out.println(String.format(NO_TASK_FOUND, keyword));
        } else {
            System.out.println(String.format(TASKS_FOUND, keyword));
            for (Task task: results){
                task.printTask();
            }
        }
    }
    public void printGoodbye(){
        System.out.println(GOODBYE);
    }
}
