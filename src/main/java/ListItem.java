
public class ListItem {
    private String name;
    private boolean status;

    public ListItem(String name) {
        this.name = name;
        this.status = false;
    }

    public String getName() {
        return name;
    }

    public boolean isDone() {
        return status;
    }
}