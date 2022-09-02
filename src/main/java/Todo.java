public class Todo extends Task{
    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return ("[T]" + super.toString());
    }

    public static String[] extractParameters(String command) throws IllegalArgumentException {
        String commandParameters = command.split(" ", 2)[1];
        return new String[] {commandParameters};
    }
}
