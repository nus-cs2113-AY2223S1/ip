package parser;

import Commands.Command;
import duke.Duke;
import dukeExceptionsPackage.*;
import dukeTasksPackage.*;
import ui.DukeUI;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Represents a parser that parses user input
 * Returns a Command
 */
public class Parser {
    private static final String COMMAND_BYE = "bye";
    private static final String COMMAND_LIST = "list";
    private static final String COMMAND_MARK = "mark";
    private static final String COMMAND_UNMARK = "unmark";
    private static final String COMMAND_TODO = "todo";
    private static final String COMMAND_DEADLINE = "deadline";
    private static final String COMMAND_EVENT = "event";
    private static final String COMMAND_DELETE = "delete";
    private static final String COMMAND_CLEAR = "clear";
    private static final String COMMAND_FIND = "find";

    /**
     * to convert user input into commands for Duke
     * @param input user input
     * @return command that user is telling Duke
     */
    public Command parseInputString(String input) {
        Command command;
        if (input.equals(COMMAND_BYE)) {
            command = Command.BYE;
        } else {
            String taskType = input.split("\\s+")[0];
            switch (taskType) {
            case COMMAND_TODO:
                command = Command.TODO;
                break;
            case COMMAND_DEADLINE:
                command = Command.DEADLINE;
                break;
            case COMMAND_EVENT:
                command = Command.EVENT;
                break;
            case COMMAND_MARK:
                command = Command.MARK;
                break;
            case COMMAND_UNMARK:
                command = Command.UNMARK;
                break;
            case COMMAND_LIST:
                command = Command.LIST;
                break;
            case COMMAND_DELETE:
                command = Command.DELETE;
                break;
            case COMMAND_CLEAR:
                command = Command.CLEAR;
                break;
            case COMMAND_FIND:
                command = Command.FIND;
                break;
            default:
                command = Command.UNRECOGNISED_INPUT;
                break;
            }
        }
        return command;
    }
}