public class Task {
    protected String name;
    static public int currTotal = 0;
    public int index;
    protected boolean isDone;

    public Task(String name) {
        this.name = name;
        this.index = currTotal + 1;
        currTotal += 1;
        this.isDone = false;
    }


    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public void UpdateStatus(){
        this.isDone = !this.isDone;
    }
    //...
    public String printSelf(){
        return ("["+this.getStatusIcon()+"] " + this.name);
    }

}