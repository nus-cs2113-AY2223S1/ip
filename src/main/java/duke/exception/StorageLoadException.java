package duke.exception;

public class StorageLoadException extends RuntimeException {
    public StorageLoadException(Throwable throwable) {
        super(throwable);
    }

    public StorageLoadException(String message) {
        super(message);
    }
}
