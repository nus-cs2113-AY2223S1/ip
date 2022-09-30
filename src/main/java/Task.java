
/**
 * Parent class for all the different task types, sets up the methods to be
 * overridden and the base constructor with name and doneness
 */
public class Task {
    private String name;
    private boolean isDone;

    /**
     * Sets up an open constructor for a task with no name and not being done
     */
    public Task() {
        name = "";
        isDone = false;
    }

    /**
     * General constructor for all task classes that includes both doneness of
     * an activity and its name
     * @param name name of task
     * @param done doneness of task
     */
    public Task(String name, boolean done) {
        this.name = name;
        isDone = done;
    }

    /**
     * Allows the name of a task to be modified based on an input
     * @param name name to change to
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * returns the name of the task upon getting called
     * @return name of task
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the doneness of a task
     * @param done is used to set the doneness of the task
     */
    public void setDone(boolean done) {
        if (isDone == done) {
            System.out.println("Task was already marked like that");
        } else {
            isDone = !isDone;
        }
    }

    public boolean getDone(){
        return isDone;
    }

    /**
     * Overrides the Object toString method and is used to
     * @return a base string representation of a task
     */
    @Override
    public String toString() {
        if (isDone) {
            return "[X] " + name;
        } else {
            return "[ ] " + name;
        }
    }

    /**
     * Gets the type of task as a string and is overridden in the child classes
     * @return "task"
     */
    public String classInfo(){
        return "Task";
    }

    /**
     * Method that does nothing here but is a placeholder to be overridden
     * in the child classes of Task
     * @return ""
     */
    public String getDate(){
        return "";
    }
}
