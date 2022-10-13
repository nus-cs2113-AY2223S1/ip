package duke;

/**
 * Sub-class of task.
 */
public class ToDo extends TaskList {

    protected String by;

    public ToDo(String description) {
        super(description);

    }

    /**
     * Constructs a ToDo object given description string.
     * @param name The name of task.
     * @return The generated ToDo Task.
     */
    public static ToDo addToDo(String name){
        ToDo newToDo = new ToDo(name);
        System.out.println(""+newToDo.toString());
        return newToDo;
    }



    @Override
    public String toString() {
        return "[T]" + "["+this.getStatusIcon()+"] " + this.name;
    }
    @Override
    public String recordString() {
        return "T | " + super.recordString();

    }
}
