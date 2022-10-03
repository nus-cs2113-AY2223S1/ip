import Tasks.*;
import Exceptions.*;

import java.util.Scanner;
import java.util.Vector;
import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Objects;

public class Duke {
    protected static final int EVENT_KEYWORD_LENGTH = 6;
    protected static final int DEADLINE_KEYWORD_LENGTH = 9;
    private static final String FILE_DIRECTORY_LOCATION = Paths.get(".", "save").toString(); ////////
    protected static final String DATA_FILE_NAME = "duke" + ".txt";
    private static final String DATA_FILE_PATH = Paths.get(FILE_DIRECTORY_LOCATION, DATA_FILE_NAME).toString();//////////
    //protected static final String DATA_FILE_PATH = "src/main/java/" + DATA_FILE_NAME;
    protected static final String SEPARATOR = "|";
    protected static final String TASK_DONE_FLAG = "1";
    protected static final String TASK_NOT_DONE_FLAG = "0";
    public static Vector<Task> tasks = new Vector<>(); // list of tasks

    public static void createFile() { // create file if it is not present
        File fileHandle = new File(DATA_FILE_PATH);
        if (!fileHandle.exists()) {
            try {
                fileHandle.createNewFile();
            } catch (IOException e) {
                System.out.println(fileHandle.getParentFile().mkdirs()); ////////
                System.out.println("Error - An error has occurred when creating 'duke.txt': ");
                System.out.println(e.getMessage());
            }
        }
    }

    public static void fileParse(String filePath) {
        File fileHandler = new File(DATA_FILE_PATH);
        Scanner fileScanner = null; 
        try {
            fileScanner = new Scanner(fileHandler);
            fileScanner.useDelimiter(System.lineSeparator());
        } catch (FileNotFoundException e) {
            System.out.println("Error - 'duke.txt' not found");
        }

        while (fileScanner.hasNext()) {
            String nextTask = fileScanner.next();
            String[] taskTypes = nextTask.split(SEPARATOR);
            Boolean taskDone = null;

            switch (taskTypes[0]) {
            case "T":
                if (Objects.equals(taskTypes[1], TASK_DONE_FLAG)) {
                    taskDone = true;
                } else if ((Objects.equals(taskTypes[1], TASK_NOT_DONE_FLAG))) {
                    taskDone = false;
                }
                tasks.add(new ToDo(taskTypes[2], taskDone));
                break;

            case "D":
                if (Objects.equals(taskTypes[1], TASK_DONE_FLAG)) {
                    taskDone = true;
                } else if ((Objects.equals(taskTypes[1], TASK_NOT_DONE_FLAG))) {
                    taskDone = false;
                }
                tasks.add(new Deadline(taskTypes[2], taskDone, taskTypes[3]));
                break;

            case "E":
                if (Objects.equals(taskTypes[1], TASK_DONE_FLAG)) {
                    taskDone = true;
                } else if ((Objects.equals(taskTypes[1], TASK_NOT_DONE_FLAG))) {
                    taskDone = false;
                }
                tasks.add(new Event(taskTypes[2], taskDone, taskTypes[3]));
                break;

            default:
                break;
            }
        }
    }

    public static void writeDisk(String newContent) throws IOException {
        File fileHandle = new File(DATA_FILE_PATH);
        try (FileWriter fileWriter = new FileWriter(fileHandle)) {
        if (!fileHandle.exists()){
            fileHandle.createNewFile();
        }
        fileWriter.write(newContent);
        fileWriter.close();
        } catch (IOException e) { // any exceptions, file will be deleted
            fileHandle.delete();
        }
    }
    
    public static String parseTaskToString(Vector <Task> tasks) {
        String resultList = "";
        String taskString = "";

        for (int i = 1; i <= tasks.size(); i++) {
            String taskType = tasks.get(i - 1).getTaskType();
            String markDone;
            if (tasks.get(i - 1).getTaskDone()) {
                markDone = TASK_DONE_FLAG;
            } else {
                markDone = TASK_NOT_DONE_FLAG;
            }

            switch (taskType) {
            case "ToDo":
                taskString = "T " + SEPARATOR + " " + markDone + " " + SEPARATOR + " " + tasks.get(i - 1).getTaskTitle() +
                        System.lineSeparator();
                resultList += taskString;
                break;

            case "Deadline":
                String deadline = tasks.get(i - 1).getTaskDeadline();
                taskString = "D " + SEPARATOR + " " + markDone + " " + SEPARATOR + " " + tasks.get(i - 1).getTaskTitle() +
                        " " + SEPARATOR + " " + deadline + System.lineSeparator();
                resultList += taskString;
                break;

            case "Event":
                String eventTime = tasks.get(i - 1).getTaskDate();
                taskString = "E " + SEPARATOR + " " + markDone + " " + SEPARATOR + " " + tasks.get(i - 1).getTaskTitle() +
                        " " + SEPARATOR + " " + eventTime + System.lineSeparator();
                resultList += taskString;
                break;

            default:
                break;
            }
        }
        return resultList;
    }

    
    
    
    
    
    public static void printGreet(){
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke_HTT. \n What can I do for you?");
    }

