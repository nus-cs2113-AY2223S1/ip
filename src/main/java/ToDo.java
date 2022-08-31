public class ToDo extends Task{
    private boolean isDone;

    public void setDone(boolean isDone){
        this.isDone = isDone;
    }

    public boolean getDone(boolean isDone){
        return this.isDone;
    }

    public ToDo(String description){
        super(description);
        isDone = false;
    }

    @Override
    public void printItem() {
        System.out.println("[T]["+this.getStatusIcon()+"] "+ this.description);
    }
}
