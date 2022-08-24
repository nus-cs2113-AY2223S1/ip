public class IndivItemDuke {
    private String item;
    private Boolean completed;

    public IndivItemDuke(String item) {
        this.item = item;
        completed = false;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }

    public Boolean getCompleted() {
        return this.completed;
    }

    public String getItem() {
        return this.item;
    }
}
