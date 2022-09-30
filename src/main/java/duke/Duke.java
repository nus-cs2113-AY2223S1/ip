package duke;
import java.io.*;
import java.util.Scanner;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;
import duke.exception.EmptyDescriptionException;
import duke.exception.InvalidCommandException;
import java.util.ArrayList;
import duke.utilityfunctions.Ui;
import duke.utilityfunctions.Filereader;
import duke.utilityfunctions.Command;
public class Duke {
    /**
     * Loads tasks from the data.txt file stored locally.
     * @param filepath the location in which the data.txt file is stored in
     * @param tasks the ArrayList in which tasks retrieved from the data.txt file will be stored in
     * @throws FileNotFoundException
     */
    public static void loadTasksFromTextFile(String filepath, ArrayList<Task> tasks) throws FileNotFoundException {
        File f = new File(filepath);
        Scanner s = new Scanner(f);
        Filereader textReader = new Filereader();
        while(s.hasNext()) {
            tasks.add(textReader.generateTask(s.nextLine()));
        }
        s.close();
    }

    /**
     * Updates the data.txt file with the new array of tasks
     * @param filepath the location in which the data.txt file is stored in
     * @param taskArray the ArrayList from which tasks will retrieved from, to update the data.txt file
     * @throws IOException
     */
    public static void updateFile(String filepath, ArrayList<Task> taskArray) throws IOException {
        FileWriter fw = new FileWriter(filepath);
        for(int i = 0; i < taskArray.size(); i++){
            if(i != taskArray.size() - 1){
                fw.write("[" + taskArray.get(i).getTaskIcon() + "]" + "[" + taskArray.get(i).getStatusIcon() + "] " + taskArray.get(i).getRawDescription() + System.lineSeparator());
            } else {
                fw.write("[" + taskArray.get(i).getTaskIcon() + "]" + "[" + taskArray.get(i).getStatusIcon() + "] " + taskArray.get(i).getRawDescription());
            }
        }
        fw.close();
    }

