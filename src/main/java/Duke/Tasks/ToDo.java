package Duke.Tasks;

public class ToDo extends Tasks {

    public ToDo(String description) {

        super(description);
    }

    @Override
    public String toString() {

        return "[T]" + getStatusIcon() + super.description;
    }
}
