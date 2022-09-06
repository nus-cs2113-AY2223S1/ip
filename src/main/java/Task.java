public class Task {
    public String description;
    public boolean isDone;

    public Task(String description){
        this.description = description;
        this.isDone = false;
        System.out.println("Got it. I've added this task:");
    }

    public String toString(){
        String output = "[ ] ";
        if(isDone){
            output = "[X] ";
        }

        return output + this.description;
    }

    public void setDone(boolean status){
        isDone = status;
    }

    public boolean getDone(boolean status){
        return isDone;
    }

}
