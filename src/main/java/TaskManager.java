public class TaskManager {
    public Task[] taskList = new Task[100];
    public int numOfTasks = 0;

    /**
     * Add a task into the list
     *
     * @param input task to add
     */
    public void addTask(String input){
        taskList[numOfTasks] = new Task(input);
        numOfTasks++;
        System.out.println(
                Duke.PRINT_LINE
                + "added: " + input + "\n"
                + Duke.PRINT_LINE
        );
    }

    /**
     * mark a task in the list
     *
     * @param input position of item to mark in the list
     */
    public void markTask(int input){
        if(input > 0 && input <= numOfTasks){
            taskList[input - 1].markAsDone();
        }
    }

    /**
     * unmark a task in the list
     *
     * @param input position of item to unmark in the list
     */
    public void unmarkTask(int input){
        if(input > 0 && input <= numOfTasks){
            taskList[input - 1].markAsNotDone();
        }
    }

    /**
     * Print List in chronological order
     */
    public void printList(){
        System.out.println(
                Duke.PRINT_LINE
                + "Here are the tasks in your list:"
        );
        for(int i = 0; i < numOfTasks; i ++) {
            System.out.println(
                    (i+1) + "."
                            + taskList[i].getStatusIcon()
                            + taskList[i].description
            );
        }
        System.out.println(
                Duke.PRINT_LINE
        );
    }
}
