import java.util.*;
public class Duke {
    private static ArrayList<Task> lists = new ArrayList<>();
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String inputs;
        String[] splitInputs;
        doGreetings();
        inputs = scanner.nextLine();
        while(!(inputs.equalsIgnoreCase("bye"))) {
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            splitInputs = inputs.split(" ");
            if(inputs.equals("list")){
                doPrintlists();
            }
            else if(splitInputs[0].equalsIgnoreCase("mark")){
                if(splitInputs.length == 1){
                    System.out.println("No index given to mark");
                }
                else if(Integer.parseInt(splitInputs[1]) > lists.size()){
                    System.out.println("There is no task with index " + splitInputs[1]);
                }
                else {
                    lists.get(Integer.parseInt(splitInputs[1]) - 1).setDone(true);
                    doPrintlists();
                }
            }
            else if(splitInputs[0].equalsIgnoreCase("unmark")){
                if(splitInputs.length == 1){
                    System.out.println("No index given to unmark");
                }
                else if(Integer.parseInt(splitInputs[1]) > lists.size()){
                    System.out.println("There is no task with index " + splitInputs[1]);
                }
                else {
                    lists.get(Integer.parseInt(splitInputs[1]) - 1).setDone(false);
                    doPrintlists();
                }
            }
            else if(splitInputs[0].equalsIgnoreCase("todo") || splitInputs[0].equalsIgnoreCase("event") || splitInputs[0].equalsIgnoreCase("deadline")){
                lists.add(new Task(splitInputs));
                System.out.println("\t Added: ");
                lists.get(lists.size() - 1).doPrint();
                System.out.println("\t There are currently " + lists.size() + " task(s) in the list :)");
            }
            else {
                System.out.println("Sorry I do not understand your command :(");
            }
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            inputs = scanner.nextLine();
        }
        doExit();
    }
    public static class Task {
        protected String description = "";

        protected String deadline = "";

        protected char taskType;

        protected boolean isDone;

        public Task(String[] splitInputs) {
            int i = 1;
            this.taskType = Character.toUpperCase(splitInputs[0].charAt(0));
            while(i < splitInputs.length && !(splitInputs[i].contains("at") || splitInputs[i].contains("by"))){
                this.description += splitInputs[i++];
                this.description += " ";
            }
            i += 1;
            while(i < splitInputs.length){
                this.deadline += splitInputs[i++];
                this.deadline += " ";
            }

            this.isDone = false;
        }

        public String getStatusIcon() {
            return (isDone ? "X" : " "); // mark done task with X
        }

        public void setDone(boolean isMark) {
            isDone = isMark;
        }

        public void doPrint(){
            System.out.print("\t [" + this.taskType + "]" +
                    "[" + this.getStatusIcon() + "] " +
                    this.description + "    ");
            if(taskType == 'D') {
                System.out.print("by : ");
            }
            if(taskType == 'E'){
                System.out.print("at : ");
            }
            System.out.println(deadline);
        }
    }

    public static void doPrintlists() {
        for (int i = 0; i < lists.size();i++) {
            System.out.print(i+1 + ": ");
            lists.get(i).doPrint();
        }
    }

    public static void doExit() {
        System.out.println(" Bye! Hope to see you again soon :)");
    }

    public static void doGreetings(){
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo + "\n \n Hello! I'm Duke\n What can I do for you?");
    }
}
