public class Todo extends Task {

    public Todo(String description, boolean status) {
        super(description, status);
    }

    @Override
    public String getDescriptionAndStatus() {
        return "[T][" + this.getStatus() + "] " + this.getDescription();
    }
    @Override
    public String fileFormat() {
        return "T | " + this.getStatus() + " | " + this.description;
    }

}