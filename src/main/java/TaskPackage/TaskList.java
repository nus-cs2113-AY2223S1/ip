package TaskPackage;

import java.util.ArrayList;
/*
A list of Tasks
 */
public class TaskList {
    private static ArrayList<Task> myList = new ArrayList<Task>();
    /*
    Allows other classes to access myList and act upon it like a regular ArrayList.
     */
    public ArrayList<Task> getTasks(){
        return myList;
    }

    public TaskList(){}


}
