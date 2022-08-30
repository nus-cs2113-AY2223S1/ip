package main.java;

public class Event extends Task{
    protected String date;
    public Event(String n, boolean d, String dat){
        super(n, d);
        date = dat;
    }

    @Override
    public String toString() {
        return "[E]"+super.toString()+" (at: "+date+")";
    }
}
