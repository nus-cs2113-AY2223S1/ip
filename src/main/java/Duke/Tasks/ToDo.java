package Duke.Tasks;

public class ToDo extends Tasks {

    public ToDo(String description) {
        super(description);
    }

    @Override
    public String toString() {

        return "[T]" + getStatusIcon() + super.description;
    }

    @Override
    public String toFile() {
        return "T|" + ((this.isDone) ? "1" : "0") + "|" + super.description + "\n";
    }
}
