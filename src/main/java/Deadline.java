package main.java;

/**
 * The deadline class is used to hold the information about deadline objects.
 * It extends the task class.
 */
public class Deadline extends Task{
    protected String date;

    /**
     * Constructor for Deadline objects, reads in the name, doneness, and date
     * associated with the Deadline and sets it to the proper variables
     * @param name name of deadline
     * @param done doneness of deadline
     * @param date date of deadline
     */
    public Deadline(String name, boolean done, String date){
        super(name, done);
        this.date = date;
    }

    /**
     * Returns the date of the deadline
     * @return date
     */
    @Override
    public String getDate(){
        return date;
    }

    /**
     * Returns the deadline object as a string
     * @return The unique string translation of a deadline object
     */
    @Override
    public String toString() {
        return "[D]"+super.toString()+" (by: "+date+")";
    }

    /**
     * Returns the type of class it is as a string
     * @return the type of task as a string
     */
    @Override
    public String classInfo() {
        return "Deadline";
    }
}
