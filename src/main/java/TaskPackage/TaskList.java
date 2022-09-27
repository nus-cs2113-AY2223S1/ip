package TaskPackage;

import java.util.ArrayList;
public class TaskList {
    private static ArrayList<Task> myList = new ArrayList<Task>();
    public ArrayList<Task> getTasks(){
        return myList;
    }

    public TaskList(){}


}
