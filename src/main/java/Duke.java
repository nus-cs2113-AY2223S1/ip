import dukeExceptionsPackage.*;
import dukeTasksPackage.*;
import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;
public class Duke {
    private static final String COMMAND_BYE = "bye";
    private static final String MESSAGE_BYE = "Duke: Bye. Hope to see you again soon!";
    private static final String COMMAND_LIST = "list";
    private static final String COMMAND_MARK = "mark";
    private static final String COMMAND_UNMARK = "unmark";
    private static final String COMMAND_TODO = "todo";
    private static final String COMMAND_DEADLINE = "deadline";
    private static final String COMMAND_EVENT = "event";
    private static final String COMMAND_DELETE = "delete";
    private static final String MESSAGE_DELETE = "Duke: Noted. I have deleted the task below: ";
    private static final String COMMAND_CLEAR = "clear";
    private static final String MESSAGE_CLEAR = "file has been cleared";

    private static void printFileContents(String filePath) throws FileNotFoundException {
        File f = new File(filePath); // create a File for the given file path
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        while (s.hasNext()) {
            System.out.println(s.nextLine());
        }
    }
    private static void appendToFile(String filePath, String textToAppend) throws IOException {
        FileWriter fw = new FileWriter(filePath, true); // create a FileWriter in append mode
        fw.write(textToAppend);
        fw.close();
    }
    private static void writeToFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(textToAdd);
        fw.close();
    }
    private static void clearFile(String filePath) throws IOException {
        FileWriter fw = new FileWriter(filePath, false);
        PrintWriter pw = new PrintWriter(fw, false);
        pw.flush();
        pw.close();
        fw.close();
    }
    public static void main(String[] args) {
        String directoryPath = "C:\\Users\\cwxky\\projects\\cs2113-git\\data";
        File directory = new File(directoryPath);
        if (!directory.exists()) {
            directory.mkdir();
            System.out.println("Directory has been created!");
        } else {
            System.out.println("Directory already exists.");
        }
        String textFilePath = "C:\\Users\\cwxky\\projects\\cs2113-git\\data\\duke.txt";
        File textFile = new File(textFilePath);
        try {
            if (!textFile.exists()) {
                textFile.createNewFile();
                System.out.println("File has been created!");
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("Duke has failed to make the file");
        }

        String line;
        ArrayList<Task> tasksList = new ArrayList<Task>();
        ArrayList<String> taskOverrideList = new ArrayList<String>();
        int count = 0;
        Scanner in = new Scanner(System.in);
        System.out.println("Duke: Hello! I'm Duke\n" + "Duke: What can I do for you?");

        while (true) {
            line = in.nextLine();
            String[] words = line.split(" ");
            switch (words[0]) {
            case COMMAND_BYE:
                System.out.println(MESSAGE_BYE);

                break;
            case COMMAND_LIST:
                try {
                    if (tasksList.isEmpty()) {
                        throw new EmptyListException(line);
                    } else {
                        System.out.println("Here are the tasks for today: ");
                        for (int i = 0; i < tasksList.size(); i++) {
                            System.out.println(tasksList.get(i));
                        }
                    }
                    printFileContents(textFilePath);
                } catch (EmptyListException e) {
                    System.out.println(e.getExceptionMessage());
                } catch (FileNotFoundException f) {
                    System.out.println("File not found.");
                }
                break;
            case COMMAND_MARK:
                int itemNumber = Integer.parseInt(line.split(" ")[1]) - 1;
                try {
                    if (itemNumber < 0 || itemNumber > tasksList.size() - 1) {
                        throw new IllegalTaskNumber("this task does not exist");
                    } else {
                        Task toBeChanged = tasksList.get(itemNumber);
                        if (toBeChanged.isDone) {
                            throw new IllegalMarkingException("already marked");
                        } else {
                            toBeChanged.markAsDone(toBeChanged);
                            String textToAdd = toBeChanged.toFileString();
                            taskOverrideList.set(itemNumber, textToAdd);
                            writeToFile(textFilePath, taskOverrideList.get(0));
                            for (int i = 1; i < taskOverrideList.size(); i++) {
                                appendToFile(textFilePath, taskOverrideList.get(i));
                            }
                        }
                    }
                } catch (IllegalMarkingException e) {
                    System.out.println(e.getExceptionMessage());
                } catch (IllegalTaskNumber f) {
                    System.out.println(f.getExceptionMessage());
                } catch (IOException g) {
                    System.out.println("Something went wrong. I am unable to mark this task.");
                }
                break;
            case COMMAND_UNMARK:
                itemNumber = Integer.parseInt(line.split(" ")[1]) - 1;
                try {
                    if (itemNumber < 0 || itemNumber > tasksList.size() - 1) {
                        throw new IllegalTaskNumber("this task does not exist");
                    } else {
                        Task toBeChanged = tasksList.get(itemNumber);
                        if (toBeChanged.isDone) {
                            toBeChanged.markAsUndone(toBeChanged);
                            String textToAdd = toBeChanged.toFileString();
                            taskOverrideList.set(itemNumber, textToAdd);
                            writeToFile(textFilePath, taskOverrideList.get(0));
                            for (int i = 1; i < taskOverrideList.size(); i++) {
                                appendToFile(textFilePath, taskOverrideList.get(i));
                            }
                        } else {
                            throw new IllegalUnmarkingException("still unmarked");
                        }
                    }
                } catch (IllegalUnmarkingException e) {
                    System.out.println(e.getExceptionMessage());
                } catch (IllegalTaskNumber f) {
                    System.out.println(f.getExceptionMessage());
                } catch (IOException g) {
                    System.out.println("Something went wrong. I am unable to unmark this task.");
                }
                break;
            case COMMAND_TODO:
                try {
                    if (line.equals("todo")) {
                        throw new EmptyDescriptionException(line);
                    } else {
                        String description = line.substring(line.indexOf("todo") + 5);
                        if (description.length() < 1) {
                            throw new EmptyDescriptionException(description);
                        } else {
                            Todo td = new Todo(description);
                            tasksList.add(td);
                            System.out.println("Got it. I've added this task: " + System.lineSeparator() +
                                    td + System.lineSeparator() + "Now you have " + tasksList.size() + " tasks in the list");
                            String textToAdd = "T | " + td.status + " | " + td.description + System.lineSeparator();
                            taskOverrideList.add(textToAdd);
                            appendToFile(textFilePath, textToAdd);
                        }
                    }
                } catch (EmptyDescriptionException e) {
                    System.out.println(e.getExceptionMessage());
                } catch (IOException f) {
                    System.out.println("Something went wrong. I am unable to add the todo task.");
                }
                break;
            case COMMAND_DEADLINE:
                try {
                    if (line.equals("deadline")) {
                        throw new EmptyDescriptionException(line);
                    } else {
                        String message = line.substring(line.indexOf("deadline") + 9);
                        if (message.length() < 1) {
                            throw new EmptyDescriptionException(message);
                        } else if (message.indexOf('/') == -1 || !message.contains("by")) {
                            throw new UnrecognisedDeadlineException(message);
                        } else {
                            String description = line.substring(line.indexOf("deadline") + 9, line.indexOf("/"));
                            String by = line.substring(line.indexOf("by") + 3);
                            Deadline d = new Deadline(description, by);
                            tasksList.add(d);
                            System.out.println("Got it. I've added this task: " + System.lineSeparator() +
                                    d + System.lineSeparator() + "Now you have " + tasksList.size() + " tasks in the list");
                            String textToAdd = "D | " + d.status + " | " + d.description + "| " + by + System.lineSeparator();
                            taskOverrideList.add(textToAdd);
                            appendToFile(textFilePath, textToAdd);
                        }
                    }
                } catch (EmptyDescriptionException e) {
                    System.out.println(e.getExceptionMessage());
                } catch (UnrecognisedDeadlineException f) {
                    System.out.println(f.getExceptionMessage());
                } catch (IOException g) {
                    System.out.println("Something went wrong. I am unable to add the deadline task.");
                }
                break;
            case COMMAND_EVENT:
                try {
                    if (line.equals("event")) {
                        throw new EmptyDescriptionException(line);
                    } else {
                        String message = line.substring(line.indexOf("event") + 6);
                        if (message.length() < 1) {
                            throw new EmptyDescriptionException(message);
                        } else if (message.indexOf('/') == -1 || !message.contains("at")) {
                            throw new UnrecognisedEventException(message);
                        } else {
                            String description = line.substring(line.indexOf("event") + 6, line.indexOf("/"));
                            String time = line.substring(line.indexOf("/at") + 4);
                            Event e = new Event(description, time);
                            tasksList.add(e);
                            if (tasksList.size() == 1) {
                                System.out.println("Got it. I've added this task: " + System.lineSeparator() +
                                        e + System.lineSeparator() + "Now you have 1 task in the list.");
                            } else {
                                System.out.println("Got it. I've added this task: " + System.lineSeparator() +
                                        e + System.lineSeparator() + "Now you have " + tasksList.size() + " tasks in the list.");
                            }
                            String textToAdd = "E | " + e.status + " | " + e.description + "| " + time + System.lineSeparator();
                            taskOverrideList.add(textToAdd);
                            appendToFile(textFilePath, textToAdd);
                        }
                    }
                } catch (EmptyDescriptionException e) {
                    System.out.println(e.getExceptionMessage());
                } catch (UnrecognisedEventException f) {
                    System.out.println(f.getExceptionMessage());
                } catch (IOException g) {
                    System.out.println("Something went wrong. I am unable to add the event task.");
                }
                break;
            case COMMAND_DELETE:
                itemNumber = Integer.parseInt(line.split(" ")[1]) - 1;
                Task toBeRemoved = tasksList.get(itemNumber);
                tasksList.remove(itemNumber);
                System.out.println(MESSAGE_DELETE);
                System.out.println(toBeRemoved.toString());
                System.out.println("You now have " + tasksList.size() + " tasks in the list.");
                break;
            case COMMAND_CLEAR:
                try {
                    clearFile(textFilePath);
                    System.out.println(COMMAND_CLEAR);
                } catch (IOException e) {
                    System.out.println("Something went wrong. I am unable to clear the file.");
                }
                break;
            default:
                try {
                    String message = "error";
                    throw new UnrecognisedInput(message);
                } catch (UnrecognisedInput f) {
                    System.out.println(f.getExceptionMessage());
                }
                break;
            }
        }
    }
}
