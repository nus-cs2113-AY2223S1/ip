package TaskPackage;

import TaskPackage.Task;
/*
Represents Todo, a type of task.
 */
public class Todo extends Task {
    public Todo(String description){
        super(description);
    }
    public String toString(){
        return "[T]" + super.toString();
    }
}