    protected static String getTaskMark(String[] inLine) {
        int inputTaskIndex = Integer.parseInt(inLine[1]);
        String taskType = tasks.get(inputTaskIndex - 1).getTaskType();
        String taskMark = "";
        switch (taskType) {
        case "ToDo":
            taskMark = "[T]";
            break;

        case "Deadline":
            taskMark = "[D]";
            break;

        case "Event":
            taskMark = "[E]";
            break;

        default:
            break;
        }
        return taskMark;
    }

    public static void processInput(){   
        String inData = "";
        Scanner scan = new Scanner ( System.in ); 
        inData = scan.nextLine(); // user line of input
        String[] inLine = inData.split(" "); // code process line of input into an array
        
        boolean isFirstLine = false; 

        while (!inLine[0].equals("bye")){
            if (isFirstLine) { // kick start the loop if first time
                inData = scan.nextLine();
                inLine = inData.split(" ");
            } else {
                isFirstLine = true;
            }

            int inputTaskIndex = Integer.parseInt(inLine[1]);
            int taskIndex = inputTaskIndex - 1;
            switch (inLine[0]){
            case "list":
                printList(tasks);
                break;

            case "mark":
            try {
                if (inputTaskIndex > tasks.size() || inputTaskIndex < 1) {
                    throw new InvalidListIndexException();
                }

                tasks.get(taskIndex).setTaskDone(true);
                
                String taskMark = getTaskMark(inLine);
                String doneMark;
                if (tasks.get(taskIndex).getTaskDone()){
                    doneMark = "[X]";
                } else{
                    doneMark = "[ ]";
                }

                System.out.println("Nice! I've marked this task as done: \n" + taskMark + doneMark +
                        tasks.get(taskIndex).getTaskTitle());

                String newEntry = parseTaskToString(tasks);
                writeDisk(newEntry);
            } catch (InvalidListIndexException | NumberFormatException e) {
                System.out.println("Error - List index given invalid. Please check again.");
            } catch (IOException e) {
                System.out.println("Error when saving file: " + e.getMessage());
            }
            break;
            
            case "unmark":
            try {
                if (inputTaskIndex > tasks.size() || inputTaskIndex < 1) {
                    throw new InvalidListIndexException();
                }

                tasks.get(taskIndex).setTaskDone(false);


                String taskMark = getTaskMark(inLine);
                String doneMark;
                if (tasks.get(taskIndex).getTaskDone()){
                    doneMark = "[X]";
                } else{
                    doneMark = "[ ]";
                }

                System.out.println("Nice! I've marked this task as not done: \n" + taskMark + doneMark +
                        tasks.get(taskIndex).getTaskTitle());

                String newEntry = parseTaskToString(tasks);
                writeDisk(newEntry);
            } catch (InvalidListIndexException | NumberFormatException e) {
                System.out.println("Error - List index given invalid. Please check again.");
            } catch (IOException e) {
                System.out.println("Error when saving file: " + e.getMessage());
            }
            break;
            
            case "todo":
            try {
                if (inLine.length <= 1) {
                    throw new InvalidTodoException();
                }

                tasks.add(new ToDo(inData.substring(5)));

                System.out.println("Got it. I've added this task: \n" + "[T][ ] "
                        + inData.substring(5) ); //////////magic number problem
                System.out.println("Now you have " + tasks.size() +  " tasks in the list.");

                String newEntry = parseTaskToString(tasks);
                writeDisk(newEntry);
            } catch (InvalidTodoException e) {
                System.out.println("Error - Please input a description for your To do. ");
            } catch (IOException e) {
                System.out.println("Error when saving file: " + e.getMessage());
            }
            break;

            case "event":
                try {
                    int taskEndIndex = inData.indexOf("/at");
                    if (taskEndIndex == -1) { // no "/at"
                        throw new InvalidEventException();
                    } else if (taskEndIndex <= EVENT_KEYWORD_LENGTH) { // no task title
                        throw new InvalidTaskTitleException();
                    } else if (taskEndIndex + 3 <= inData.length()) { // no task date
                        throw new InvalidTaskDateException();
                    }

                    String eventTitle = inData.substring(EVENT_KEYWORD_LENGTH,taskEndIndex); 
                    String eventDate = inData.substring(taskEndIndex + 3); /////magic number, "/at"
                    tasks.add(new Event(eventTitle, eventDate));

                    System.out.println("Got it. I've added this event: \n" + "[E] [ ]"
                            + eventTitle + " (at: " + eventDate + ")");
                    System.out.println("Now you have " + tasks.size() +  " tasks in the list.");

                    String newEntry = parseTaskToString(tasks);
                    writeDisk(newEntry);
                } catch (InvalidEventException e) {
                    System.out.println("Error - '/at' must be included.");
                } catch (InvalidTaskTitleException e) {
                    System.out.println("Error - input an event description");
                } catch (InvalidTaskDateException e) {
                    System.out.println("Error - input an event date.");
                } catch (IOException e) {
                    System.out.println("Error when saving file: " + e.getMessage());
                }
                break;

            case "deadline":
                try {
                    int taskEndIndex = inData.indexOf("/by");
                    if (taskEndIndex == -1) { // no "/by"
                        throw new InvalidDeadlineException();
                    } else if (taskEndIndex <= DEADLINE_KEYWORD_LENGTH) { // no task title
                        throw new InvalidTaskTitleException();
                    } else if (taskEndIndex + 3 <= inData.length()) { // no task date
                        throw new InvalidTaskDateException();
                    }

                    String deadlineTitle = inData.substring(DEADLINE_KEYWORD_LENGTH, taskEndIndex); 
                    String deadlineDate = inData.substring(taskEndIndex + 3); //magic number, "/by"
                    tasks.add(new Deadline(deadlineTitle, deadlineDate));

                    System.out.println("Got it. I've added this deadline: \n" + "[D] [ ]"
                            + deadlineTitle + " (at: " + deadlineDate + ")");
                    System.out.println("Now you have " + tasks.size() +  " tasks in the list.");


                    String newEntry = parseTaskToString(tasks);
                    writeDisk(newEntry);


                } catch (InvalidDeadlineException e){
                    System.out.println("Error - '/by' must be included.");
                } catch (InvalidTaskTitleException e) {
                    System.out.println("Error - input a deadline description");
                } catch (InvalidTaskDateException e) {
                    System.out.println("Error - input a deadline date.");
                } catch (IOException e) {
                    System.out.println("Error when saving file: " + e.getMessage());
                }
                break;

            case "delete":
            try {
                if ((inputTaskIndex) > tasks.size() || (inputTaskIndex) < 1) {
                    throw new InvalidListIndexException();
                }

                String taskMark = getTaskMark(inLine);
                String doneMark;
                if (tasks.get(taskIndex).getTaskDone()){
                    doneMark = "[X]";
                } else{
                    doneMark = "[ ]";
                }
                System.out.println("Noted. I have removed this task:");
                System.out.println(taskMark + doneMark + " " +
                        tasks.get(taskIndex).getTaskTitle());
                System.out.println("Now you have " + tasks.size() + " tasks in the list.");

                tasks.remove(taskIndex);
            } catch (InvalidListIndexException | NumberFormatException e) {
                System.out.println("Error - List index given invalid. Please check again.");
            }
            break;

            case "bye":
                break;

            default:
            try {
                throw new InvalidGeneralInputException(); // catch all other exceptions
            } catch (InvalidGeneralInputException e) {
                System.out.println("Error - Sorry, I do not understand this input.");
            }
            }
        }
    }
  
