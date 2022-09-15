import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
public class Duke {
    public static int numberOfTasks = 0;
    public static ArrayList<Task> myList = new ArrayList<Task>();

    public static void main(String[] args) throws MethodNotRecognisedException {

        String GREETING = "____________________________________________________________\n" +
                " __      __                       \n" +
                "/  \\    /  \\ ____   ____    ____  \n" +
                "\\   \\/\\/   // __ \\ /    \\  / ___\\ \n" +
                " \\        /\\  ___/|   |  \\/ /_/  >\n" +
                "  \\__/\\  /  \\___  >___|  /\\___  / \n" +
                "       \\/       \\/     \\//_____/  " +
                "\n Hello! I'm Weng!\n" +
                " What can I do for ya?\n" +
                "____________________________________________________________\n";
        System.out.println("\n" + GREETING);


        while (true) {
            Scanner sc = new Scanner(System.in);
            String input = sc.nextLine();
            String words[] = input.split(" ");
            try {
                if (input.startsWith("bye")) {
                    sayBye();
                    break;
                } else if (input.startsWith("list")) {
                    printList();
                } else if (input.startsWith("mark")) {
                    markTask(words);
                } else if (input.startsWith("unmark")) {
                    unmarkTask(words);
                } else if (input.startsWith("todo")) {
                    addTodo(input);
                } else if (input.startsWith("event")) {
                    addEvent(input);
                } else if (input.startsWith("deadline")) {
                    addDeadline(input);
                } else if (input.startsWith("delete")) {
                    myList.remove(Integer.parseInt(words[1]) - 1);
                    numberOfTasks--;
                } else {
                    System.out.println("OOPS!!! I'm sorry, but I don't know what that means :-(");
                }

                saveTasks();

            } catch (NoTaskSpecifiedException e) {
                System.out.println("OOPS!!! I'm sorry, no task specified :-(");
            }
        }
    }

    private static void sayBye() {
        String EXIT_MESSAGE = "____________________________________________________________\n" +
                " Bye. Hope to cya again soon!\n" +
                "____________________________________________________________";
        System.out.println(EXIT_MESSAGE);
    }

    private static void printList() {
        for (int i = 0; i < numberOfTasks; i++) {
            System.out.print(i + 1);
            System.out.print(".");
            System.out.println(myList.get(i).toString());
        }
        System.out.println("You have "+numberOfTasks+" tasks in the list");
    }

    private static void markTask(String[] words) {
        int index = Integer.parseInt(words[1]);
        myList.get(index - 1).markAsDone();
        System.out.println("____________________________________________________________\n" +
                "Nice! I've marked this task as done:\n" +
                myList.get(index - 1).getStatusIcon() + " " + myList.get(index - 1).description +
                "\n____________________________________________________________");
    }

    private static void unmarkTask(String[] words) {
        int index = Integer.parseInt(words[1]);
        myList.get(index - 1).markAsNotDone();
        System.out.println("____________________________________________________________\n" +
                "     Ok, I've marked this task as not done yet:\n" +
                myList.get(index - 1).getStatusIcon() + " " + myList.get(index - 1).description +
                "    ____________________________________________________________");
    }

    private static void addDeadline(String input) {
        String description = input.substring(input.indexOf(" ") + 1, input.indexOf("/by") - 1);
        String by = input.substring(input.indexOf("/by") + 4);
        Task newElement = new Deadline(description, by);
        myList.add(newElement);
        numberOfTasks++;
        printCurrentTaskMessage();
    }

    private static void addEvent(String input) {
        String description = input.substring(input.indexOf(" ") + 1, input.indexOf("/at") - 1);
        String at = input.substring(input.indexOf("/at") + 4);
        Task newElement = new Event(description, at);
        myList.add(newElement);
        numberOfTasks++;
        printCurrentTaskMessage();
    }

    private static void addTodo(String input) throws NoTaskSpecifiedException{
        String words[] = input.split(" ");
        if(words.length <  2){
            throw new NoTaskSpecifiedException();
        }
        String description = input.substring(input.indexOf(" ") + 1);
        Task newElement = new Todo(description);
        myList.add(newElement);
        numberOfTasks++;
        printCurrentTaskMessage();
    }

    private static void printCurrentTaskMessage(){
        System.out.println("Got it. I've added this task:");
        System.out.println(myList.get(numberOfTasks-1).toString());
        System.out.println("Now you have "+numberOfTasks+" tasks in the list");
    }

    private static void writeToFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(textToAdd);
        fw.close();
    }
    private static void saveTasks() {
        String home = System.getProperty("user.home");
        boolean directoryExists = new java.io.File(home + "/Desktop/tasks.txt").exists();
        if(!directoryExists){
            System.out.println("directory does not exist");
        }
        String filePath = home + "/Desktop/tasks.txt";
        String fileContent = "";
        for (int i = 0; i < numberOfTasks; i++) {
            fileContent += (myList.get(i).toString());
            fileContent += "\n";
        }
        try {
            writeToFile(filePath, fileContent);
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }
//    private static void retrieveTasks() {
//        String home = System.getProperty("user.home");
//        boolean directoryExists = new java.io.File(home + "/Desktop/tasks.txt").exists();
//        if(!directoryExists){
//            System.out.println("directory does not exist");
//        }
//        String filePath = home + "/Desktop/tasks.txt";
//        Scanner s = new Scanner(filePath);
//        while (s.hasNext()) {
//            String next = s.nextLine();
//            if(next.charAt(1) == 'T'){
//                addTodo();
//            }
//        }
//    }
}

