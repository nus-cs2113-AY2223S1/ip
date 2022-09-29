package main.duke;

public class Duke {

    /* Variables for Duke's use */
    private static Ui ui;
    private static Storage storage;
    private static TaskList taskList;


    public static void main(String[] args) {

        //Print introduction to Duke
        Utils.introduction();

        //Initialize necessary variables
        storage = new Storage();


        //Load in tasks if a data file exists, and show them to the user
        storage.loadTasksOrError();

        //If a file is loaded in successfully, save that as a taskList
        if (storage.getIsFileLoaded()) {
            taskList = storage.getTaskList();
        } else {
            taskList = new TaskList();
        }

        //Initiate the user input machine with the given task list
        ui = new Ui(taskList);

        //Re-iterate what the user types, store in list, and unmark / mark
        ui.userInput();

        //Once the user exists, save the current lists of tasks in a data file
        storage.updateTaskList(taskList);
        storage.saveTasksOrError();

        //Salute them goodbye
        Utils.goodBye();
    }


}
