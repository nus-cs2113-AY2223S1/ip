package duke.command;

public abstract class Command {

    private String keyword;
    private boolean isBye;
    private boolean isLegal = true;

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

    public boolean isLegal() {
        return isLegal;
    }

    public void setLegal(boolean legal) {
        isLegal = legal;
    }

    public abstract void setArgument(String argument, int i);

    public abstract String getArgument(boolean b);

}
