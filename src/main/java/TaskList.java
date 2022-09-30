/**
 * TaskList class
 * Contains all the operations to be peformed on the collection of tasks
 * Functions include:
 * Add: adding a task
 * Delete: Deleting a task
 * Mark: Marking a task as done
 * Unmark: Marking a task as not done
 * Find: Finding all tasks with a specific word in them
 * handle stored tasks: Retrieving previously stored tasks in file
 */

import java.util.ArrayList;

public class TaskList {
    /**
     * Adds a task to the collection based on type: Todo, Deadline, or Event
     * if the command is not of a particular type (Todo, Deadline, or Event), the function throws an InvalidCommandException and asks for input again
     * in the end, a message is displayed to show that the task has been added
     * @param command specifies the type of task: Todo, Deadline, or Event
     * @param tasks is a reference to the collection on which operation needs to be performed (adding in this case)
     * @param inputText contains the user input from which the details of the task to be added is extracted
     *                  in the same format as  entered by the user in the CLI
     *                  in case of todo, it is just the description
     *                  if case of deadline, it contains description and /by
     *                  in case of event, it contains description and /at
     */
    public static void addTask(String command, ArrayList<Task> tasks, String inputText) {
        try {
            if (command.equals("todo")) {
                tasks.add(new Todo(inputText.substring(5)));
            } else if (command.equals("deadline")) {
                tasks.add(new Deadline(inputText.substring(9, inputText.indexOf('/')), inputText.substring(inputText.indexOf('/') + 4)));
            } else if (command.equals("event")) {
                tasks.add(new Event(inputText.substring(6, inputText.indexOf('/')), inputText.substring(inputText.indexOf('/') + 4)));
            } else {
                throw new InvalidCommandException();
            }
            UI.addMessage(inputText, tasks.size());

        } catch (InvalidCommandException e) {
            System.out.println("This is not a valid command.Please re-enter a valid command");
        }
    }

    /**
     * Deletes a task from the collection at a position specified by the user
     * in the end, a message is displayed to show that the task has been deleted
     * @param position specifies the position of the task which has to be deleted
     * @param tasks is a reference to the collection on which operation needs to be performed (deleting in this case)
     */
    public static void deleteTask(int position, ArrayList<Task> tasks){
        String str = tasks.get(position-1).toString();
        tasks.remove(position-1);
        UI.deleteMessage(str, tasks.size());
    }

    /**
     * Marks a task as done in the collection at a position specified by user
     * in the end, displays a message showing that the task has been marked as done
     * @param position specifies the position of the task which has to be marked as done
     * @param tasks is a reference to the collection on which operation needs to be performed (marking as done in this case)
     */
    public static void markTask(int position,ArrayList<Task> tasks ){
        tasks.get(position-1).markAsDone();
        UI.markMessage("mark",tasks.get(position-1).description,tasks.get(position-1).getStatusIcon() );
    }

    /**
     * Marks a task as not done in the collection at a position specified by user
     * in the end, displays a message showing that the task has been marked as not done
     * @param position specifies the position of the task which has to be marked as not done
     * @param tasks is a reference to the collection on which operation needs to be performed (marking as not done in this case)
     */
    public static void unmarkTask(int position,ArrayList<Task> tasks ){
        tasks.get(position-1).markAsUndone();
        UI.markMessage("unmark",tasks.get(position-1).description,tasks.get(position-1).getStatusIcon() );
    }

    /**
     * Finds all tasks in the collection which contain the word entered by the user
     * in the end, displays the list of lasts found which contain the word given by user
     * @param tasks is a reference to the collection on which operation needs to be performed (searching in this case)
     * @param word The word to be searched for
     *             in String format
     */
    public static void findTask(ArrayList<Task> tasks , String word){
        ArrayList<Task> tasksFound = new ArrayList<>();
        for(int i=0;i<tasks.size();i++){
            String str = tasks.get(i).description;
            if (str.contains(word)){
                tasksFound.add(tasks.get(i));
            }
        }
        UI.listMessage(tasksFound);
    }

    /**
     * Reads memory of any previously stored tasks in the data.txt file and loads it to the collection being used at runtime
     * The previously stored tasks are in String format and are parsed before adding to the current collection being used
     * Once the data is loaded to the collection, the rest of the task operations are carried out based on commands entered by the user
     * @param storedTasks is a reference to the ArrayList of tasks that were read from the data.txt file
     *                    each task is stored as a String
     *                    the tasks are in toString() format:[TASK_TYPE][STATUS] DESCRIPTION (DEADLINE_BY/EVENT_AT)
     * @param tasks is a reference to the collection to which the previous tasks need to be added.
     *              Once they are added, this collection is used for the rest of the runtime
     */
    public static void handleStoredTasks(ArrayList<String> storedTasks, ArrayList<Task> tasks){
        for (String str : storedTasks) {
            if (str.charAt(1) == 'T') {
                tasks.add(new Todo(str.substring(7)));
                if (str.charAt(4) == 'X') {
                    tasks.get(tasks.size() - 1).markAsDone();
                }
            }
            if (str.charAt(1) == 'D') {
                tasks.add(new Deadline(str.substring(7, str.indexOf('(')), str.substring(str.indexOf('(') + 4, str.indexOf(')'))));
                if (str.charAt(4) == 'X') {
                    tasks.get(tasks.size() - 1).markAsDone();
                }
            }
            if (str.charAt(1) == 'E') {
                tasks.add(new Event(str.substring(7, str.indexOf('(')), str.substring(str.indexOf('(') + 4, str.indexOf(')'))));
                if (str.charAt(4) == 'X') {
                    tasks.get(tasks.size() - 1).markAsDone();
                }
            }
        }
    }


}
