package main.java;

public class Deadline extends Task{
    protected String date;
    public Deadline(String n, boolean d, String dat){
        super(n, d);
        date = dat;
    }

    public String getDate(){
        return date;
    }

    @Override
    public String toString() {
        return "[D]"+super.toString()+" (by: "+date+")";
    }

    @Override
    public String classInfo() {
        return "Deadline";
    }
}
