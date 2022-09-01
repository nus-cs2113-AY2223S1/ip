public class Task {
    public String description;
    public boolean isDone;

    public Task(String description){
        this.description = description;
        this.isDone = false;
    }

    public String toString(){
        String output = "[ ] ";
        if(isDone){
            output = "[X] ";
        }

        return output + description;
    }

    public void setDone(boolean status){
        isDone = status;
    }

    public boolean getDone(boolean status){
        return isDone;
    }

}
