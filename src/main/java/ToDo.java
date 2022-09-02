public class ToDo extends Task{

    public ToDo(String description){
        super(description);
        this.isDone = false;
        this.taskType = 'T';
    }

    @Override
    public void printTask() {
        super.printTask();
        System.out.println();
    }
}
