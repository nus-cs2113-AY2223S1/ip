public class TaskManager {

    public static final String TASK_ADDED = "Got it. I've added this task:\n";
    public Task[] taskList = new Task[100];
    public int numOfTasks = 0;

    /**
     * Add a task into the list
     *
     * @param task task to add
     */
    public void addTask(Task task) {
        taskList[numOfTasks] = task;
        numOfTasks++;
        System.out.println(
                Duke.PRINT_LINE
                + TASK_ADDED
                + task + "\n"
                + "Now you have " + numOfTasks + " in the list.\n"
                + Duke.PRINT_LINE
        );
    }
    public void addToDo(String input){
        String todoTask = input.substring("todo".length(),input.length());
        ToDo todo = new ToDo(todoTask);
        addTask(todo);
    }
    public void addEvent(String input) {
        String eventTask = input.substring("event".length(),input.indexOf("/") - 1);
        String eventDate = input.substring(input.indexOf("/at ") + "/at ".length());
        Event event = new Event(eventTask, eventDate);
        addTask(event);

    }

    public void addDeadline(String input) {
        String deadlineTask = input.substring("deadline".length(),input.indexOf("/") - 1);
        String deadlineDate = input.substring(input.indexOf("/by ") + "/by ".length());
        Deadline deadline = new Deadline(deadlineTask, deadlineDate);
        addTask(deadline);

    }


    /**
     * mark a task in the list
     *
     * @param input position of item to mark in the list
     */
    public void markTask(int input) {
        if(input > 0 && input <= numOfTasks){
            taskList[input - 1].markAsDone();
        }
    }

    /**
     * unmark a task in the list
     *
     * @param input position of item to unmark in the list
     */
    public void unmarkTask(int input) {
        if(input > 0 && input <= numOfTasks){
            taskList[input - 1].markAsNotDone();
        }
    }

    /**
     * Print List in chronological order
     */
    public void printList() {
        System.out.println(
                Duke.PRINT_LINE
                + "Here are the tasks in your list:"
        );
        for(int i = 0; i < numOfTasks; i ++) {
            System.out.println(
                    (i+1) + "."
                            + taskList[i]
            );
        }
        System.out.println(
                Duke.PRINT_LINE
        );
    }
}
