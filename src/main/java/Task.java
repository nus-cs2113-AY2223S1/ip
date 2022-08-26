package main.java;

public class Task {

    private String mark = " ";
    private String description;

    public Task(String description) {
        this.description = description;
    }

    public void mark() {
        setMark("X");
    }

    public void unmark() {
        setMark(" ");
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
