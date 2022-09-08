public class Todo extends Task{
    public Todo (String taskDescription){
        super(taskDescription);
    }

    @Override
    public String showTask(){
        return "[T][" + (isDone ? "\u2713" : " ") + "] " + taskDescription;
    }
}