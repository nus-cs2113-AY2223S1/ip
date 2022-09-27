import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.ArrayList;

import java.io.FileNotFoundException;

public class Duke {

    public static void main(String[] args) throws IOException {
        System.out.println("Hello, my name is Duke! \nWhat can I do for you?");

        ArrayList<Task> todoList;

        Scanner myObj = new Scanner(System.in);

        Path path = Paths.get("src","main","data");

        try{
            Files.createDirectories(path);
        }catch (FileAlreadyExistsException ignored){}

        try{
            todoList = ReadFromFile.addFileContents(path);
            System.out.println("I found some tasks saved, I have added them to the current session");

        }catch (FileNotFoundException | DukeExceptions e){
            System.out.println("File not found, creating one");
            WriteToFile.createFile(path);
            todoList = new ArrayList<>();
        }

        String userInput;
        String taskType;
        String userInputNewValue;
        String taskString; //based on how many tasks are in list, changes to plural
        final String LINES = "____________________________________________________________";

        int markedValue = 0;

        boolean bye = false;

        Commands commandType;
        while (!bye) {
            userInput = myObj.nextLine() + " ";
            taskType = userInput.substring(0, userInput.indexOf(' ')); //list, mark, unmark etc.

            userInputNewValue = userInput.substring(taskType.length()); //firstly does it assuming that it is todo, so no date , todo borrow book -> borrow book

            try {
                markedValue = Integer.parseInt(userInput.substring(userInput.indexOf(' ')).replaceAll("\\s+", "")) - 1; //used just for mark and unmark

            } catch (Exception ignored) {}

            try {
                commandType = Commands.valueOf(taskType.toUpperCase());
                switch (commandType) {
                case LIST:
                    System.out.println(LINES);
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < todoList.size(); i++) {
                        System.out.println("\t" + (i + 1) + ") " + todoList.get(i).toString());
                    }
                    System.out.println(LINES);
                    break;

                case BYE:
                    bye = true;
                    break;

                case MARK:
                    todoList.get(markedValue).markAsDone();
                    System.out.println(LINES);
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println(todoList.get(markedValue).getStatusIcon() + todoList.get(markedValue).getDescription());
                    System.out.println(LINES);
                    break;

                case UNMARK:
                    todoList.get(markedValue).unMark();
                    System.out.println(LINES);
                    System.out.println("OK, I've marked this task as not done yet:");
                    System.out.println(todoList.get(markedValue).getStatusIcon() + todoList.get(markedValue).getDescription());
                    System.out.println(LINES);

                case TODO:
                    try {
                        Task newTask = new Todo(userInputNewValue);

                        todoList.add(newTask);
                        System.out.println(LINES);
                        System.out.println("Got it. I've added this task: ");
                        System.out.println(todoList.get(todoList.size() - 1).toString());

                        taskString = todoList.size() == 1 ? " task " : " tasks ";

                        System.out.println("Now you have " + todoList.size() + taskString + "in the list.");
                        System.out.println(LINES);
                    }
                    catch (DukeExceptions e){
                        System.out.println(LINES);
                        System.out.println("TODO needs description");
                        System.out.println(LINES);
                    }

                    break;
                case DEADLINE:

                    Task newDeadline = new Deadline(userInputNewValue);
                    todoList.add(newDeadline);

                    System.out.println(LINES);
                    System.out.println("Got it. I've added this task: ");
                    System.out.println(todoList.get(todoList.size() - 1).toString());

                    taskString = todoList.size() == 1 ? " task " : " tasks ";

                    System.out.println("Now you have " + todoList.size() + taskString + "in the list.");
                    System.out.println(LINES);

                    break;
                case EVENT:

                    Task newEvent = new Event(userInputNewValue);
                    todoList.add(newEvent);

                    System.out.println(LINES);
                    System.out.println("Got it. I've added this task: ");
                    System.out.println(todoList.get(todoList.size() - 1).toString());

                    taskString = todoList.size() == 1 ? " task " : " tasks ";

                    System.out.println("Now you have " + todoList.size() + taskString + "in the list.");
                    System.out.println(LINES);
                    break;
                case DELETE:
                    System.out.println(LINES);
                    System.out.println("Noted. I've removed this task: ");
                    System.out.println(todoList.get(markedValue).toString());

                    taskString = todoList.size() == 2 ? " task " : " tasks ";

                    System.out.println("Now you have " + (todoList.size() - 1) + taskString + "in the list.");
                    System.out.println(LINES);
                    todoList.remove(markedValue);
                    break;
                case FIND:
                    int counter = 0;
                    System.out.println("I have found these tasks:");
                    for (Task currentTask: todoList){
                        String currentDescription = currentTask.getDescription();
                        if (currentDescription.contains(userInputNewValue)){
                            System.out.println(counter + "." + currentTask.toString());
                            counter++;
                        }
                    }

                    break;
                }

            } catch (Exception e) {
                System.out.println(LINES);
                System.out.println("\t Not sure what you meant, try again");
                System.out.println(LINES);
            }
        }
        System.out.println(LINES);
        System.out.println("\tBye. Hope to see you soon!");
        System.out.println(LINES);

        WriteToFile.appendToFile(path, todoList);
    }
}

