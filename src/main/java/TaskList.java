import java.util.ArrayList;

public class TaskList {


    public static void addTask(String command, ArrayList<Task> tasks, String inputText) {
        try {
            if (command.equals("todo")) {
                tasks.add(new Todo(inputText.substring(5)));
            } else if (command.equals("deadline")) {
                tasks.add(new Deadline(inputText.substring(9, inputText.indexOf('/')), inputText.substring(inputText.indexOf('/') + 4)));
            } else if (command.equals("event")) {
                tasks.add(new Event(inputText.substring(6, inputText.indexOf('/')), inputText.substring(inputText.indexOf('/') + 4)));
            } else {
                throw new InvalidCommandException();
            }
            UI.addMessage(inputText, tasks.size());

        } catch (InvalidCommandException e) {
            System.out.println("This is not a valid command.Please re-enter a valid command");
        }
    }

    public static void deleteTask(int position, ArrayList<Task> tasks){
        String str = tasks.get(position-1).toString();
        tasks.remove(position-1);
        UI.deleteMessage(str, tasks.size());
    }

    public static void markTask(int position,ArrayList<Task> tasks ){
        tasks.get(position-1).markAsDone();
        UI.markMessage("mark",tasks.get(position-1).description,tasks.get(position-1).getStatusIcon() );
    }

    public static void findTask(ArrayList<Task> tasks , String word){
        ArrayList<Task> tasksFound = new ArrayList<>();
        for(int i=0;i<tasks.size();i++){
            String str = tasks.get(i).description;
            if (str.contains(word)){
                tasksFound.add(tasks.get(i));
            }
        }
        UI.listMessage(tasksFound);
    }

    public static void handleStoredTasks(ArrayList<String> storedTasks, ArrayList<Task> tasks){
        for (String str : storedTasks) {
            if (str.charAt(1) == 'T') {
                tasks.add(new Todo(str.substring(7)));
                if (str.charAt(4) == 'X') {
                    tasks.get(tasks.size() - 1).markAsDone();
                }
            }
            if (str.charAt(1) == 'D') {
                tasks.add(new Deadline(str.substring(7, str.indexOf('(')), str.substring(str.indexOf('(') + 4, str.indexOf(')'))));
                if (str.charAt(4) == 'X') {
                    tasks.get(tasks.size() - 1).markAsDone();
                }
            }
            if (str.charAt(1) == 'E') {
                tasks.add(new Event(str.substring(7, str.indexOf('(')), str.substring(str.indexOf('(') + 4, str.indexOf(')'))));
                if (str.charAt(4) == 'X') {
                    tasks.get(tasks.size() - 1).markAsDone();
                }
            }
        }
    }

    public static void unmarkTask(int position,ArrayList<Task> tasks ){
        tasks.get(position-1).markAsUndone();
        UI.markMessage("unmark",tasks.get(position-1).description,tasks.get(position-1).getStatusIcon() );
    }
}
