public class Event extends Task {
    private String timing;

    public String getTiming(){
        return this.timing;
    }

    public Event(String description, String timing){
        super(description);
        this.timing = timing;
    }

    public void setTiming(String timing) {
        this.timing = timing;
    }
    @Override
    public void printItem() {
        System.out.println("[E]["+this.getStatusIcon()+"] "+this.description+" (at: "+this.timing+")");
    }
}
