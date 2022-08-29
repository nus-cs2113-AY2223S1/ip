public class Todo extends Task{
    /*public Todo(String command) {
        int DescStartIdx = command.indexOf(' ') + 1;
        String description = command.substring(DescStartIdx);
        this.description = description;
        this.isDone = false;
    }*/

    public Todo(String command, Character lastChar) {
        super(command, lastChar);
    }
}
