package duke.exception;

public class StorageWriteException extends RuntimeException {
    public StorageWriteException(Throwable throwable) {
        super(throwable);
    }

    public StorageWriteException(String message) {
        super(message);
    }
}
