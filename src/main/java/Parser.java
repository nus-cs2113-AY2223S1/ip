/**
 * understand user input
 */
public class Parser {
    private final String EVENT = "event";
    private final String TODO = "todo";
    private final String DEADLINE = "deadline";

    /**
     * check whether the user wants to quit  the program
     * If the user wants to quit, true is returned. Vice versa
     *
     * @param val input of user
     * @return whether the user wants to quit
     */
    public boolean quit(String val){
        if(val.equals("quit") || val.equals("bye")){
            return true;
        }

        return false;
    }

    /**
     * check whether the input is "list"
     * If its is list, true is returned. Vice versa
     *
     * @param val input of user
     * @return whether val is list
     */
    public boolean isList(String val){
        if (val.equals("list")) {
            return true;
        }

        return false;
    }

    /**
     * check whether the input is the same as the content in check
     * If they are the same, true is returned. Vice versa
     *
     * @param val input of user
     * @param check the string compared too
     * @return whether val is check
     */
    public boolean is(String val, String check){
        if (val.length() >= check.length() && val.substring(0,check.length()).equals(check)) {
            return true;
        }

        return false;
    }


    /**
     * returns what is the index mentioned in user input
     * returns -1 when the input or index is invalid
     *
     * @param val input of user
     * @param ui deals with interactions with the user
     * @param length the number of tasks in the list
     * @return the index of task
     */
    public int indexIs(String val, Ui ui, int length){
        int startId = val.indexOf(" ");
        if (startId < 0) {
            ui.indexEmptyMsg();
            return -1;
        }

        String content = val.substring(startId + 1).trim();

        if(!isInt(content)){
            ui.formatIncorrect();
            return -1;
        }

        int index = Integer.parseInt(content) - 1;

        if (isValidNumber(index, length)) {
            return index;
        }

        ui.indexOutOfBoundMsg();
        return -1;
    }

    /**
     * check whether is the number valid
     * returns false when the input is invalid. Vice versa
     *
     * @param val input of user
     * @param length the number of tasks in the list
     * @return whether is the number valid
     */
    public boolean isValidNumber(int val, int length){
        if(val < 0 || val >= length){
            return false;
        }

        return true;
    }

    /**
     * check what is task mentioned in user input
     * returns null when the input is invalid
     *
     * @param val input of user
     * @param ui deals with interactions with the user
     * @return the task type
     */
    public String taskType(String val, Ui ui){
        if(!val.contains(" ")){
            ui.unKnownMsg();
            return null;
        }

        String type = val.substring(0,val.indexOf(" ") + 1);

        if (type.length() == TODO.length() + 1 && val.substring(0,TODO.length()).equals(TODO)) {
            if (val.substring(TODO.length()).trim().isEmpty()) {
                ui.emptyErrorMsg(TODO);
                return null;
            }

            return  TODO;
        } else if (val.length() >= DEADLINE.length() + 1 && val.substring(0,DEADLINE.length()).equals(DEADLINE)) {
            if (val.substring(DEADLINE.length()).trim().isEmpty()) {
                ui.emptyErrorMsg(DEADLINE);
                return  null;
            }

            return  DEADLINE;
        } else if (val.length() >= EVENT.length() + 1&& val.substring(0,EVENT.length()).equals(EVENT)) {
            if (val.substring(EVENT.length()).trim().isEmpty()) {
                ui.emptyErrorMsg(EVENT);
                return null;
            }

            return EVENT;
        }

        ui.unKnownMsg();
        return null;
    }

    /**
     * find the description of the task mentioned in the input
     * returns null when the description is invalid
     *
     * @param val input of user
     * @param ui deals with interactions with the user
     * @param taskType the type of task entered
     * @return the description
     */
    public String description(String val, Ui ui, String taskType){
        String des = "";
        if(taskType.equals(TODO)){

            des = val.substring(TODO.length()).trim();

        } else if (taskType.equals(EVENT)) {
            if(!val.contains(" /at ") || val.substring(val.indexOf(" /at ") + 4).trim().isEmpty()){
                ui.formatIncorrect();
                return null;
            }

            des = val.substring(taskType.length(), val.indexOf(" /at")).trim();

        } else if (taskType.equals(DEADLINE)) {

            if(!val.contains(" /by ") || val.substring(val.indexOf(" /by ") + 4).trim().isEmpty()){
                ui.formatIncorrect();
                return null;
            }

            des =  val.substring(DEADLINE.length(), val.indexOf(" /by")).trim();
        }

        if(!des.isEmpty()){
            return des;
        }

        ui.formatIncorrect();
        return null;
    }

    /**
     * find the time mentioned in user input
     * returns null when the time is invalid
     *
     * @param val input of user
     * @param taskType the type of task entered
     * @return the time mentioned in user input
     */
    public String time(String val, String taskType){
        if(taskType.equals(TODO)) {
            return null;
        }

        if(val.substring(val.indexOf("/") + 3).trim().isEmpty()){
            return null;
        }
        return val.substring((val.indexOf("/") + 4));
    }

    public String getKeyword(String val, Ui ui){
        String key = val.substring(4).trim();
        if(key.isEmpty()){
            ui.formatIncorrect();
            return null;
        }

        return key;
    }

    public boolean isInt(String val){

        Boolean strResult = val.matches("-?\\d?");
        if(strResult == true) {
            return true;
        }

        return false;
    }


}

