//import java.io.File;
//import java.io.FileNotFoundException;
//import java.io.FileWriter;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Scanner;
//
//public class ModifyList extends Constants {
//    private final List<Task> tasks = new ArrayList<>();
//    File file = new File("./data.txt");
//
//    public static String LINE {
//        return "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n";
//    }
//
//
//
//    public void loadFileData(){
//        try {
//            if (file.length() != 0) {
//                System.out.println("Loading existing file data...\n");
//                Scanner s = new Scanner(file);
//                while (s.hasNext()) {
////                    String task = s.nextLINE;
////                    tasks.add(taskFromFile(task));
//                }
//            } else {
//                System.out.println("No existing file data...creating empty list\n");
//            }
//        } catch (FileNotFoundException error) {
//            System.out.println(FILE_NOT_FOUND);
//        } catch (Error error) {
//            System.out.println(error.getMessage());
//            System.exit(0);
//        }
//    }
//
//    public void writeToFile() {
//        try {
//            FileWriter fw = new FileWriter(file);
//            for (Task task : tasks) {
//                fw.write(task.fileFormat() + System.lineSeparator());
//            }
//            fw.close();
//        } catch (IOException error) {
//            System.out.println(FILE_NOT_FOUND);
//        }
//    }
//
//    public void appendToFile(String textToAppend) {
//        try {
//            FileWriter fw = new FileWriter(file, true);
//            fw.write(textToAppend + System.lineSeparator());
//            fw.close();
//        } catch (IOException error) {
//            System.out.println(FILE_NOT_FOUND);
//        }
//    }
//
//    private void handleTask(String taskDetails, Task task) {
//        tasks.add(task);
//        System.out.println(
//                LINE +
//                        "Noted. Following task has been added: " + '\n' + taskDetails + "\n" +
//                        "Total tasks in list: " + tasks.size() + '\n' +
//                        LINE);
//        appendToFile(task.fileFormat());
//    }
//
//    public void task(String taskType, String details) throws Error {
//        String[] separateDetails;
//        String description;
//        String time;
//        switch (taskType) {
//            case "todo":
//                Todo task = new Todo(details, false);
//                handleTask(task.getDescriptionAndStatus(), task);
//                break;
//
//            case "deadline":
//                separateDetails = details.split(" /by ", 2);
//                if (separateDetails.length != 2) {
//                    throw new Error(DEADLINE_FORMAT_ERROR);
//                }
//                else if (separateDetails[0].equals("")) {
//                    throw new Error(DEADLINE_DESCRIPTION_ERROR);
//                }
//                description = separateDetails[0];
//                time = "(by:" + separateDetails[1] + ")";
//                Deadline deadline = new Deadline(description, time, false);
//                handleTask(deadline.getDescriptionAndStatus(), deadline);
//                break;
//
//            case "event":
//                separateDetails = details.split(" /at ", 2);
//                if (separateDetails.length != 2) {
//                    throw new Error(EVENT_FORMAT_ERROR);
//                }
//                else if (separateDetails[0].equals("")) {
//                    throw new Error(EVENT_DESCRIPTION_ERROR);
//                }
//                description = separateDetails[0];
//                time = "(at:" + separateDetails[1] + ")";
//                Event event = new Event(description, time, false);
//                handleTask(event.getDescriptionAndStatus(), event);
//                break;
//        }
//    }
//
//    public void list() {
//        int itemNumber = 1;
//        System.out.println(LINE + "Here are your list of tasks:");
//        for (Task task : tasks) {
//            System.out.println(itemNumber + "." + task.getDescriptionAndStatus());
//            itemNumber++;
//        }
//        System.out.println(LINE);
//    }
//
//    public void mark(int index) {
//        Task task = tasks.get(index - 1);
//        task.setDone(true);
//        tasks.set(index - 1, task);
//        System.out.println(
//                LINE +
//                        "The following task been marked as completed:\n" +
//                        task.getDescriptionAndStatus() + "\n" +
//                        LINE);
//        writeToFile();
//    }
//
//    public void delete(int index) {
//        Task task = tasks.get(index - 1);
//        tasks.remove(index - 1);
//        System.out.println(
//                LINE +
//                        "OK! I will remove the following task:\n" +
//                        task.getDescriptionAndStatus() + "\n" +
//                        "Total tasks in list: " + tasks.size() + '\n' +
//                        LINE);
//        writeToFile();
//    }
//
//    public void unmark(int index) {
//        Task task = tasks.get(index - 1);
//        task.setDone(false);
//        tasks.set(index - 1, task);
//        System.out.println(
//                LINE +
//                        "The following task been marked as not done yet:\n" +
//                        task.getDescriptionAndStatus() + "\n" +
//                        LINE);
//        writeToFile();
//    }
//}