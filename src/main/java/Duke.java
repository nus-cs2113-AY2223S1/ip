import Exceptions.*;
//import Tasks.Deadline;
//import Tasks.Event;
//import Tasks.Task;
//import Tasks.ToDo;
import Tasks.*;

import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Duke {
    public static final int DEADLINE_STRING_LENGTH = 9;     // "deadline "
    public static final int BY_SEPARATOR_LENGTH = 4;        // "/by  "
    public static final int EVENT_STRING_LENGTH = 6;        // "event "
    public static final int AT_SEPARATOR_LENGTH = 4;        // "/at  "
    public static final String DATA_FILE_PATH = "data.txt";
    public static ArrayList<Task> list = new ArrayList<Task>();

    public static void initializeFile() {
        File f = new File(DATA_FILE_PATH);
        if (!f.exists()) {
            try {
                f.createNewFile();
            } catch (IOException e) {
                System.out.println("An error occurred while creating 'data.txt'");
            }
        }

//        System.out.println("full path: " + f.getAbsolutePath());
//        System.out.println("file exists?: " + f.exists());
//        System.out.println("is Directory?: " + f.isDirectory());
    }

    public static void populateInitialList(String filePath) {
        File f = new File(DATA_FILE_PATH);
        Scanner s = null;
        try {
            s = new Scanner(f);
            s.useDelimiter(System.lineSeparator());
        } catch (FileNotFoundException e) {
            System.out.println("'data.txt' not found");
        }

        while (s.hasNext()) {
            String nextTask = s.next();
            String[] taskParameters = nextTask.split(" \\| ");

            switch (taskParameters[0]) {
            case "T":
                list.add(new ToDo(taskParameters[2], Boolean.parseBoolean(taskParameters[1])));
                break;

            case "D":
                list.add(new Deadline(taskParameters[2], Boolean.parseBoolean(taskParameters[1]), taskParameters[3]));
                break;

            case "E":
                list.add(new Event(taskParameters[2], Boolean.parseBoolean(taskParameters[1]), taskParameters[3]));
                break;

            default:
                break;
            }
        }
    }

    private static void writeToFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(textToAdd);
        fw.close();
    }

    private static void appendToFile(String filePath, String textToAppend) throws IOException {
        FileWriter fw = new FileWriter(filePath, true); // create a FileWriter in append mode
        fw.write(textToAppend);
        fw.close();
    }

    public static String taskListToString(ArrayList<Task> list) {
        String outputList = "";
        String tempTaskString = "";
        for (int i = 1; i <= list.size(); i += 1) {
            String taskType = list.get(i - 1).getTaskType();
            String markDoneStatus = list.get(i - 1).isDone() ? "1" : "0";

            switch (taskType) {
            case "ToDo":
                tempTaskString = "T | " + markDoneStatus + " | " + list.get(i - 1).getTaskName() + System.lineSeparator();
                outputList += tempTaskString;
                break;

            case "Deadline":
                String deadline = list.get(i - 1).getTaskDeadline();
                tempTaskString = "D | " + markDoneStatus + " | " + list.get(i - 1).getTaskName() +
                        " | " + deadline + System.lineSeparator();
                outputList += tempTaskString;
                break;

            case "Event":
                String eventTime = list.get(i - 1).getEventTime();
                tempTaskString = "E | " + markDoneStatus + " | " + list.get(i - 1).getTaskName() +
                        " | " + eventTime + System.lineSeparator();
                outputList += tempTaskString;
                break;

            default:
                break;
            }
        }
        return outputList;
    }

    public static void printList(ArrayList<Task> list) {
        System.out.println("        ____________________________________________");
        for (int i = 1; i <= list.size(); i += 1) {
            String taskType = list.get(i - 1).getTaskType();
            String markDoneStatus = list.get(i - 1).isDone() ? "[X]" : "[ ]";

            switch (taskType) {
            case "ToDo":
                System.out.println("        [T]" + markDoneStatus + " " + i + ". " + list.get(i - 1).getTaskName());
                break;

            case "Deadline":
                String deadline = list.get(i - 1).getTaskDeadline();
                System.out.println("        [D]" + markDoneStatus + " " + i + ". " + list.get(i - 1).getTaskName() +
                        " (by: " + deadline + ")");
                break;

            case "Event":
                String eventTime = list.get(i - 1).getEventTime();
                System.out.println("        [E]" + markDoneStatus + " " + i + ". " + list.get(i - 1).getTaskName() +
                        " (at: " + eventTime + ")");
                break;

            default:
                break;
            }
        }
        System.out.println("        ____________________________________________");
    }

    private static void printGoodbyeMessage() {
        System.out.println("____________________________________________");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("____________________________________________");
    }

    private static void printWelcomeMessage() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("____________________________________________");
        System.out.println("Hello! I'm Duke.");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________");
    }

    private static String getTaskIndicator(String[] line) {
        String taskType = list.get(Integer.parseInt(line[1]) - 1).getTaskType();
        String taskIndicator = "";
        switch (taskType) {
        case "ToDo":
            taskIndicator = "[T]";
            break;

        case "Deadline":
            taskIndicator = "[D]";
            break;

        case "Event":
            taskIndicator = "[E]";
            break;

        default:
            break;
        }
        return taskIndicator;
    }

    public static void handleInput() {
        String input = "";
        Scanner in = new Scanner(System.in);
        input = in.nextLine();
        String[] line = input.split(" ");
        boolean checkedInitialLine = false;

        while (!line[0].equals("bye")) {
            if (checkedInitialLine) {
                input = in.nextLine();
                line = input.split(" ");
            } else {
                checkedInitialLine = true;
            }

            switch (line[0]) {
            case "list":
                printList(list);
                break;

            case "mark":
                try {
                    if ((Integer.parseInt(line[1])) > list.size() || (Integer.parseInt(line[1])) < 1) {
                        throw new InvalidListItemNumberException();
                    }

                    list.get(Integer.parseInt(line[1]) - 1).setDone(true);

                    String taskIndicator = getTaskIndicator(line);
                    String markDone = list.get(Integer.parseInt(line[1]) - 1).isDone() ? "[X]" : "[ ]";
                    System.out.println("        ____________________________________________");
                    System.out.println("        Nice! I've marked this task as done:");
                    System.out.println("            " + taskIndicator + markDone + " " +
                            list.get(Integer.parseInt(line[1]) - 1).getTaskName());
                    System.out.println("        ____________________________________________");


                    String file = DATA_FILE_PATH;
                    String newListText = taskListToString(list);
                    writeToFile(file, newListText);
                } catch (InvalidListItemNumberException | NumberFormatException e) {
                    System.out.println("OOPS!!! The list item number given is invalid.");
                } catch (IOException e) {
                    System.out.println("Something went wrong: " + e.getMessage());
                }
                break;

            case "unmark":
                try {
                    if ((Integer.parseInt(line[1])) > list.size() || (Integer.parseInt(line[1])) < 1) {
                        throw new InvalidListItemNumberException();
                    }

                    list.get(Integer.parseInt(line[1]) - 1).setDone(false);

                    String taskIndicator = getTaskIndicator(line);
                    String unmarkDone = list.get(Integer.parseInt(line[1]) - 1).isDone() ? "[X]" : "[ ]";
                    System.out.println("        ____________________________________________");
                    System.out.println("        Ok. I've marked this task as not done yet:");
                    System.out.println("            " + taskIndicator + unmarkDone + " " +
                            list.get(Integer.parseInt(line[1]) - 1).getTaskName());
                    System.out.println("        ____________________________________________");

                    String file = DATA_FILE_PATH;
                    String newListText = taskListToString(list);
                    writeToFile(file, newListText);
                } catch (InvalidListItemNumberException | NumberFormatException e) {
                    System.out.println("OOPS!!! The list item number given is invalid.");
                } catch (IOException e) {
                    System.out.println("Something went wrong: " + e.getMessage());
                }
                break;

            case "todo":
                try {
                    if (line.length == 1) {
                        throw new InvalidTodoCommandException();
                    }

                    String[] todoName = Arrays.copyOfRange(line, 1, line.length);
                    list.add(new ToDo(Arrays.toString(todoName).replace(",", "")
                            .replace("[", "").replace("]", "")));

                    System.out.println("        ____________________________________________");
                    System.out.println("        Got it. I've added this task:");
                    System.out.println("        [T][ ] " + Arrays.toString(todoName).replace(",", "")
                            .replace("[", "").replace("]", ""));
                    System.out.println("        Now you have " + list.size() + " tasks in the list.");
                    System.out.println("        ____________________________________________");


                    String file = DATA_FILE_PATH;
                    String newListText = taskListToString(list);
                    writeToFile(file, newListText);
                } catch (InvalidTodoCommandException e) {
                    System.out.println("OOPS!!! The description of a todo cannot be empty.");
                } catch (IOException e) {
                    System.out.println("Something went wrong: " + e.getMessage());
                }
                break;

            case "deadline":
                try {
                    int byIndex = input.indexOf("/by");
                    if (byIndex == -1) {
                        throw new MissingKeywordException();
                    } else if (byIndex == DEADLINE_STRING_LENGTH) {
                        throw new MissingTaskException();
                    } else if (byIndex + 2 == input.length() - 1) {
                        throw new MissingDateException();
                    }

                    String deadlineName = String.copyValueOf(input.toCharArray(), DEADLINE_STRING_LENGTH,
                            byIndex - 1 - DEADLINE_STRING_LENGTH);
                    String taskDeadline = String.copyValueOf(input.toCharArray(), byIndex + BY_SEPARATOR_LENGTH,
                            input.length() - byIndex - BY_SEPARATOR_LENGTH);
                    list.add(new Deadline(deadlineName, taskDeadline));

                    System.out.println("        ____________________________________________");
                    System.out.println("        Got it. I've added this task:");
                    System.out.println("        [D][ ] " + deadlineName + " (by: " + taskDeadline + ")");
                    System.out.println("        Now you have " + list.size() + " tasks in the list.");
                    System.out.println("        ____________________________________________");



                    String file = DATA_FILE_PATH;
                    String newListText = taskListToString(list);
                    writeToFile(file, newListText);
                } catch (MissingKeywordException e) {
                    System.out.println("OOPS!!! You did not include '/by'.");
                } catch (MissingTaskException e) {
                    System.out.println("OOPS!!! You did not indicate the task.");
                } catch (MissingDateException e) {
                    System.out.println("OOPS!!! You did not indicate the deadline.");
                } catch (IOException e) {
                    System.out.println("Something went wrong: " + e.getMessage());
                }
                break;

            case "event":
                try {
                    int atIndex = input.indexOf("/at");
                    if (atIndex == -1) {
                        throw new MissingKeywordException();
                    } else if (atIndex == EVENT_STRING_LENGTH) {
                        throw new MissingTaskException();
                    } else if (atIndex + 2 == input.length() - 1) {
                        throw new MissingDateException();
                    }

                    String eventName = String.copyValueOf(input.toCharArray(), EVENT_STRING_LENGTH,
                            atIndex - 1 - EVENT_STRING_LENGTH);
                    String eventTime = String.copyValueOf(input.toCharArray(), atIndex + AT_SEPARATOR_LENGTH,
                            input.length() - atIndex - AT_SEPARATOR_LENGTH);
                    list.add(new Event(eventName, eventTime));

                    System.out.println("        ____________________________________________");
                    System.out.println("        Got it. I've added this task:");
                    System.out.println("        [E][ ] " + eventName + " (at: " + eventTime + ")");
                    System.out.println("        Now you have " + list.size() + " tasks in the list.");
                    System.out.println("        ____________________________________________");


                    String file = DATA_FILE_PATH;
                    String newListText = taskListToString(list);
                    writeToFile(file, newListText);
                } catch (MissingKeywordException e) {
                    System.out.println("OOPS!!! You did not include '/at'.");
                } catch (MissingTaskException e) {
                    System.out.println("OOPS!!! You did not indicate the task.");
                } catch (MissingDateException e) {
                    System.out.println("OOPS!!! You did not indicate the event date.");
                } catch (IOException e) {
                    System.out.println("Something went wrong: " + e.getMessage());
                }
                break;

            case "delete":
                try {
                    if ((Integer.parseInt(line[1])) > list.size() || (Integer.parseInt(line[1])) < 1) {
                        throw new InvalidListItemNumberException();
                    }

                    String taskIndicator = getTaskIndicator(line);
                    String taskStatus = list.get(Integer.parseInt(line[1]) - 1).isDone() ? "[X]" : "[ ]";
                    System.out.println("        ____________________________________________");
                    System.out.println("        Noted. I've removed this task:");
                    System.out.println("            " + taskIndicator + taskStatus + " " +
                            list.get(Integer.parseInt(line[1]) - 1).getTaskName());
                    System.out.println("        Now you have " + list.size() + " tasks in the list.");
                    System.out.println("        ____________________________________________");

                    list.remove(Integer.parseInt(line[1]) - 1);

                    String file = DATA_FILE_PATH;
                    String newListText = taskListToString(list);
                    writeToFile(file, newListText);
                } catch (InvalidListItemNumberException | NumberFormatException e) {
                    System.out.println("OOPS!!! The list item number given is invalid.");
                } catch (IOException e) {
                    System.out.println("Something went wrong: " + e.getMessage());
                }
                break;

            case "bye":
                break;

            default:
                try {
                    throw new InvalidCommandException();
                } catch (InvalidCommandException e) {
                    System.out.println("OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            }
        }
        return;
    }



    public static void main(String[] args) throws InvalidCommandException {
        printWelcomeMessage();

        initializeFile();
        String filePath = DATA_FILE_PATH;
        populateInitialList(filePath);

        handleInput();
        printGoodbyeMessage();
    }
}
