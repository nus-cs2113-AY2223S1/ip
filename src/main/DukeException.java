package main;

public class DukeException extends Exception{
    private String type;
    private String name;
    public DukeException(String type){
        this.type = type;
        name = "";
    }
    public DukeException(String type, String name){
        this.type = type;
        this.name = name;
    }
    public void printError() {
        if (name.equals("")){
            if (type.equals("todo")||type.equals("deadline")||type.equals("event")) {
                System.out.println(type+" command requires a description to be added to the list");
            }
            else{
                System.out.println("I'm sorry I don't know what that is, please enter another command");
            }
        }
        else{
            System.out.println("Please add a date range for this event: "+name);
        }
    }
}
