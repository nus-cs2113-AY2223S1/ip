public class Task {
    private String description;
    private boolean isDone;

    public Task(String description) {
        this.description = description;
        isDone = false;
    }

    public void markDone() {
        System.out.println("Nice! I've marked this task as done:");
        isDone = true;
        System.out.println(" [X] " + description);
    }

    public void unmarkDone() {
        System.out.println(" OK, I've marked this task as not done yet:");
        isDone = false;
        System.out.println(" [ ] " + description);
    }

    public String getIsDoneMarking() {
        if (isDone) {
            return "X";
        }
        return " ";
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
