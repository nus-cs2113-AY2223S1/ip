package duke;

public class DukeException {
    public static class IllegalNoMarkIndexException extends Exception {
        @Override
        public String getMessage() {
            return "Please enter a task number only to be marked";
        }
    }
    public static class IllegalMarkTargetException extends Exception {
        @Override
        public String getMessage() {
            return "Index of task is out of range";
        }
    }
    public static class IllegalDeadlineFormatException extends Exception {
        @Override
        public String getMessage() {
            return "Please enter a deadline task followed by a '/' and indicate a deadline";
        }
    }
    public static class IllegalDeadlineDateException extends Exception {
        @Override
        public String getMessage() {
            return "Please enter a deadline for this task in the format 'yyyy-mm-dd hh:mm'";
        }
    }
    public static class IllegalEventFormatException extends Exception {
        @Override
        public String getMessage() {
            return "Please enter an event task followed by a '/' and indicate when";
        }
    }
    public static class IllegalEventDateException extends Exception {
        @Override
        public String getMessage() {
            return "Please enter when this event is happening in the format 'yyyy-mm-dd hh:mm'!";
        }
    }
    public static class IllegalTodoException extends Exception {
        @Override
        public String getMessage() {
            return "Please enter a name for the todo task!";
        }
    }
    public static class IllegalDeleteIndexException extends Exception {
        @Override
        public String getMessage() {
            return "Please enter a task number only to be deleted";
        }
    }
    public static class IllegalDeleteTargetException extends Exception {
        @Override
        public String getMessage() {
            return "Index of task is out of range";
        }
    }
    public static class IllegalReadFromSaveData extends Exception {
    }
    public static class IllegalDateTimeException extends Exception {
        @Override
        public String getMessage() {
            return "The date entered is not recognised, please follow the format of 'yyyy-mm-dd'";
        }
    }
}

