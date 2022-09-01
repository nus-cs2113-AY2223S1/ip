public class Task {
    private String name;
    private boolean isCompleted;

    public Task(String name) {
        this.name = name;
        this.isCompleted = false;
    }

    public String getName(){
        return name;
    }

    public boolean getIsCompleted(){
        return isCompleted;
    }

    public void setIsCompleted(boolean isCompleted){
        this.isCompleted = isCompleted;
    }

    //returns the checkbox as a string
    public String getCheckBox() {
        if (isCompleted){
            return "[X]";
        }
        return "[ ]";
    }
}
