public class IndivItemDuke {
    private String item;
    private Boolean isCompleted;

    public IndivItemDuke(String item) {
        this.item = item;
        isCompleted = false;
    }

    public void setCompleted(Boolean isCompleted) {
        this.isCompleted = isCompleted;
    }

    public Boolean hasCompleted() {
        return this.isCompleted;
    }

    public String getItem() {
        return this.item;
    }
}
