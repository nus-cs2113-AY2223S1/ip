import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> inputLists = new ArrayList<Task>();

    public String getItemFromList(int n) {
        String output = "\t";
        output += inputLists.get(n - 1).getCompleteDescription();
        return output;
    }

    public String getCompleteList() {
        String output = "";
        for (int i = 0; i < inputLists.size(); i++) {
            output += Integer.toString(i + 1) + ". " + getItemFromList(i + 1);
        }
        return output;
    }

    public int addTaskToList(String input, TaskType type) {
        Task newItem;

        if (type == TaskType.EVENT) {
            int indexOfTime = input.indexOf("/at");
            String description = input.substring(0, indexOfTime).strip();
            String time = input.substring(indexOfTime + "/at ".length()).strip();
            newItem = new Event(description, time);
        } else if (type == TaskType.DEADLINE) {
            int indexOfTime = input.indexOf("/by");
            String description = input.substring(0, indexOfTime).strip();
            String time = input.substring(indexOfTime + "/by ".length()).strip();
            newItem = new Deadline(description, time);
        } else if (type == TaskType.TODO) {
            newItem = new Todo(input);
        } else {
            newItem = new Task(input);
        }
        inputLists.add(newItem);
        return inputLists.size() - 1;
    }

    public void markCompleted(int n, boolean bool) {
        inputLists.get(n - 1).setCompleted(bool);
    }
}
