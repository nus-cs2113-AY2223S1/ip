
/**
 * Exception class for errors unique to duke that caught using this excpetion class
 */
public class DukeException extends Exception{
    private String type;
    private String name;

    /**
     * Constructor for DukeException that only contains the type of task
      * @param type type of task
     */
    public DukeException(String type){
        this.type = type;
        name = "";
    }

    /**
     * Constructor for DukeException that has both the type and name of a task but no date
     * @param type type of task
     * @param name name of task
     */
    public DukeException(String type, String name){
        this.type = type;
        this.name = name;
    }

    /**
     * Prints out the error associated with the creation of the DukeException
     * and details which parts of the user input were flawed and coused the
     * error
     */
    public void printError() {
        if (name.equals("")){
            if (type.equals("todo")||type.equals("deadline")||type.equals("event")) {
                System.out.println(type+" command requires a description to be added to the list");
            }
            else{
                System.out.println("I'm sorry I don't know what that is, please enter another command");
            }
        }
        else{
            System.out.println("Please add a date range for this event: "+name);
        }
    }
}
