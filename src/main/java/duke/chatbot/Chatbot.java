package duke.chatbot;

import duke.exception.TodoNoDescriptionException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;
import duke.text.ErrorText;
import duke.text.InfoText;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Chatbot {
    private ArrayList<Task> tasks = new ArrayList<>();
    private String output;

    public void printOutput(String output) {
        System.out.println(InfoText.INFO_DASH + System.lineSeparator()
                + output + System.lineSeparator()
                + InfoText.INFO_DASH);
    }

    public String constructTaskActionInfo(Enum taskActionInfo) {
        return taskActionInfo + System.lineSeparator()
                + "    * " + tasks.get(tasks.size()-1).getTaskDetails() + System.lineSeparator()
                + String.format("%s", String.format(String.valueOf(InfoText.INFO_TASK_COUNT), tasks.size()));
    }

    public void writeToFile() {
        try {
            FileWriter fw = new FileWriter("data/data.txt");
            for (int i = 0; i < tasks.size(); i++) {
                String taskType = Character.toString(tasks.get(i).getTaskDetails().charAt(1));
                String taskStatus = tasks.get(i).getStatusIcon().equals("X") ? "1" : "0";
                String taskTitle = tasks.get(i).getTitle();
                String textToWrite = String.format("%s | %s | %s", taskType, taskStatus, taskTitle);
                if (taskType.equals("D")) {
                    textToWrite += String.format(" | %s", tasks.get(i).getDueBy());
                } else if (taskType.equals("E")) {
                    textToWrite += String.format(" | %s", tasks.get(i).getTime());
                }
                fw.write(textToWrite + System.lineSeparator());
            }
            fw.close();
        } catch (IOException e) {
            output = String.valueOf(ErrorText.ERROR_FILE_IO);
        }
    }

    public void greet() {
        output = "    *******     *******   *******       **" + System.lineSeparator()
                + "    /**////**   **/////** /**////**     ****" + System.lineSeparator()
                + "    /**    /** **     //**/**   /**    **//**" + System.lineSeparator()
                + "    /**    /**/**      /**/*******    **  //**" + System.lineSeparator()
                + "    /**    /**/**      /**/**///**   **********" + System.lineSeparator()
                + "    /**    ** //**     ** /**  //** /**//////**" + System.lineSeparator()
                + "    /*******   //*******  /**   //**/**     /**" + System.lineSeparator()
                + "    ///////     ///////   //     // //      //" +  System.lineSeparator()
                + InfoText.INFO_WELCOME;
        printOutput(output);
    }

    public void bye() {
        output = String.valueOf(InfoText.INFO_BYE);
        printOutput(output);
    }

    public void listTasks() {
        output = String.valueOf(InfoText.INFO_LIST) + System.lineSeparator();
        for (int i = 0; i < tasks.size(); i++) {
            output += "    " + tasks.get(i).getTaskDetails() + System.lineSeparator();
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

    public void markTask(String description, boolean isDone) {
        int taskIndex = validateIndex(description, ErrorText.ERROR_INVALID_STATUS_FORMAT);
        if (taskIndex == -1) {
            return;
        }
        tasks.get(taskIndex).setStatus(isDone);
        output = String.valueOf(isDone ? InfoText.INFO_TASK_MARKED : InfoText.INFO_TASK_UNMARKED);
        output += System.lineSeparator() + "    * " + tasks.get(taskIndex).getTaskDetails();
        printOutput(output);
        writeToFile();
    }

    public void checkTodoFormat(String description) throws TodoNoDescriptionException {
        if (description.isBlank()) {
            throw new TodoNoDescriptionException();
        }
    }

    public void addTask(String command, String description) {
        Enum invalidFormatType = ErrorText.ERROR_INVALID_TODO_FORMAT;
        String[] descriptionSplits;
        try {
            switch (command) {
            case "todo":
                checkTodoFormat(description);
                Todo todo = new Todo(description);
                tasks.add(todo);
                break;
            case "deadline":
                invalidFormatType = ErrorText.ERROR_INVALID_DEADLINE_FORMAT;
                descriptionSplits = description.split(" /by ");
                Deadline deadline = new Deadline(descriptionSplits[0], descriptionSplits[1]);
                tasks.add(deadline);
                break;
            case "event":
                invalidFormatType = ErrorText.ERROR_INVALID_EVENT_FORMAT;
                descriptionSplits = description.split(" /at ");
                Event event = new Event(descriptionSplits[0], descriptionSplits[1]);
                tasks.add(event);
                break;
            }
            output = constructTaskActionInfo(InfoText.INFO_TASK_ADDED);
            writeToFile();
        } catch (TodoNoDescriptionException | ArrayIndexOutOfBoundsException e) {
            output = String.valueOf(invalidFormatType);
        }
        printOutput(output);
    }

    public void deleteTask(String description) {
        int taskIndex = validateIndex(description, ErrorText.ERROR_INVALID_DELETE_FORMAT);
        if (taskIndex == -1) {
            return;
        }
        String taskDetails = tasks.get(taskIndex).getTaskDetails();
        tasks.remove(taskIndex);
        output = constructTaskActionInfo(InfoText.INFO_TASK_DELETED);
        printOutput(output);
        writeToFile();
    }

    public void alertInvalidCommand() {
        output = String.valueOf(ErrorText.ERROR_INVALID_COMMAND);
        printOutput(output);
    }
}

