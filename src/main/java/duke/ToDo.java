package duke;

public class ToDo extends Task {

    protected String by;

    public ToDo(String description) {
        super(description);

    }

    public static ToDo addToDo(String name){
        ToDo newToDo = new ToDo(name);
        System.out.println(""+newToDo.toString());
        return newToDo;
    }



    @Override
    public String toString() {
        return this.index + "."+"[T]" + "["+this.getStatusIcon()+"] " + this.name;
    }
    @Override
    public String recordString() {
        return "T | " + super.recordString();

    }
    @Override
    public String recordString() {
        return "T | " + super.recordString();
    }
}
