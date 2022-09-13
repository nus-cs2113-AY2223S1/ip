public class Todo extends Task{
    public Todo(String description) throws DukeExceptions {
        super(description);
        if (description.equals(" ")){
            throw new DukeExceptions();
        }
    }
    public String getCommandIcon(){return "[T]";}
    @Override
    public String toString() {
        return getCommandIcon() + getStatusIcon() + getDescription();
    }

    public String fileString(){
        return ( "T," + getDone() + ","+ description.trim());
    }
}
