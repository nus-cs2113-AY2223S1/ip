public class Task {
    protected String task;
    protected boolean isDone;

    public Task(String task) {
        this.task = task;
        System.out.println("Got it. I added to the list.");
    }

    public String getTask() {
        return task;
    }

    public String getStatusIcon() {
        return (isDone? "X" : " ");
    }

    public void setStatusIcon(String status) {
        if (status.equals("mark")) {
            isDone = true;
        } else {
            isDone = false;
        }
    }

    public void printTask(int index) {
        System.out.println((index + 1) + ".[" + getStatusIcon() + "] " + getTask());
    }
}
