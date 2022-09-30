package duke;

import duke.tasks.TaskList;
import duke.ui.Ui;

public class Parser {
    public static final String BY = "/by ";
    public static final String TODO = "todo ";
    public static final String EVENT = "event ";
    public static final String LIST = "list";
    public static final String BYE = "bye";
    public static final String DEADLINE = "deadline ";
    public static final String AT = "/at ";
    public static final String DELETE = "delete ";
    public static final String MARK_UNDONE = "unmark ";
    public static final String MARK_DONE = "mark ";

    public static Ui ui = new Ui ();

    public static TaskList tasks = new TaskList();

    public void parse (String input){

        if (input.contains(LIST)) {
            tasks.listTasks(TaskList.taskList);
        } else if (input.contains(MARK_UNDONE)) {
            try {
                prepMarkUnDone(input);
            }
            catch (DukeException e)
            {
                System.out.println("The task number is out of bound and therefore cannot be marked undone!");
            }
        } else if (input.contains(MARK_DONE)) {
            try {
                prepMarkDone(input);
            } catch (DukeException e) {
                System.out.println("The task number is out of bound and therefore cannot be marked done!");
            }

        } else if (input.contains(DELETE)) {
            try {
                prepDelete(input);
            }
            catch (DukeException e)
            {
                System.out.println("The task number is out of bound and therefore cannot be deleted!");
            }
        } else if (input.contains (TODO)) {
            try {
                prepToDo(input);
            }
            catch (DukeException e)
            {
                System.out.println("The todo input is not valid! Might be missing description!");
            }
        } else if (input.contains(DEADLINE)) {
            try {
                prepDeadline(input);
            }
            catch (DukeException e)
            {
                System.out.println("The deadline input is not valid! Might be missing description, '/by' or deadline!");
            }
        } else if (input.contains(EVENT)) {
            try{
                prepEvent(input);
            }
            catch (DukeException e){
                System.out.println("The event input is not valid! Might be missing description, '/at' or time !");
            }
        } else if (input.equals(BYE)) {
            ui.printBye();
        }
        else {
            System.out.println("Oops... cannot recognize the input command !");
        }

    }

    public void prepMarkDone (String input) throws DukeException{
        int taskNumber = Integer.parseInt(input.substring(input.indexOf(MARK_DONE) + MARK_DONE.length()));
        if (taskNumber < TaskList.taskList.size()) {
            tasks.markDone(TaskList.taskList, taskNumber);
        } else {
            throw new DukeException();
        }
    }


    public void prepMarkUnDone (String input) throws DukeException{
        int taskNumber = Integer.parseInt(input.substring(input.indexOf(MARK_UNDONE) + MARK_UNDONE.length()));
        if (taskNumber < TaskList.taskList.size()) {
            tasks.markUnDone(TaskList.taskList, taskNumber);
        }
        else{
            throw new DukeException();
        }
    }



    public void prepToDo(String input) throws DukeException{
        String task = input.substring(TODO.length());
        if (task.equals("")){
            throw new DukeException();
        } else{
            tasks.addTodo(TaskList.taskList, task);
        }
    }


    public void prepDeadline (String input) throws DukeException{
        String task = input.substring(DEADLINE.length(), input.indexOf(BY));
        String deadline = input.substring(input.indexOf(BY) + BY.length());

        if (task.equals("") || deadline.equals("")){
            throw new DukeException();
        } else{
            tasks.addDeadline(TaskList.taskList, task, deadline);
        }
    }

    public void prepEvent (String input) throws DukeException{
        String task = input.substring(EVENT.length(), input.indexOf(AT));
        String time = input.substring(input.indexOf(AT) + AT.length());

        if (task.equals("") || (time.equals(""))){
            throw new DukeException();
        }
        else{
            tasks.addEvent(TaskList.taskList, task, time);
        }
    }


    public void prepDelete (String input) throws DukeException{
        int taskNumber = Integer.parseInt(input.substring(input.indexOf(DELETE) + DELETE.length()));
        if (taskNumber < TaskList.taskList.size()) {
            tasks.deleteTask (TaskList.taskList, taskNumber);
        } else {
            throw new DukeException();
        }
    }









}
