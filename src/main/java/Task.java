public class Task {
    protected String description;
    protected boolean isDone;
    protected String taskType;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.taskType = "T";
    }

    /**
     * Get status of list item if it is done or undone
     */
    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]"
        ); // mark done task with X
    }


    /**
     * Mark an item as not done
     */
    public void markAsNotDone(){
        this.isDone = false;
        System.out.println(
                Duke.PRINT_LINE
                        +"OK, I've marked this task as not done:\n"
                        + "[ ] "
                        + description
                        + "\n____________________________________________________________"
        );
    }

    /**
     * Mark an item as done
     */
    public void markAsDone(){
        this.isDone = true;
        System.out.println(
                Duke.PRINT_LINE
                        +"Nice! I've marked this task as done:\n"
                        + "[X] "
                        + description
                        + "\n____________________________________________________________"
        );
    }
}
