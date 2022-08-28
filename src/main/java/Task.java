public class Task {
    protected String task;
    protected boolean isDone;

    //Constructor
    public Task(String task) {
        // Initialise task and print completion message
        this.task = task;
        System.out.println("Got it. I added to the list.");
    }

    public String getTask() {
        // Return task
        return task;
    }

    public String getStatusIcon() {
        // Return X if marked and empty if unmarked
        return (isDone? "X" : " ");
    }

    public void setStatusIcon(String status) {
        // If it's marked, isDone is changed to true and false if unmarked
        if (status.equals("mark")) {
            isDone = true;
        } else {
            isDone = false;
        }
    }
    public void printTask(int index) {
        // Print the task in numbered list format.
        System.out.println((index + 1) + ". [" + getStatusIcon() + "] " + getTask());
    }


}
