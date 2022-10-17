package Duke;

import java.io.IOException;
import java.util.Scanner;

public class UI {
    Messages messages = new Messages();
    Storage storage = new Storage();
    Parser parser = new Parser();
    DukeException dukeException = new DukeException();

    /**
     * Prints the welcome message
     */
    public void welcomeMessage() {
        messages.welcomeMessage();
    }

    /**
     * Lists all the tasks
     *
     * @param taskList TaskList object containing all the task objects
     */
    public void list(TaskList taskList) {
        System.out.println(messages.lineBreak);
        System.out.println(messages.listMessage);
        for (int i = 0; i < (taskList.noOfTasks); i++) {
            System.out.print(i + 1 + ".");
            displayOneTask(i, taskList);
        }
        System.out.println(messages.lineBreak);
    }

    /**
     * Prints one task in the given format
     *
     * @param taskNo   the task to be printed
     * @param taskList TaskList object containing all the task objects
     */
    public void displayOneTask(int taskNo, TaskList taskList) {
        System.out.print(taskList.tasks.get(taskNo).getType());
        if (taskList.tasks.get(taskNo).isMark()) {
            System.out.print(messages.marked);
        } else {
            System.out.print(messages.unmarked);
        }
        System.out.print(taskList.tasks.get(taskNo).getDescription());
        if (taskList.tasks.get(taskNo).getType().equals("[E]")) {
            System.out.println(" (at: " + taskList.tasks.get(taskNo).getTime() + ")");
        } else if (taskList.tasks.get(taskNo).getType().equals("[D]")) {
            System.out.println(" (by: " + taskList.tasks.get(taskNo).getTime() + ")");
        } else {
            System.out.println("");
        }
    }

    /**
     * Adds event task with the requisite messages
     *
     * @param description
     * @param at
     * @param isMark
     * @param taskList
     * @throws IOException
     */
    public void addEvent(String description, String at, boolean isMark, TaskList taskList) throws IOException {
        System.out.println(messages.lineBreak);
        System.out.println(messages.initialAddTaskMessage);
        taskList.addEvent(description, at, isMark);
        displayOneTask(taskList.noOfTasks - 1, taskList);
        System.out.println("Now you have " + (taskList.noOfTasks) + taskOrTasks(taskList.noOfTasks) + "in the list.");
        System.out.println(messages.lineBreak);
        storage.writeAllEvents(taskList);
    }

    /**
     * Adds todo task with the requisite messages
     *
     * @param description
     * @param isMark
     * @param taskList
     * @throws IOException
     */
    public void addTodo(String description, boolean isMark, TaskList taskList) throws IOException {
        System.out.println(messages.lineBreak);
        System.out.println(messages.initialAddTaskMessage);
        taskList.addTodo(description, isMark);
        displayOneTask(taskList.noOfTasks - 1, taskList);
        System.out.println("Now you have " + (taskList.noOfTasks) + taskOrTasks(taskList.noOfTasks) + "in the list.");
        System.out.println(messages.lineBreak);
        storage.writeAllEvents(taskList);
    }

    /**
     * Adds deadline tasks with the requisite messages
     *
     * @param description
     * @param by
     * @param isMark
     * @param taskList
     * @throws IOException
     */
    public void addDeadline(String description, String by, boolean isMark, TaskList taskList) throws IOException {
        System.out.println(messages.lineBreak);
        System.out.println(messages.initialAddTaskMessage);
        taskList.addDeadline(description, by, isMark);
        displayOneTask(taskList.noOfTasks - 1, taskList);
        System.out.println("Now you have " + (taskList.noOfTasks) + taskOrTasks(taskList.noOfTasks) + "in the list.");
        System.out.println(messages.lineBreak);
        storage.writeAllEvents(taskList);
    }

    /**
     * Accepts commands from the user
     *
     * @param taskList TaskList object we are working with
     * @param storage  Storage object we are working with
     * @throws IOException
     */

    public void acceptCommands(TaskList taskList, Storage storage) throws IOException {
        Scanner SC = new Scanner(System.in);
        String input;
        String type;
        while (true) {
            input = SC.nextLine();
            type = parser.getType(input);
            if (type.equals("bye")) {
                System.out.println(messages.lineBreak);
                System.out.println(messages.bye);
                System.out.println(messages.lineBreak);
                storage.writeAllEvents(taskList);
                break;
            } else if (type.equals("list")) {
                list(taskList);
            }
            insertBasedOnType(type, input, taskList);
        }
    }

