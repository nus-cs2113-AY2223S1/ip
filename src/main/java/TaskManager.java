public class TaskManager {
    private static final int MAX_NUM_OF_TASKS = 100;
    private Task[] tasks = new Task[MAX_NUM_OF_TASKS];

    public void addTask(String type, String input) {
        final String ADD_PHRASE_START = "added: ";
        // add one because newTask has not been created yet
        final String ADD_PHRASE_END = "Now you have " + Integer.toString(Task.getTaskCount() + 1)
                + " task(s) in the list.";

        Parser parser = new Parser();

        Task newTask = parser.parseInput(type, input);

        // change to zero-index
        tasks[Task.getTaskCount() - 1] = newTask;

        System.out.println(ADD_PHRASE_START + newTask);
        System.out.println(ADD_PHRASE_END);
    }

    public void printList() {
        for (int i = 0; i < Task.getTaskCount(); i += 1) {
            System.out.println(Integer.toString(i + 1) + ". " + tasks[i]);
        }
    }

    public void markTask(int num) {
        // note that num here is zero-index
        tasks[num].setDone();

        System.out.println("Completed! The following task is marked as done:");
        System.out.println(tasks[num]);
    }

    public void unmarkTask(int num) {
        // note that num here is zero-index
        tasks[num].setUndone();

        System.out.println("Oh no! The following task is marked as undone:");
        System.out.println(tasks[num]);
    }
}
