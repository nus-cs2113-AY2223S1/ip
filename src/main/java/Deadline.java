package main.java;

public class Deadline extends Task {

    private String dueDate;

    public Deadline(String description, String date) {
        super(description, TaskType.DEADLINE);
        this.dueDate = date;
    }

    @Override
    public String toString() {
        return super.toString() + "(by: " + dueDate + ")";
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }


}
