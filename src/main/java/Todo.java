package main.java;

/**
 * Creates the todo object that extends the Task class
 */
public class Todo extends Task{
    /**
     * Todo constructor that sets up the name and date of the task
     * @param name name of todo
     * @param done doneness of todo
     */
    public Todo(String name, boolean done){
        super(name, done);
    }

    /**
     * Overrides the toString method of Task to add the todo part
     * @return
     */
    @Override
    public String toString(){
        return "[T]"+super.toString();
    }

    /**
     * Overrides the task info method to specify that it is a todo
     * @return "Todo"
     */
    @Override
    public String classInfo(){
        return "Todo";
    }
}
