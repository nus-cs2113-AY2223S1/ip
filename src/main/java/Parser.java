import task.Deadline;
import task.Event;
import task.Todo;

public class Parser {
    private static final String EVENT = "event";
    private static final String TODO = "todo";
    private static final String DEADLINE = "deadline";

    private final String[] taskName = {"todo","event",  "deadline"};

    public static String commandParser(String val, Ui ui){
        if(val == null || val.isEmpty()){
            ui.emptyMsg();
            return null;
        }
        return "";
    }

    public boolean quit(String val){
        if(val.equals("quit") || val.equals("bye")){
            return true;
        }

        return false;
    }


    public boolean isList(String val){
        if (val.length() >= 4 && val.equals("list")) {
            return true;
        }

        return false;
    }

    public boolean is(String val, String check){
        if (val.length() >= check.length() && val.substring(0,check.length()).equals(check)) {
            return true;
        }

        return false;
    }

    public boolean isUnmark(String val){
        String check = "unmark";
        if (val.length() >= check.length() && val.substring(0,check.length()).equals(check)) {
            return true;
        }

        return false;
    }

    public boolean isDelete(String val){
        String check = "delete";
        if (val.length() >= check.length() && val.substring(0,check.length()).equals(check)) {
            return true;
        }

        return false;
    }

    public int indexIs(String val, Ui ui, int length){
        int startId = val.indexOf(" ");
        if (startId < 0) {
            ui.indexEmptyMsg();
            return -1;
        }

        int index = Integer.parseInt(val.substring(startId + 1)) - 1;

        if (isValidNumber(index, length)) {
            return index;
        }

        ui.indexOutOfBoundMsg();
        return -1;
    }

    public boolean isValidNumber(int val, int length){
        if(val < 1 || val > length){
            return false;
        }

        return true;
    }

    public String taskType(String val, Ui ui){
        if(val.indexOf(" ") == -1){
            ui.unKnownMsg();
            return null;
        }

        String type = val.substring(0,val.indexOf(" ") + 1);

        if (type.length() == TODO.length() + 1 && val.substring(0,TODO.length()).equals(TODO)) {
            if (val.substring(TODO.length()).trim().isEmpty()) {
                Ui.emptyErrorMsg(TODO);
                return null;
            }

            return  TODO;
        } else if (val.length() >= DEADLINE.length() + 1 && val.substring(0,DEADLINE.length()).equals(DEADLINE)) {
            if (val.substring(DEADLINE.length()).trim().isEmpty()) {
                Ui.emptyErrorMsg(DEADLINE);
                return  null;
            }

            return  DEADLINE;
        } else if (val.length() >= EVENT.length() + 1&& val.substring(0,EVENT.length()).equals(EVENT)) {
            if (val.substring(EVENT.length()).trim().isEmpty()) {
                Ui.emptyErrorMsg(EVENT);
                return null;
            }

            return EVENT;
        }

        ui.unKnownMsg();
        return null;
    }

    public String description(String val, Ui ui, String taskType){
        String des = "";
        if(taskType.equals(TODO)){

            des = val.substring(TODO.length()).trim();

        } else if (taskType.equals(EVENT)) {
            if(val.indexOf("/at ") == -1 || val.substring(val.indexOf("/at ") + 3).trim().isEmpty()){
                ui.formatIncorrect();
                return null;
            }

            des = val.substring(taskType.length(), val.indexOf("/at")).trim();

        } else if (taskType.equals(DEADLINE)) {

            if(val.indexOf("/by ") == -1 || val.substring(val.indexOf("/by ") + 3).trim().isEmpty()){
                ui.formatIncorrect();
                return null;
            }

            des =  val.substring(DEADLINE.length(), val.indexOf("/by")).trim();
        }

        if(!des.isEmpty()){
            return des;
        }

        ui.formatIncorrect();
        return null;
    }

    public String time(String val, Ui ui, String taskType){
        if(taskType == TODO) {
            return null;
        }

        if(val.substring(val.indexOf("/") + 3).trim().isEmpty()){
            return null;
        }
        return val.substring((val.indexOf("/") + 4));
    }


}

