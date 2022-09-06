public class TaskManager {
    private static final int MAX_LIST_NUM = 100;
    private static Task[] taskList = new Task[MAX_LIST_NUM];
    private static int listLength = 0;
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
            listLength++;
            taskList[listLength] = todo;
            response = taskList[listLength].getResponse();
            Util.printTaskResponse(response, listLength);
            break;
        case "deadline":
            Deadline deadline = new Deadline(commandArgs);
            listLength++;
            taskList[listLength] = deadline;
            response = taskList[listLength].getResponse();
            Util.printTaskResponse(response, listLength);
            break;
        case "event":
            Event event = new Event(commandArgs);
            listLength++;
            taskList[listLength] = event;
            response = taskList[listLength].getResponse();
            Util.printTaskResponse(response, listLength);
            break;
        case "list":
            showAllTasks();
            break;
        case "mark":
            index = Integer.valueOf(commandArgs);
            taskList[index].setStringState(true);
            response = taskList[index].getResponse();
            Util.printMarkResponse(response);
            break;
        case "unmark":
            index = Integer.valueOf(commandArgs);
            taskList[index].setStringState(false);
            response = taskList[index].getResponse();
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
        for (int i = 1; i <= listLength; i++) {
            System.out.println("     " + String.valueOf(i) + "." + taskList[i].getResponse());
        }
        Util.printSplitLine();
        System.out.println();
    }

}
