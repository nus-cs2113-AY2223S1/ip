import java.util.ArrayList;

public class ListDuke {
    private ArrayList<String> inputLists = new ArrayList<String>();

    public String getInputLists() {
        String output = "";
        for (int i = 0; i < inputLists.size(); i++) {
            output += Integer.toString(i+1) + ". " + inputLists.get(i) + "\n";
        }
        return output;
    }

    public void setInputLists(String input) {
        inputLists.add(input);
    }

}
