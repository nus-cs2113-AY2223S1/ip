public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    @Override
    public String getDescriptionAndStatus() {
        return "[T][" + this.getStatus() + "] " + this.getDescription();
    }
    @Override
    public String fileFormat() {
        return "T ┊ " + this.getStatus() + " ┊ " + this.description;
    }

}