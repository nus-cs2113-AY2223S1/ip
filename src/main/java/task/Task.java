package task;

/**
 * Parent class of task, with children Todo, Event and Deadline
 */
public class Task {
    protected String description;
    /**
     * Boolean to show if task is done or not
     */
    public boolean isDone;
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }
    /**
     * Method is called when task is to be printed
     * @return X if isDone is true, " " if isDone is false.
     * If true, [X] will be printed when task is printed
     * If false, [ ] will be printed when task is printed
     */
    public String getStatusIcon() {
        if (isDone) {
            return "X";
        }
        return " ";
    }

    /**
     * @return the full details of each task as a string in child
     */
    public String toString() {
        return toString();
    }

    /**
     * @return the corresponding letter of the type of task in child
     * "D" if deadline, "E" if event, "T" if todo
     */
    public String getTaskClass() {
        return getTaskClass();
    }

    /**
     * @return description of task, regardless of child type
     */
    public String getDescription() {
        return description;
    }

    /**
     * @return date + time of task, in string format, regardless of child type
     */
    public String getDetails() {
        return getDetails();
    }
}