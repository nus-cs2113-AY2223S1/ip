package controller;

import exceptions.InvalidDeadlineException;
import exceptions.InvalidEventException;
import exceptions.InvalidValueException;
import exceptions.InvalidTodoException;
import exceptions.UnrecognisedCommandException;
import tasks.Deadline;
import tasks.Event;
import tasks.Todo;

public class Parser {

    private Storage storage;
    private TaskList list;
    private Ui ui;

    public Parser(Ui ui, Storage storage, TaskList list) {
        this.ui = ui;
        this.storage = storage;
        this.list = list;
    }

    public void processUserInput(String[] words, String userInput) {
        //CONSTANTS
        final String LIST_TRIGGER = "list";
        final String TODO_TRIGGER = "todo";
        final String EVENT_TRIGGER = "event";
        final String DEADLINE_TRIGGER = "deadline";
        final String EXIT_TRIGGER = "bye";
        final String MARK_TRIGGER = "mark";
        final String UNMARK_TRIGGER = "unmark";
        final String DELETE_TRIGGER = "delete";

        // variables for Tasks with timestamps
        int dividerIndex;
        String timestamp;
        String taskDescription;

        switch (words[0]) {
        case LIST_TRIGGER:
            list.listTasks();
            break;

        case EXIT_TRIGGER:
            // save latest state to file
            storage.updateFile(list.getTaskArray());
            break;

        case TODO_TRIGGER:
            try {
                if (words.length == 1) {
                    throw new InvalidTodoException();
                } else {
                    Todo latestTodo = new Todo(words[1]);
                    list.addTask(latestTodo);
                    ui.printStatus(latestTodo, list.getTaskArray().size(), true);
                    break;
                }
            } catch (InvalidTodoException e) {
                System.out.println(e.getMessage());
                break;
            }

        case EVENT_TRIGGER:
            try {
                dividerIndex = words[1].indexOf("/");

                if (dividerIndex == -1) {
                    throw new InvalidEventException();
                }
                timestamp = words[1].substring(dividerIndex+3);
                taskDescription = words[1].substring(0, dividerIndex-1);
                
                Event latestEvent = new Event(taskDescription, timestamp);
                list.getTaskArray().add(latestEvent);
                ui.printStatus(latestEvent, list.getTaskArray().size(), true);
                break;
            } catch (ArrayIndexOutOfBoundsException a) {
                System.out.println("Missing details after 'event' keyword.");
                break;
            } catch (InvalidEventException e) {
                System.out.println(e.getMessage());
                break;
            }

        case DEADLINE_TRIGGER:
            try {
                dividerIndex = words[1].indexOf("/");
                
                if (dividerIndex == -1) {
                    throw new InvalidDeadlineException();
                }
                timestamp = words[1].substring(dividerIndex+3);
                taskDescription = words[1].substring(0, dividerIndex-1); 
                
                Deadline latestDeadline = new Deadline(taskDescription, timestamp);
                list.getTaskArray().add(latestDeadline);
                ui.printStatus(latestDeadline, list.getTaskArray().size(), true);
                break;
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Missing details after 'deadline' keyword.");
                break;
            } catch (InvalidDeadlineException e) {
                System.out.println(e.getMessage());
                break;
            }

        case MARK_TRIGGER:
            // making sure there is valid numerical input after mark/unmark
            try {
                if (userInput.matches("mark [0-9]+")) {
                    int val = Integer.parseInt(words[1]);

                    if (val < 0 || val > list.getTaskArray().size()) {
                        throw new InvalidValueException();
                    } else {
                        list.getTaskArray().get(val - 1).markDone();
                        list.listTasks();
                    }
                    break;
                } 
            } catch (InvalidValueException e){
                System.out.println("Can't mark: " + e.getMessage());
                break;
            } 

        case UNMARK_TRIGGER:
            try {
                // making sure there is valid numerical input after mark/unmark
                if (userInput.matches("unmark [0-9]+")) {    
                    int val = Integer.parseInt(words[1]);

                    if (val < 0 || val > list.getTaskArray().size()) {
                        throw new InvalidValueException();
                    } else {
                        list.getTaskArray().get(val - 1).unmark();
                        list.listTasks();
                    }
                    break;
                } 
            } catch (InvalidValueException e) {
                System.out.println("Can't unmark: " + e.getMessage());
                break;
            }
        
        case DELETE_TRIGGER:
            try {
                // making sure there is valid numerical input after mark/unmark
                if (userInput.matches("delete [0-9]+")) {    
                    int val = Integer.parseInt(words[1]);

                    if (val < 0 || val > list.getTaskArray().size()) {
                        throw new InvalidValueException();
                    } else {
                        ui.printStatus(list.getTaskArray().get(val-1), list.getTaskArray().size()-1, false);
                        list.deleteTask(val-1);
                        list.listTasks();
                    }
                    break;
                } 
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Missing details after 'delete' keyword.");
                break;
            } catch (InvalidValueException e) {
                System.out.println("Can't delete: " + e.getMessage());
                break;
            }

        default:
            // unrecognised command
            try {
                throw new UnrecognisedCommandException();
            } catch (UnrecognisedCommandException e) {
                System.out.println(e.getMessage());
                break;
            }
        }
    }
}
