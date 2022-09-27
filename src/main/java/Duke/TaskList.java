package Duke;

import Duke.task.Task;
import java.util.ArrayList;
public class TaskList {
    public ArrayList<Task> list= new ArrayList<>();
    public Boolean isLast = false;

    public void executeTask (String commandType, String commandActual, String fileLocation) throws IllegalCommand{
        try {
            switch (commandType) {
            case "unmark":
                Ui.setUnmarked(list,commandActual);
                break;
            case "mark":
                Ui.setMarked(list,commandActual);
                break;
            case "todo":
                Ui.newToDo(list, commandActual);
                break;
            case "deadline":
                Ui.newDeadline(list,commandActual);
                break;
            case "event":
                Ui.newEvent(list,commandActual);
                break;
            case "list":
                Ui.listTasks(list);
                break;
            case "delete":
                Ui.deleteTask(list, commandActual);
                break;
            case "bye":
                isLast = true;
                break;
            default:
                throw new IllegalCommand();
            }
        } catch (EmptyToDo e) {
            Ui.showEmptyToDo();
        } catch (IllegalCommand e) {
            Ui.showIllegalCommand();
        }
    }



}
