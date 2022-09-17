package duke.command;


import java.util.ArrayList;

import duke.data.task.Task;

public class CommandResult {
    public CommandResult(String top){
        this.messageTop = top;
    }
    public CommandResult(String top,ArrayList tasks , String bottom){
        this.messageTop = top;
        this.messageBottom = bottom;
        this.taskManipulated = tasks;
    }

    public String[] toStringArray(){
        String[] Array = {"",""};
        return Array;
    }
    public String messageTop;
    public String messageBottom;
    public ArrayList<Task> taskManipulated;

}
