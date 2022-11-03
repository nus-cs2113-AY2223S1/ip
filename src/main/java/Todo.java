/**
 * Task of type Todo and helps all the workings specific to task
 */
public class Todo extends Task{
    public Todo(String description) throws DukeExceptions {
        super(description);
        if (description.equals(" ")){
            throw new DukeExceptions();
        }
    }

    /**
     * Gets the type of task it is
     * @return String of T
     */
    public String getCommandIcon() { return "[T]"; }
    @Override
    public String toString() {
        return getCommandIcon() + getStatusIcon() + getDescription();
    }

    public String fileString(){
        return ( "T," + getDone() + ","+ description.trim());
    }
}
