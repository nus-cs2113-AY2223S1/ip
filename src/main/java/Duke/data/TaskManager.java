package Duke.data;
import java.time.LocalDate;
import java.util.ArrayList;
import Duke.data.exception.EmptyListException;
import Duke.data.tasks.Task;

/**
 * Class to store all tasks and perform actions relating tasks
 */
public class TaskManager {
    private static final ArrayList<Task> tasks = new ArrayList<>();

    /**
     * This method adds a new task to the current list of task(s)
     * @param newTask task to be added
     */
    public void addTask(Task newTask) {
        tasks.add(newTask);
    }

    /**
     * This method deletes a task specified by user
     * @param taskNumber index of task to be deleted
     * @throws EmptyListException when there is no task
     */
    public void deleteTask(int taskNumber) throws EmptyListException {
        if(tasks.size() == 0) {
            throw new EmptyListException();
        }
        printTask(taskNumber);
        System.out.println("-- Deleted --");
        tasks.remove(taskNumber);
    }

    /**
     * This method marks a task as specified by user to be done
     * @param taskNumber index of task to be mark as done
     */
    public void markDone(int taskNumber) {
        tasks.get(taskNumber).setDone(true);
    }

    /**
     * This method marks a task as specified by user to be not done
     * @param taskNumber index of task to be mark as not done
     */
    public void unmarkDone(int taskNumber) {
        tasks.get(taskNumber).setDone(false);
    }

    /**
     * This method prints all task in list
     * @throws EmptyListException when there is no task
     */
    public void printList() throws EmptyListException {
        int index = 1;
        if(tasks.size() == 0) {
            throw new EmptyListException();
        }
        System.out.println("Here are the tasks in your list:");
        for (Task element : tasks) {
            System.out.print(index++ + ": ");
            element.printTask();
        }
    }

    /**
     * This method looks for tasks that have same date as the user input and prints it
     * @param date date to be compared to
     */
    public void searchForDate(LocalDate date) {
        boolean hasResults = false;
        System.out.println("Here are the tasks in your list that matches the given date:");
        for(Task element : tasks) {
            if(element.assignedDate.equals(date)) {
                element.printTask();
                hasResults = true;
            }
        }
        if(!hasResults) {
            System.out.println("There is no results.\n" +
                    "Please make sure you have keyed in the right date");
        }
    }

    /**
     * This method looks for tasks which has description that contain the given word
     * @param word substring to be searched for
     */
    public void searchForWord(String word) {
        boolean hasResults = false;
        System.out.println("Here are the tasks in your list that matches the given word:");
        for(Task element : tasks) {
            if((element.getDescription()).contains(word)) {
                element.printTask();
                hasResults = true;
            }
        }
        if(!hasResults) {
            System.out.println("There is no results.\n" +
                    "Please make sure you have keyed in the right keyword" +
                    "\n*note that keyword is case-sensitive*");
        }
    }

    /**
     * This method prints a specified task
     * @param taskNumber is the index of task to be printed
     */
    public void printTask(int taskNumber) {
        tasks.get(taskNumber).printTask();
    }

    /**
     * This method converts a specified task into a string and returns it
     * @param taskNumber is the index of task to be processed
     * @return string containing information about task
     */
    public String getTaskString(int taskNumber) {
        return tasks.get(taskNumber).toString();
    }

    /**
     * @return total number of task in list
     */
    public int getSize() {
        return tasks.size();
    }

    /**
     * This method prints the current total number of task in list
     */
    public void printSize() {
        System.out.println("There are currently " + tasks.size() + " task(s) in the list :)");
    }
}
