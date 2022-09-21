package duke.data.task;


/** Represent Description Attribute */
public class Description implements Attributes {
    public String data;

    public Description(String data) {
        this.setData(data);
    }

    /** Get Description data */
    @Override
    public String getData() {
        return this.data;
    }

    /** Set Description data */
    @Override
    public void setData(String data) {
        this.data = data;
    }

    /** String valid when not blank */
    @Override
    public boolean isValid() {
        return !this.data.isBlank();
    }
}
