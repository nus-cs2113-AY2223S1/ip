import java.util.ArrayList;

public class find extends Command {
    String fullCommand;
    find(String fullCommand) {
        this.fullCommand = fullCommand;
    }

    public void execute(ArrayList<String> type, ArrayList<String> marks, ArrayList<String> tasks, Ui ui, Storage storage) {
        System.out.println("Here are the matching tasks in your list:");
        int numbering = 1;
        String wordToFind = fullCommand.substring(5,fullCommand.length()).toLowerCase();
        for (int i = 1; i < storage.getCount(); i++) {
            if(tasks.get(i - 1).toLowerCase().contains(wordToFind)){
                System.out.println(numbering + "." + type.get(i-1) + marks.get(i-1) + " " + tasks.get(i-1));
                numbering++;
            }
        }
    }
    boolean isExit() {
        return false;
    }

}
