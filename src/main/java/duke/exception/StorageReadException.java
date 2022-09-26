package duke.exception;

public class StorageReadException extends DukeException {

    private String MESSAGE_FIRST_PART = "Sorry, there was an error loading the ";
    private String MESSAGE_LAST_PART = " portion of the stored data :-(";
    private String type;

    public StorageReadException(String type) {
        super();
        this.type = type;
    }

    @Override
    public String getMessage() {
        return MESSAGE_FIRST_PART + type + MESSAGE_LAST_PART;
    }
}
