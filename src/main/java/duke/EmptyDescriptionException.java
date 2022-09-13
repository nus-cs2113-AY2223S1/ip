package duke;

public class EmptyDescriptionException extends Exception {
        private String task;
        public EmptyDescriptionException(String task) {
            this.task = task;
        }
        String getExceptionDescription() {
            return "OOPS!!! The description of a " + task + " cannot be empty";
        }
}
