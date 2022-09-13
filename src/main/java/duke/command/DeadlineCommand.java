package duke.command;

public class DeadlineCommand extends Command{

    private String time;
    private String description;

    public DeadlineCommand() {

    }

    @Override
    public void setArgument(String argument) {
        if (!description.equals("")) {
            this.time = argument;
        }   else {
            this.description = argument;
        }
    }

    @Override
    public String getArgument(boolean b) {
        if (b) {
            return description;
        }   else {
            return time;
        }
    }
}
