public class Deadline extends Task{
    protected String[] descriptions;

    public Deadline(String commandArgs){
        super(commandArgs);
        descriptions = this.parseDescriptions(commandArgs);
    }

    @Override
    public String getTaskType(){
        return "D";
    }

    private String[] parseDescriptions(String commandArgs){
        String[] descriptions = commandArgs.split("/");
        return descriptions;
    }
}
