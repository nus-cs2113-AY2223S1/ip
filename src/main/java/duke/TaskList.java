package duke;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import duke.tasks.Deadlines;
import duke.tasks.Events;
import duke.tasks.Task;
import duke.tasks.Todos;


public class TaskList {
    public static final String END_OF_LINE = "\n____________________";
    private static List<Task> tasks = new ArrayList<Task>();

    public String addNewTodo(String taskName, boolean toPrint) {
        tasks.add(new Todos(taskName));
        if (toPrint) {
            return ("Added new todo task: " + taskName + "\n " + tasks.get(tasks.size() - 1).toString()
                    + "\nYou have " + tasks.size() + " tasks in the list." + END_OF_LINE);
        }
        return null;
    }

    public String addNewDeadline(String taskName, String toBeDoneBy, boolean toPrint) {
        String[] arrOfBy = toBeDoneBy.split(" ");
        tasks.add(new Deadlines(taskName, toBeDoneBy, LocalDate.parse(arrOfBy[0]), LocalTime.parse(arrOfBy[1])));
        if (toPrint) {
            return ("Added new deadline task: " + taskName + "\n " + tasks.get(tasks.size() - 1).toString()
                    + "\nYou have " + tasks.size() + " tasks in the list." + END_OF_LINE);
        }
        return null;
    }

    public String addNewEvent(String taskName, String happeningAt, boolean toPrint) {
        String[] arrOfAt = happeningAt.split(" ");
        tasks.add(new Events(taskName, happeningAt, LocalDate.parse(arrOfAt[0]), LocalTime.parse(arrOfAt[1])));
        if (toPrint) {
            return ("Added new event task: " + taskName + "\n " + tasks.get(tasks.size() - 1).toString()
                    + "\nYou have " + tasks.size() + " tasks in the list." + END_OF_LINE);
        }
        return null;
    }

    public String listTasks() {
        if (tasks.size() == 0) {
            return Printables.NO_TASKS_IN_LIST_MESSAGE;
        }

        String list = "Here is your list of tasks:\n";
        for (int i = 0; i < tasks.size(); i++) {
            list += (i + 1) + ". " + tasks.get(i).toString() + "\n";
        }
        return list + END_OF_LINE;
    }

    public String markTasks(boolean isMark, int taskIndex, boolean isPrint)
            throws DukeException.IllegalMarkTargetException {
        String responseLine;
        if (taskIndex < 1 || taskIndex > tasks.size()) {
            throw new DukeException.IllegalMarkTargetException();
        }

        if (isMark) {
            if (tasks.get(taskIndex - 1).getIsDone()) {
                return Printables.ALREADY_MARKED_MESSAGE;
            }
            tasks.get(taskIndex - 1).setIsDone();
            responseLine = tasks.get(taskIndex - 1).getTaskName()
                    + " has been marked as done!" + END_OF_LINE;
        } else {
            if (!tasks.get(taskIndex - 1).getIsDone()) {
                return Printables.ALREADY_UNMARKED_MESSAGE;
            }
            tasks.get(taskIndex - 1).setIsNotDone();
            responseLine = tasks.get(taskIndex - 1).getTaskName()
                    + " has been unmarked!" + END_OF_LINE;
        }

        if (isPrint) {
            return responseLine;
        }
        return null;
    }

    public int getNumberOfTasks() {
        return tasks.size();
    }

    public String saveTaskList() {
        String data = "";
        for (Task task : tasks) {
            String type = task.getType();
            if (type.equals("T")) {
                Todos temp = (Todos) task;
                data += (type + "|" + temp.getTaskName() + "|" + (temp.getIsDone() ? "1" : "0") + "\n");
            } else if (type.equals("D")) {
                Deadlines temp = (Deadlines) task;
                data += (type + "|" + temp.getTaskName() + "|" + (temp.getIsDone() ? "1" : "0") + "|"
                        + temp.getToBeDoneBy() + "\n");
            } else {
                Events temp = (Events) task;
                data += (type + "|" + temp.getTaskName() + "|" + (temp.getIsDone() ? "1" : "0") + "|"
                        + temp.getHappeningAt() + "\n");
            }
        }
        return data;
    }

    public String deleteTask(int taskIndex) throws DukeException.IllegalDeleteTargetException {
        String response;
        String taskDescription;
        try {
            taskDescription = tasks.get(taskIndex - 1).toString();
            tasks.remove(taskIndex - 1);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException.IllegalDeleteTargetException();
        }
        response = "Deleted: " + taskDescription  + "\nYou have " + tasks.size()
                + " tasks in the list." + END_OF_LINE;
        return response;
    }

    public String findTasksWithKeyphrase(String keyphrase) {
        String list = Printables.TASK_SEARCH_INIT_STRING;
        for (Task task : tasks) {
            list += (task.getTaskName().toLowerCase().contains(keyphrase.toLowerCase())
                    ? (task + "\n") : "");
        }
        return (list.equals(Printables.TASK_SEARCH_INIT_STRING) ? Printables.EMPTY_TASK_SEARCH_RESULT_MESSAGE : list);
    }

    public String checkoutDate(String dateString) {
        LocalDate date = LocalDate.parse(dateString);
        String list = Printables.DATE_SEARCH_INIT_STRING;

        for (Task task : tasks) {
            if (task instanceof Deadlines) {
                Deadlines temp = (Deadlines) task;
                list += (temp.getDate().equals(date) ? (temp + "\n") : "");
            } else if (task instanceof Events) {
                Events temp = (Events) task;
                list += (temp.getDate().equals(date) ? (temp + "\n") : "");
            }
        }

        return (list.equals(Printables.DATE_SEARCH_INIT_STRING) ? Printables.EMPTY_DATE_SEARCH_RESULT_MESSAGE : list);
    }
}