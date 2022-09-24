package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    public String filePath;
    public ArrayList<Task> tasks;
    public int numberOfTasks;

    public Storage(String filePath, ArrayList<Task> tasks, int numberOfTasks) {
        this.filePath = filePath;
        this.tasks = tasks;
        this.numberOfTasks = numberOfTasks;
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public int getNumberOfTasks() {
        return numberOfTasks;
    }

    public void readFile() throws FileNotFoundException {
        File file = new File(filePath);
        UI ui = new UI();

        try {
            boolean isExisting = file.createNewFile();
        } catch (IOException e) {
            ui.printLoadingError();
        }

        Scanner scanner = new Scanner(file);

        while (scanner.hasNext()) {
            String line = scanner.nextLine();
            String[] words = line.split(" ");
            try {
                String command = line.substring(2);
                if (words.length == 1) {
                    throw new DukeException();
                }
                Task newTask = new Task("");
                switch (words[1]) {
                case "todo" :
                    newTask = new Todo(command.substring(5));
                    break;
                case "deadline" :
                    String deadlineWords[] = command.substring(8).split("/");
                    newTask = new Deadline(deadlineWords[0].trim(), deadlineWords[1].substring(3));
                    break;
                case "event" :
                    String eventWords[] = command.substring(6).split("/");
                    newTask = new Event(eventWords[0].trim(), eventWords[1].substring(3));
                    break;
                }
                tasks.add(newTask);
                numberOfTasks++;
                if (words[0].equals("1")) {
                    try {
                        Task currentTask = tasks.get(Integer.parseInt(String.valueOf(numberOfTasks)) - 1);
                        currentTask.markAsDone();
                        rewriteFile();
                    } catch (NullPointerException e) {
                        ui.printIndexError();
                    } catch (IOException e) {
                        ui.printLoadingError();
                    }
                }
            } catch (DukeException e) {
                ui.printUnrecognizedCommandError();
            }
        }
    }

    public void appendToFile(String command) throws IOException {
        FileWriter fw = new FileWriter(filePath, true);
        fw.write("0 " + command + "\n");
        fw.close();
    }

    public void rewriteFile() throws IOException {
        FileWriter fw = new FileWriter("duke.txt");
        String number;
        for (Task task : tasks) {
            if (task.isDone) {
                number = "1";
            } else {
                number = "0";
            }
            if (task instanceof Todo) {
                fw.write(number + " todo " + task.description + "\n");
            } else if (task instanceof Deadline) {
                fw.write(number + " deadline " + task.description + " /by " + ((Deadline) task).by + "\n");
            } else if (task instanceof Event) {
                fw.write(number + " event " + task.description + " /at " + ((Event) task).at + "\n");
            }
        }
        fw.close();
    }
}
