public class Task {
    private String description;
    private boolean marking;

    public Task(String description) {
        this.description = description;
        marking = false;
    }

    public void markDone() {
        System.out.println("Nice! I've marked this task as done:");
        marking = true;
        System.out.println(" [X] " + description);
    }

    public void unmarkDone() {
        System.out.println(" OK, I've marked this task as not done yet:");
        marking = false;
        System.out.println(" [ ] " + marking);
    }

    public String getMarking() {
        if (marking) {
            return "X";
        }
        return " ";
    }

    public String getDescription() {
        return description;
    }

}
