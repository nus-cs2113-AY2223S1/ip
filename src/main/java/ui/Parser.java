package ui;

import exceptions.InvalidDateTimeException;
import exceptions.InvalidTaskDescriptionException;
import exceptions.UnknownCommandException;
import tasks.Task;
import tasks.Todo;
import tasks.Deadline;
import tasks.Event;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Extracts task attributes from user input by tokenizing and parsing user input.
 */
public class Parser {
    private String readInput;
    private String command;
    private String name;
    private int taskNumber;
    private String dateTime;
    private String taskType;
    private ArrayList<String> taskCommands = new ArrayList<String>(Arrays.asList("todo", "deadline", "event"));
    private ArrayList<String> numberCommands = new ArrayList<String>(Arrays.asList("delete", "mark", "unmark"));
    private ArrayList<String> otherCommmands = new ArrayList<String>(Arrays.asList("list", "help", "save", "delete", "bye"));

    public Parser(String readInput){ this.readInput = readInput; }

    public void parseCommand() throws Exception {
        String[] splitString = readInput.split("\\s+");
        // If the first token is a task command
        this.command = splitString[0];
        if (taskCommands.contains(splitString[0])) {
            this.taskType = splitString[0];
            if (splitString.length == 1) { // Only one token
                throw new InvalidTaskDescriptionException();
            }
            if (this.taskType.equals("todo")) {
                this.name = readInput.substring(taskType.length() + 1);
            } else if (this.taskType.equals("deadline") || this.taskType.equals("event")) {
                int dateStrIndex = readInput.indexOf("/");
                if (dateStrIndex > -1) {
                    String afterSlash = readInput.substring(readInput.indexOf("/"));
                    String[] slashString = afterSlash.split("\\s+");
                    this.name = readInput.substring(taskType.length() + 1, readInput.indexOf("/")).trim();
                    this.dateTime = afterSlash.substring(slashString[0].length() + 1);
                } else {
                    throw new InvalidDateTimeException();
                }

            }
        } else if (numberCommands.contains(splitString[0])) {
            // If the first token is a number command
            if (splitString.length==2){
                this.taskNumber = Integer.parseInt(splitString[1]) - 1;
            }else{
                throw new UnknownCommandException();
            }
        } else {
            if(splitString.length != 1 || !otherCommmands.contains(splitString[0])) {
                throw new UnknownCommandException();
            }
        }
    }

    public String getCommand() { return command; }

    public int getTaskNumber() { return taskNumber; }

    public Task toTask(){
        if(taskType.equals("deadline")){
            return new Deadline(this.name, this.dateTime);
        }
        else if(taskType.equals("event")){
            return new Event(this.name, this.dateTime);
        }
        else{
            return new Todo(this.name);
        }
    }
}

