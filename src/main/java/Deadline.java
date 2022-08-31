public class Deadline extends Task {

    private String by;

    public String getBy(){

        return this.by;

    }

    public Deadline(String description, String by){
        super(description);
        this.by = by;
    }

    public void setBy(String by){
        this.by = by;
    }

    @Override
    public void printItem() {
        System.out.println("[D]["+this.getStatusIcon()+"] "+this.description+" (by: "+this.by+")");
    }
}