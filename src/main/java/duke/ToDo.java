package duke;

/**
 * Todo class is an extension of Task class, modify the string representation of an Todo task.
 */
public class ToDo extends Task{

    public ToDo(String description){
        super(description);
    }

    public String toString(){
        return String.format("[T]%s", super.toString());
    }
}
