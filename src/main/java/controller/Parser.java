package controller;

import exceptions.InvalidDeadlineException;
import exceptions.InvalidEventException;
import exceptions.InvalidValueException;
import exceptions.InvalidTodoException;
import exceptions.UnrecognisedCommandException;
import exceptions.IncompleteCommandException;
import tasks.Deadline;
import tasks.Event;
import tasks.Todo;

/**
 * Represents a parser object which processes a user's line inputs by its methods
 */
public class Parser {

    private Storage storage;
    private TaskList list;
    private Ui ui;

    // information for Tasks with timestamps
    private int dividerIndex;
    private String timestamp;
    private String taskDescription;

    public Parser(Ui ui, Storage storage, TaskList list) {
        this.ui = ui;
        this.storage = storage;
        this.list = list;
    }

    /**
     * Determines whether user has entered todo command correctly
     * If yes, adds the new todo and prints out list.
     *
     * @param words array consisting of the individual words typed by user
     */
    public void addTodo(String[] words) {
        try {
            if (words.length == 1) {
                throw new InvalidTodoException();
            } else {
                Todo latestTodo = new Todo(words[1]);
                list.addTask(latestTodo);
                ui.printStatus(latestTodo, list.getTaskArray().size(), true);
            }
        } catch (InvalidTodoException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Determines whether user has entered event command correctly
     * If yes, adds the new event and prints out list.
     *
     * @param words array consisting of the individual words typed by user
     */
    public void addEvent(String[] words) {
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
        
        } catch (ArrayIndexOutOfBoundsException a) {
            System.out.println("Missing details after 'event' keyword.");
        
        } catch (InvalidEventException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Determines whether user has entered deadline command correctly
     * If yes, adds the new deadline and prints out list.
     *
     * @param words array consisting of the individual words typed by user
     */
    public void addDeadline(String[] words) {
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
            
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Missing details after 'deadline' keyword.");
            
        } catch (InvalidDeadlineException e) {
            System.out.println(e.getMessage());   
        }
    }

    /**
     * Determines whether user has entered mark command correctly
     * If yes, marks the particular task and prints out list.
     *
     * @param words array consisting of the individual words typed by user
     * @param userInput raw command string entered by the user
     */
    public void markTask(String userInput, String[] words) {
        try {
            if (userInput.matches("mark [0-9]+")) {
                int val = Integer.parseInt(words[1]);

                if (val < 0 || val > list.getTaskArray().size()) {
                    throw new InvalidValueException();
                } else {
                    list.getTaskArray().get(val - 1).markDone();
                    list.listTasks();
                }
            } 
        } catch (InvalidValueException e){
            System.out.println("Can't mark: " + e.getMessage());
        } 
    }

    /**
     * Determines whether user has entered unmark command correctly
     * If yes, unmarks the particular task and prints out list.
     *
     * @param words array consisting of the individual words typed by user
     * @param userInput raw command string entered by the user
     */
    public void unmarkTask(String userInput, String[] words) {
        try {
            if (userInput.matches("unmark [0-9]+")) {    
                int val = Integer.parseInt(words[1]);

                if (val < 0 || val > list.getTaskArray().size()) {
                    throw new InvalidValueException();
                } else {
                    list.getTaskArray().get(val - 1).unmark();
                    list.listTasks();
                }
            } 
        } catch (InvalidValueException e) {
            System.out.println("Can't unmark: " + e.getMessage());
        }
    }
    
    /**
     * Determines whether user has entered delete command correctly
     * If yes, removes the task and prints out remaining items
     *
     * @param words array consisting of the individual words typed by user
     * @param userInput raw command string entered by the user
     */
    public void deleteTask(String userInput, String[] words) {
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
            } 
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Missing details after 'delete' keyword.");
        } catch (InvalidValueException e) {
            System.out.println("Can't delete: " + e.getMessage());
        }
    }

    /**
     * Finds all the tasks which have descriptions matching 
     * keyword entered by user
     *
     * @param words array consisting of the individual words typed by user
     * 
     */
    public void findMatching(String[] words) {
        try {
            if (words.length == 1) {
                throw new IncompleteCommandException();
            } else {
                TaskList matchingList = new TaskList();
                for (int i = 0; i < list.getTaskArray().size(); i++) {
                    if (list.getTaskArray().get(i).toString().contains(words[1])) {
                        matchingList.addTask(list.getTaskArray().get(i));
                    }
                }
                System.out.println("\n\tHere are the matching tasks:");
                matchingList.listTasks();
            }
        } catch (IncompleteCommandException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Processes the first keyword of the command entered by user
     *
     * @param words array consisting of the individual words typed by user
     * @param userInput raw command string entered by the user
     */
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
        final String FIND_TRIGGER = "find";
        final String DO_NOTHING = "";
        
        switch (words[0]) {
        case LIST_TRIGGER:
            list.listTasks();
            break;

        case EXIT_TRIGGER:
            // save latest state to file
            storage.updateFile(list.getTaskArray());
            break;

        case TODO_TRIGGER:
            addTodo(words);
            break;

        case EVENT_TRIGGER:
            addEvent(words);
            break;

        case DEADLINE_TRIGGER:
            addDeadline(words);
            break;
        
        case MARK_TRIGGER:
            markTask(userInput, words);
            break;

        case UNMARK_TRIGGER:
            unmarkTask(userInput, words);
            break;
        
        case DELETE_TRIGGER:
            deleteTask(userInput, words);
            break;
            
        case FIND_TRIGGER:
            findMatching(words);
            break;

        case DO_NOTHING:
            break;
        
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