    /**
     * Inserts one task into tasklist, based on its type
     *
     * @param type     type of task
     * @param input    input string from user
     * @param taskList
     * @throws IOException
     */
    public void insertBasedOnType(String type, String input, TaskList taskList) throws IOException {
        String time;
        String description;
        if (type.equals("")) {
            dukeException.notRecognizedException();
        } else if (type.equals("event")) {
            description = parser.getEventDescription(input);
            time = parser.getEventTime(input);
            if (description.equals("") || time.equals("")) {
                dukeException.eventException();
            } else {
                addEvent(description, time, false, taskList);
            }
        } else if (type.equals("todo")) {
            description = parser.getTodoDescription(input);
            if (description.equals("")) {
                dukeException.todoException();
            } else {
                addTodo(description, false, taskList);
            }
        } else if (type.equals("deadline")) {
            description = parser.getDeadlineDescription(input);
            time = parser.getDeadlineTime(input);
            if (description.equals("") || time.equals("")) {
                dukeException.deadlineException();
            } else {
                addDeadline(description, time, false, taskList);
            }
        } else if (type.equals("mark")) {
            markTask(input, taskList);
        } else if (type.equals("unmark")) {
            unmarkTask(input, taskList);
        } else if (type.equals("delete")) {
            deleteTask(input, taskList);
        } else if (type.equals("find")) {
            findTask(input, taskList);
        }
    }

    /**
     * Finds task in list, based on search string
     *
     * @param input    search string from user
     * @param taskList
     */
    public void findTask(String input, TaskList taskList) {
        String search = parser.findSearchString(input);
        if (search.equals("")) {
            new DukeException().findException();
        } else {
            System.out.println(messages.lineBreak);
            System.out.println(messages.findMessage);
            for (int i = 0; i < taskList.noOfTasks; i++) {
                if (taskList.findTask(i, search)) {
                    System.out.print(i + 1 + ".");
                    displayOneTask(i, taskList);
                }
            }
            System.out.println(messages.lineBreak);
        }
    }

    /**
     * Delete task no entered by user
     *
     * @param input    string entered by user
     * @param taskList
     * @throws IOException
     */
    public void deleteTask(String input, TaskList taskList) throws IOException {
        int taskNo = parser.findTaskNo(input);
        if (taskNo <= 0 || taskNo > taskList.noOfTasks) {
            dukeException.deleteException();
        } else {
            System.out.println(messages.lineBreak);
            System.out.println(messages.deleteMessage);
            displayOneTask(taskNo - 1, taskList);
            taskList.delete(taskNo - 1);
            System.out.println("Now you have " + taskList.noOfTasks + taskOrTasks(taskList.noOfTasks) + "in the list.");
            System.out.println(messages.lineBreak);
            storage.writeAllEvents(taskList);
        }
    }

    /**
     * Marks task no entered by user
     *
     * @param input    string entered by user
     * @param taskList
     * @throws IOException
     */
    public void markTask(String input, TaskList taskList) throws IOException {
        int taskNo = parser.findTaskNo(input);
        if (taskNo <= 0 || taskNo > taskList.noOfTasks) {
            dukeException.markException();
        } else {
            taskList.mark(taskNo - 1, true);
            System.out.println(messages.lineBreak);
            System.out.println(messages.markAsDone);
            displayOneTask(taskNo - 1, taskList);
            System.out.println(messages.lineBreak);
            storage.writeAllEvents(taskList);
        }
    }

    /**
     * Unmark task no entered by user
     *
     * @param input    string entered by user
     * @param taskList
     * @throws IOException
     */
    public void unmarkTask(String input, TaskList taskList) throws IOException {
        int taskNo = parser.findTaskNo(input);
        if (taskNo <= 0 || taskNo > taskList.noOfTasks) {
            dukeException.markException();
        } else {
            taskList.mark(taskNo - 1, false);
            System.out.println(messages.lineBreak);
            System.out.println(messages.unmarkAsNotDone);
            displayOneTask(taskNo - 1, taskList);
            System.out.println(messages.lineBreak);
            storage.writeAllEvents(taskList);
        }
    }

    /**
     * Either prints task or tasks depending on whether there are 1 or multiple tasks present
     *
     * @param noOfTasks
     */
    String taskOrTasks(int noOfTasks) {
        if (noOfTasks == 1) {
            return " task ";
        } else {
            return " tasks ";
        }
    }
}
