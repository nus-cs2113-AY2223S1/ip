public class Task {
    private String description;
    private boolean completion;

    public Task(String description) {
        this.description = description;
        this.completion = false;
    }

    public String getStatus() {
        if (completion) {
            return "X";
        } else {
            return " ";
        }
    }

    public void setCompletion(boolean completion) {
        this.completion = completion;
    }

    public String getDescription() {
        return description;
    }
}
