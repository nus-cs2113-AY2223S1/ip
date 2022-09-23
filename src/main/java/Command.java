public class Command {

    String commandAction;

    public Command(String commandAction) {
        this.commandAction = commandAction;
    }

    public boolean executeCommand(TaskList tasks, String fullCommand) {
        switch (commandAction.toLowerCase()) {
            case "bye":
                return true;
            case "list":
                //print the task list
                tasks.printList();
                break;
            case "todo":
                //add todo to task list, no date
                if(commandAction.matches(fullCommand)){
//                    throw new DukeException();
                    System.out.println("OOPS! The description of todo cannot be empty");
                } else {
                    tasks.addTodo(commandAction, fullCommand);
                }
                break;
            case "deadline":
                //add deadline to task list, got date
                if(commandAction.matches(fullCommand)){
//                    throw new DukeException();
                    System.out.println("OOPS! The description of deadline cannot be empty");
                } else {
                    tasks.addDeadline(commandAction, fullCommand);
                }
                break;
            case "event":
                //add event to task list, got date
                if(commandAction.matches(fullCommand)){
//                    throw new DukeException();
                    System.out.println("OOPS! The description of event cannot be empty");
                } else {
                    tasks.addEvent(commandAction, fullCommand);
                }
                break;
            case "mark":
                tasks.markDone(fullCommand);
                break;
            case "unmark":
                tasks.markUndone(fullCommand);
                break;
            case "delete":
                tasks.deleteTask(fullCommand);
                break;
            default:
                System.out.println("OOPS! I'm sorry, I don't know what that means :(");
        }
        return false;
    }
}
