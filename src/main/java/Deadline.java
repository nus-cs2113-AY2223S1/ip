public class Deadline extends Task{

    protected String date;

    public Deadline(String description, String date) {
        super(description);
        this.date = date;
    }

    @Override
    public String print() {
        return ("[D]" + super.print() + " (by: " + this.date + ")");
    }

    public String getDate() {
        return this.date;
    }
}