public class Todo extends Task{

    public Todo(String description){
        super(description);
    }

    @Override
    protected String getTaskType(){
        return "T";
    }
}
