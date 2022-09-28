import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.util.Scanner;

public class DukeManager {
    private static final String HORIZONTAL_LINE = "____________________________________________________________\n";
    private static final String ADD_CONFIRMATION_MESSAGE = "Got it. I've added this task:";
    private static final Path directoryPath = Paths.get(".","data");
    private static final Path filePath = Paths.get(".","data", "duke.txt");
    private static ArrayList<Task> inputs = new ArrayList<>();

    public static void print(String string) {
        System.out.println(HORIZONTAL_LINE + string + "\n" + HORIZONTAL_LINE);
    }

    private static String saveList() {
        String formattedString = "";
        for (int i = 0; i < inputs.size(); i++) {
            if (inputs.get(i) == null) {
                break;
            }
            formattedString += inputs.get(i).toSaveString() + "\n";
        }
        return formattedString;
    }

    private static void checkExists() throws IOException {
        boolean directoryExists = Files.exists(directoryPath);
        boolean fileExists = Files.exists(filePath);
        if (!directoryExists) {
            Files.createDirectories(directoryPath);
        }
        if (!fileExists) {
            Files.createFile(filePath);
        }
    }

    public static void save() {
        try {
            checkExists();
        } catch (IOException e) {
            print("Something went wrong when trying to save: " + e.getMessage());
            return;
        }
        String pathName = "./data/duke.txt";
        try {
            FileWriter fw = new FileWriter(pathName);
            fw.write(saveList());
            fw.close();
        } catch (IOException e) {
            print("☹ OOPS!!! Something went wrong when trying to save: " + e.getMessage());
            return;
        }
    }

    public static void load() {
        if (Files.exists(directoryPath) && Files.exists(filePath)) {
            File f = new File(String.valueOf(filePath)); // create a File for the given file path
            Scanner s = null;
            try {
                s = new Scanner(f); // create a Scanner using the File as the source
            } catch (FileNotFoundException e) {
                print("☹ OOPS!!! Something went wrong when trying to load: " + e.getMessage());
            }
            while (s.hasNext()) {
                String[] line = s.nextLine().split("\\|");
                Task newTask = null;
                if (line[0].equals("T")) {
                    try {
                        newTask = new Todo(line[2]);
                    } catch (DukeException e) {
                        print("☹ OOPS!!! Something went wrong when trying to load: " + e.getMessage());
                    }
                } else if (line[0].equals("D")) {
                    try {
                        newTask = new Deadline(line[2], line[3]);
                    } catch (DukeException e) {
                        print("☹ OOPS!!! Something went wrong when trying to load: " + e.getMessage());
                    }
                } else {
                    try {
                        newTask = new Event(line[2], line[3]);
                    } catch (DukeException e) {
                        print("☹ OOPS!!! Something went wrong when trying to load: " + e.getMessage());
                    }
                }
                if (line[1].equals("1")) {
                    newTask.markAsDone();
                } else {
                    newTask.markAsNotDone();
                }
                inputs.add(newTask);
            }
        }
    }
    private static String formatList(ArrayList inputList) {
        String formattedString = "";
        for (int i = 0; i < inputList.size(); i++) {
            if (inputList.get(i) == null) {
                break;
            }
            formattedString += (i+1) + "." + inputList.get(i).toString() + "\n";
        }
        return formattedString;
    }
    /**
     * Returns a string with formatted user inputs.
     *
     * @return Formatted string to print
     */

    public static void list() {
        System.out.println(HORIZONTAL_LINE + formatList(inputs) + HORIZONTAL_LINE);
    }

