package duke.command;

public abstract class Command {

    private String keyword;
    private boolean isBye;

    public String getKeyword() {
        return keyword;
    }

    public boolean isBye() {
        return isBye;
    }

    public void setBye(boolean b) {
        isBye = b;
    }

    public abstract void setArgument(String argument);

    public abstract String getArgument(boolean b);

}
