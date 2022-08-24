import java.util.ArrayList;

public class ListDuke {
    private ArrayList<IndivItemDuke> inputLists = new ArrayList<IndivItemDuke>();

    public String getItemFromList(int n) {
        String output = "\t[";
        if (inputLists.get(n-1).hasCompleted()){
            output += "X] ";
        } else {
            output += " ] ";
        }
        output += inputLists.get(n - 1).getItem() + "\n";
        return output;
    }
    public String getInputLists() {
        String output = "";
        for (int i = 0; i < inputLists.size(); i++) {
            output += Integer.toString(i + 1) + ". " + getItemFromList(i + 1);
        }
        return output;
    }

    public void setInputLists(String input) {
        IndivItemDuke newItem = new IndivItemDuke(input);
        inputLists.add(newItem);
    }

    public void markCompleted(int n, Boolean bool) {
        inputLists.get(n - 1).setCompleted(bool);
    }

}
