package duke.command;

public abstract class Command {

    private String keyword;
    private boolean isBye;

    public String getKeyword() {
        return keyword;

    }
    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public boolean isBye() {
        return isBye;
    }

    public void setBye(boolean b) {
        isBye = b;
    }



    public abstract void setArgument(String argument, int i);

    public abstract String getArgument(boolean b);

}