    public static void delete(String line) {
        int input;
        try {
            input = Integer.valueOf(line.replace("delete", "").strip());
        } catch (NumberFormatException e) {
            print("☹ OOPS!!! You must specify a number in the list to unmark. Format: unmark #");
            return;
        }
        try {
            Task task = inputs.get(input - 1);
            inputs.remove(input - 1);
            print("Noted. I've removed this task:\n" + task +"\nNow you have " + inputs.size() + " tasks in the list.");

        } catch (IndexOutOfBoundsException e) {
            print("☹ OOPS!!! You must specify a number in the list to delete. Format: delete #");
            return;
        }
    }
    public static void unmark(String line) {
        int input;
        try {
            input = Integer.valueOf(line.replace("unmark", "").strip());
        } catch (NumberFormatException e) {
            print("☹ OOPS!!! You must specify a number in the list to unmark. Format: unmark #");
            return;
        }
        if (input <= 0 || input > inputs.size()) {
            print("☹ OOPS!!! You must specify a number in the list.");
            return;
        }
        Task task = inputs.get(input - 1);
        task.markAsNotDone();
        print("OK, I've marked this task as not done yet: \n  " + task);
    }

    public static void mark(String line) {
        int input;
        try {
            input = Integer.valueOf(line.replace("mark", "").strip());
        } catch (NumberFormatException e) {
            print("☹ OOPS!!! You must specify a number in the list to mark. Format: mark #");
            return;
        }
        if (input <= 0 || input > inputs.size()) {
            print("☹ OOPS!!! You must specify a number in the list.");
            return;
        }
        Task task = inputs.get(input - 1);
        task.markAsDone();
        print("Nice! I've marked this task as done: \n  " + task);
    }

    public static void createTodo(String line) {
        String description = line.replace("todo", "").strip();
        Todo todo;
        try {
            todo = new Todo(description);
        } catch (DukeException e) {
            print("☹ OOPS!!! The description of a todo cannot be empty.");
            return;
        }
        inputs.add(todo);
        print(ADD_CONFIRMATION_MESSAGE + "\n  " + todo + "\nNow you have "+ inputs.size() + " tasks in the list.");
    }

    public static void createEvent(String line) {
        String removedCommand = line.replace("event", "");
        int timeIndex = removedCommand.indexOf("/at");
        String description;
        try {
            description = removedCommand.substring(0, timeIndex).strip();
        } catch (StringIndexOutOfBoundsException e) {
            print("☹ OOPS!!! ensure that you include /at. Format: event (description) /at (date)");
            return;
        }

        String date = removedCommand.substring(timeIndex + "/at".length()).strip();
        Event event;
        try {
            event = new Event(description, date);
        } catch (DukeException e) {
            print("☹ OOPS!!! The description and date of an event cannot be empty.");
            return;
        }
        inputs.add(event);
        print(ADD_CONFIRMATION_MESSAGE + "\n  " + event + "\nNow you have "+ inputs.size() + " tasks in the list.");
    }

    public static void createDeadline(String line) {
        String removedCommand = line.replace("deadline", "");
        int timeIndex = removedCommand.indexOf("/by");
        String description = "";
        try {
            description = removedCommand.substring(0, timeIndex).strip();
        } catch (StringIndexOutOfBoundsException e) {
            print("☹ OOPS!!! ensure that you include /by. Format: deadline (description) /by (date)");
            return;
        }
        if (description.isBlank()) {
            print("☹ OOPS!!! The description of a deadline cannot be empty.");
            return;
        }
        String date = removedCommand.substring(timeIndex + "/by".length()).strip();
        Deadline deadline;
        try {
            deadline = new Deadline(description, date);
        } catch (DukeException e) {
            print("☹ OOPS!!! The date and description of a deadline cannot be empty.");
            return;
        }
        inputs.add(deadline);
        print(ADD_CONFIRMATION_MESSAGE + "\n  " + deadline + "\nNow you have "+ inputs.size() + " tasks in the list.");
    }

    public static void find(String line) {
        ArrayList<Task> matchedTerms = new ArrayList<>();
        String searchTerm = line.replace("find", "").strip();
        for (int i = 0; i < inputs.size(); i++) {
            if (inputs.get(i).getDescription().contains(searchTerm)) {
                matchedTerms.add(inputs.get(i));
            }
        }
        System.out.println(HORIZONTAL_LINE + "Here are the matching tasks in your list:\n" + formatList(matchedTerms) + HORIZONTAL_LINE);
    }
}
