package main.java;

public class Todo extends Task{
    public Todo(String n, boolean d){
        super(n, d);
    }
    public String toString(){
        return "[T]"+super.toString();
    }
    public String classInfo(){
        return "Todo";
    }
}
