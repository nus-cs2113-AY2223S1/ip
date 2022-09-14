import java.util.ArrayList;

public class TaskManager {
    //private static final int MAX_LIST_NUM = 100;
    //private static Task[] taskList = new Task[MAX_LIST_NUM];
    private static ArrayList<Task> taskList = new ArrayList<Task>();
    //private static int listLength = 0;
    public boolean handleTask(String inputText) throws DukeException {
        final String[] split = inputText.trim().split("\\s+", 2);
        final String[] commandTypeAndParams = split.length == 2 ? split : new String[] { split[0], "" };
        final String commandType = commandTypeAndParams[0];
        final String commandArgs = commandTypeAndParams[1];
        int index;
        String response;
        switch (commandType) {
        case "todo":
            Todo todo = new Todo(commandArgs);
            taskList.add(todo);
            response = taskList.get(taskList.size()-1).getResponse();
            Util.printTaskResponse(response, taskList.size());
            break;
        case "deadline":
            Deadline deadline = new Deadline(commandArgs);
            taskList.add(deadline);
            response = taskList.get(taskList.size()-1).getResponse();
            Util.printTaskResponse(response, taskList.size());
            break;
        case "event":
            Event event = new Event(commandArgs);
            taskList.add(event);
            response = taskList.get(taskList.size()-1).getResponse();
            Util.printTaskResponse(response, taskList.size());
            break;
        case "list":
            showAllTasks();
            break;
        case "delete":
            index = Integer.valueOf(commandArgs);
            response = taskList.get(index-1).getResponse();
            taskList.remove(index-1);
            Util.printDeleteResponse(response, taskList.size());
            break;
        case "mark":
            index = Integer.valueOf(commandArgs);
            taskList.get(index-1).setStringState(true);
            response = taskList.get(index-1).getResponse();
            Util.printMarkResponse(response);
            break;
        case "unmark":
            index = Integer.valueOf(commandArgs);
            taskList.get(index-1).setStringState(false);
            response = taskList.get(index-1).getResponse();
            Util.printUnmarkResponse(response);
            break;
        case "bye":
            Util.showExitMessage();
            return true;
        default:
            throw new DukeException("TaskTypeError");
        }
        return false;
    }

    private void showAllTasks(){
        Util.printSplitLine();
        System.out.println("     Here are the tasks in your list:");
        for (int i = 0; i < taskList.size(); i++) {
            System.out.println("     " + String.valueOf(i+1) + "." + taskList.get(i).getResponse());
        }
        Util.printSplitLine();
        System.out.println();
    }

}
