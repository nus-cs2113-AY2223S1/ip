public class Deadline extends Task{
    protected String date;
    public Deadline(String description) {
        super(description);
        setDate(description);
    }
    /**
     * Get the command icon
     *
     * @return string of type [D]
     */
    public String getCommandIcon(){return "[D]";}

    public void setDate(String description){
        int dateIndex = description.indexOf("/") + 3;
        this.date = description.substring(dateIndex);

    }

    private String getDescriptionNoDate(){
        int dateIndex = description.indexOf("/");
        return (description.substring(0, dateIndex - 1));
    }
    public String getDate(){return date;}
    @Override
    public String toString() {
        return getCommandIcon() + getStatusIcon() + " " + getDescriptionNoDate().trim() + " (by: " + getDate().trim() + ")";
    }
    public String fileString(){
        return ( "D," + getDone() + ","+ getDescriptionNoDate().trim() + "," + getDate());
    }
}
