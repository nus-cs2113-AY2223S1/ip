package duke.chatbot;

import duke.exception.TodoNoDescriptionException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;
import duke.text.ErrorText;
import duke.text.InfoText;

import java.util.ArrayList;

public class ChatBot {
    private ArrayList<Task> tasks = new ArrayList<>();
    private String output;

    public void printOutput(String output) {
        System.out.println(InfoText.INFO_DASH + System.lineSeparator()
                + output + System.lineSeparator()
                + InfoText.INFO_DASH);
    }

    public String constructTaskActionInfo(Enum taskActionInfo, String taskDetails) {
        return taskActionInfo + System.lineSeparator()
                + "    * " + taskDetails + System.lineSeparator()
                + String.format("%s", String.format(String.valueOf(InfoText.INFO_TASK_COUNT), tasks.size()));
    }

    public void greet() {
        FileOperation.readFromFile(this);
        output = "    *******     *******   *******       **" + System.lineSeparator()
                + "    /**////**   **/////** /**////**     ****" + System.lineSeparator()
                + "    /**    /** **     //**/**   /**    **//**" + System.lineSeparator()
                + "    /**    /**/**      /**/*******    **  //**" + System.lineSeparator()
                + "    /**    /**/**      /**/**///**   **********" + System.lineSeparator()
                + "    /**    ** //**     ** /**  //** /**//////**" + System.lineSeparator()
                + "    /*******   //*******  /**   //**/**     /**" + System.lineSeparator()
                + "    ///////     ///////   //     // //      //" +  System.lineSeparator()
                + System.lineSeparator() + InfoText.INFO_GREET;
        printOutput(output);
    }

    public void bye() {
        output = String.valueOf(InfoText.INFO_BYE);
        printOutput(output);
    }

    public void listTasks() {
        output = String.valueOf(InfoText.INFO_LIST) + System.lineSeparator();
        for (Task task : tasks) {
            output += "    " + task.getTaskDetails() + System.lineSeparator();
        }
        output = output.substring(0, output.length()-1);
        printOutput(output);
    }

    public Integer validateIndex(String description, Enum invalidFormatType) {
        try {
            int taskIndex = Integer.parseInt(description) - 1;
            tasks.get(taskIndex);
            return taskIndex;
        } catch (NumberFormatException e) {
            output = String.valueOf(invalidFormatType);
        } catch (IndexOutOfBoundsException e) {
            output = String.valueOf(ErrorText.ERROR_INVALID_TASK_INDEX);
        }
        printOutput(output);
        return -1;
    }

    public void markTask(String command, String description) {
        int taskIndex = validateIndex(description, ErrorText.ERROR_INVALID_STATUS_FORMAT);
        if (taskIndex == -1) {
            return;
        }
        boolean isDone = command.equals("mark") ? true : false;
        tasks.get(taskIndex).setStatus(isDone);
        output = String.valueOf(isDone ? InfoText.INFO_TASK_MARKED : InfoText.INFO_TASK_UNMARKED);
        output += System.lineSeparator() + "    * " + tasks.get(taskIndex).getTaskDetails();
        printOutput(output);
        FileOperation.writeToFile(this, tasks);
    }

    public void checkTodoFormat(String description) throws TodoNoDescriptionException {
        if (description.isBlank()) {
            throw new TodoNoDescriptionException();
        }
    }

    public void addTask(String command, String description, boolean isDone, boolean isPrint) {
        Enum invalidFormatType = ErrorText.ERROR_INVALID_TODO_FORMAT;
        String[] descriptionSplits;
        try {
            switch (command) {
            case "todo":
                checkTodoFormat(description);
                Todo todo = new Todo(description, isDone);
                tasks.add(todo);
                break;
            case "deadline":
                invalidFormatType = ErrorText.ERROR_INVALID_DEADLINE_FORMAT;
                descriptionSplits = description.split(" /by ");
                Deadline deadline = new Deadline(descriptionSplits[0], descriptionSplits[1], isDone);
                tasks.add(deadline);
                break;
            case "event":
                invalidFormatType = ErrorText.ERROR_INVALID_EVENT_FORMAT;
                descriptionSplits = description.split(" /at ");
                Event event = new Event(descriptionSplits[0], descriptionSplits[1], isDone);
                tasks.add(event);
                break;
            default:
                break;
            }
            output = constructTaskActionInfo(InfoText.INFO_TASK_ADDED,
                    tasks.get(tasks.size()-1).getTaskDetails());
        } catch (TodoNoDescriptionException | ArrayIndexOutOfBoundsException e) {
            output = String.valueOf(invalidFormatType);
        }
        if (isPrint) {
            printOutput(output);
            FileOperation.writeToFile(this, tasks);
        }
    }

    public void deleteTask(String description) {
        int taskIndex = validateIndex(description, ErrorText.ERROR_INVALID_DELETE_FORMAT);
        if (taskIndex == -1) {
            return;
        }
        String taskDetails = tasks.get(taskIndex).getTaskDetails();
        tasks.remove(taskIndex);
        output = constructTaskActionInfo(InfoText.INFO_TASK_DELETED, taskDetails);
        printOutput(output);
        FileOperation.writeToFile(this, tasks);
    }

    public void alertInvalidCommand() {
        output = String.valueOf(ErrorText.ERROR_INVALID_COMMAND);
        printOutput(output);
    }
}

