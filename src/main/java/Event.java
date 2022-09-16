package main.java;

public class Event extends Task{
    protected String date;
    public Event(String n, boolean d, String dat){
        super(n, d);
        date = dat;
    }

    public String getDate(){
        return date;
    }

    @Override
    public String toString() {
        return "[E]"+super.toString()+" (at: "+date+")";
    }

    @Override
    public String classInfo() {
        return "Event";
    }
}