    public static void main(String[] args) {
        Ui ui = new Ui();
        Command command = new Command();
        ui.greet();
        ui.drawLine();
        ui.sayHello();
        ui.drawLine();
        Scanner in = new Scanner(System.in);
        String input;
        ArrayList<Task> taskArray = new ArrayList<Task>();
        // Create data.txt file if it doesn't exist
        try {
            File data = new File("data.txt");
            data.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // Load Tasks from Existing data.txt File
        try {
            loadTasksFromTextFile("data.txt", taskArray);
        } catch (FileNotFoundException e){
            System.out.println("File not found");
        }
        // Read input commands
        do {
            input = in.nextLine();
            try {
                String[] inputStrings = input.split(" ");
                if(!command.isValidCommand(inputStrings[0])){
                    throw new InvalidCommandException();
                }
                if(inputStrings.length == 1 && !inputStrings[0].equals("list") && !inputStrings[0].equals("bye")){
                    throw new EmptyDescriptionException(inputStrings[0]);
                }
            } catch (InvalidCommandException e){
                ui.drawLine();
                System.out.println("OOPS!!! I'm sorry, but I don't know what that means :-(");
                ui.drawLine();
                continue;
            }
            catch(EmptyDescriptionException e) {
                ui.drawLine();
                System.out.println("OOPS!!! The description of a " + e.getMessage() + " cannot be empty.");
                ui.drawLine();
                continue;
            }
            if(input.equals("bye")){
                ui.drawLine();
                ui.sayBye();
                ui.drawLine();
            } else if (input.equals("list")){
                ui.drawLine();
                ui.notifyListHeader();
                for (int i = 1; i <= taskArray.size(); i++){
                    ui.generateTaskStatus(taskArray.get(i-1).getTaskIcon(), taskArray.get(i-1).getStatusIcon(), taskArray.get(i-1).getDescription());
                }
                ui.drawLine();
            } else if (input.contains("unmark")){
                int choiceToUnMark = command.choiceParser(input);
                taskArray.get(choiceToUnMark - 1).unMarkTask();
                ui.notifyUnmarkedHeader();
                ui.generateTaskStatus(taskArray.get(choiceToUnMark - 1).getTaskIcon(), taskArray.get(choiceToUnMark - 1).getStatusIcon(),taskArray.get(choiceToUnMark - 1).getDescription() );
                try {
                    updateFile("data.txt", taskArray);
                } catch (IOException e) {
                    System.out.println("Something went wrong: "+e.getMessage());
                }
                ui.drawLine();
            } else if (input.contains("mark")){
                int choiceToMark = command.choiceParser(input);
                taskArray.get(choiceToMark - 1).markTask();
                ui.notifyMarkedHeader();
                ui.generateTaskStatus(taskArray.get(choiceToMark - 1).getTaskIcon(), taskArray.get(choiceToMark - 1).getStatusIcon(), taskArray.get(choiceToMark - 1).getDescription());
                try {
                    updateFile("data.txt", taskArray);
                } catch (IOException e) {
                    System.out.println("Something went wrong: "+e.getMessage());
                }
                ui.drawLine();
            } else if(input.contains("todo")) {
                taskArray.add(new Todo(input));
                ui.drawLine();
                ui.addedMsg();
                ui.generateTaskStatus(taskArray.get(taskArray.size() - 1).getTaskIcon(), taskArray.get(taskArray.size() - 1).getStatusIcon(), taskArray.get(taskArray.size() - 1).getDescription());
                ui.taskCountReminder(taskArray.size());
                try {
                    updateFile("data.txt", taskArray);
                } catch (IOException e) {
                    System.out.println("Something went wrong: "+e.getMessage());
                }
                ui.drawLine();
            } else if (input.contains("deadline")) {
                taskArray.add(new Deadline(input));
                ui.drawLine();
                ui.addedMsg();
                ui.generateTaskStatus(taskArray.get(taskArray.size() - 1).getTaskIcon(), taskArray.get(taskArray.size() - 1).getStatusIcon(), taskArray.get(taskArray.size() - 1).getDescription());
                ui.taskCountReminder(taskArray.size());
                try {
                    updateFile("data.txt", taskArray);
                } catch (IOException e) {
                    System.out.println("Something went wrong: "+e.getMessage());
                }
                ui.drawLine();
            } else if (input.contains("event")) {
                taskArray.add(new Event(input));
                ui.drawLine();
                ui.addedMsg();
                ui.generateTaskStatus(taskArray.get(taskArray.size() - 1).getTaskIcon(), taskArray.get(taskArray.size() - 1).getStatusIcon(), taskArray.get(taskArray.size() - 1).getDescription());
                ui.taskCountReminder(taskArray.size());
                try {
                    updateFile("data.txt", taskArray);
                } catch (IOException e) {
                    System.out.println("Something went wrong: "+e.getMessage());
                }
                ui.drawLine();
            } else if (input.contains("delete")) {
                ui.drawLine();
                int choiceToRemove = command.choiceParser(input);
                ui.notifyRemovedHeader();
                ui.generateTaskStatus(taskArray.get(choiceToRemove-1).getTaskIcon(), taskArray.get(choiceToRemove-1).getStatusIcon(), taskArray.get(choiceToRemove-1).getDescription());
                taskArray.remove(choiceToRemove - 1);
                ui.taskCountReminder(taskArray.size());
                try {
                    updateFile("data.txt", taskArray);
                } catch (IOException e) {
                    System.out.println("Something went wrong: "+e.getMessage());
                }
                ui.drawLine();
            }
            else {
                taskArray.add(new Task(input));
                ui.drawLine();
                ui.addedMsg();
                ui.generateTaskStatus(taskArray.get(taskArray.size() - 1).getTaskIcon(), taskArray.get(taskArray.size() - 1).getStatusIcon(), taskArray.get(taskArray.size() - 1).getDescription());
                ui.taskCountReminder(taskArray.size());
                try {
                    updateFile("data.txt", taskArray);
                } catch (IOException e) {
                    System.out.println("Something went wrong: "+e.getMessage());
                }
                ui.drawLine();
            }
        } while (!input.equals("bye"));
    }
}