    protected static void printGoodbyeMeessage(){
        System.out.println("Bye. Hope to see you again soon!");
    }

    public static void printList(Vector <Task> tasks){
        for (int i = 0; i < tasks.size(); i++) {
            String taskType = tasks.get(i).getTaskType();
            String doneMark;
            int outputIndex = i + 1;
            if (tasks.get(i).getTaskDone()){
                doneMark = "[X]";
            } else{
                doneMark = "[ ]";
            }

            switch(taskType) {
            
            case "ToDo":
                System.out.println("[T]" + doneMark + " " + outputIndex + ". " + tasks.get(i).getTaskTitle());
                break;

            case "Deadline":
                String deadline = tasks.get(i).getTaskDeadline();
                //String outputIndex = i + 1;
                System.out.println("[D]" + doneMark + " " + outputIndex + ". " + tasks.get(i).getTaskTitle() +
                        " (by: " + deadline + ")");
                break;

            case "Event":
                String eventDate = tasks.get(i).getTaskDate();
                //String outputIndex = i + 1;
                System.out.println("[E]" + doneMark + " " + outputIndex + ". " + tasks.get(i).getTaskTitle() +
                        " (at: " + eventDate + ")");
                break;

            default:
                break;
            }
        }
    }

    public static void main(String[] args) {
        printGreet();
        createFile();
        fileParse(DATA_FILE_PATH);
        processInput();
        printGoodbyeMeessage();  
    }
}
