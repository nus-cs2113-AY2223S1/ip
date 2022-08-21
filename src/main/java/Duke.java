import java.util.*;
public class Duke {
    private static ArrayList<Task> list = new ArrayList<>();
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String inputs;
        String[] splitInputs;
        doGreetings();
        inputs = scanner.nextLine();
        while(!inputs.equals("bye") ) {
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            splitInputs = inputs.split(" ", 2);
            if(inputs.equals("list")){
                doPrintlist();
            }
            else if(splitInputs[0].equals("mark")){
                if(Integer.parseInt(splitInputs[1]) > list.size()){
                    System.out.println("There is no task with index " + splitInputs[1]);
                }
                else {
                    list.get(Integer.parseInt(splitInputs[1]) - 1).setDone(true);
                    doPrintlist();
                }
            }
            else if(splitInputs[0].equals("unmark")){
                if(Integer.parseInt(splitInputs[1]) > list.size()){
                    System.out.println("There is no task with index " + splitInputs[1]);
                }
                else {
                    list.get(Integer.parseInt(splitInputs[1]) - 1).setDone(false);
                    doPrintlist();
                }
            }
            else {
                System.out.println("\t Added: " + inputs);
                list.add(new Task(inputs));
            }
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            inputs = scanner.nextLine();
        }
        doExit();
    }
    public static class Task {
        protected String description;
        protected boolean isDone;

        public Task(String description) {
            this.description = description;
            this.isDone = false;
        }

        public String getStatusIcon() {
            return (isDone ? "X" : "O"); // mark done task with X
        }

        public void setDone(boolean isMark) {
            isDone = isMark;
        }
    }

    public static void doPrintlist() {
        for (int i = 0; i < list.size();i++) {
            System.out.println(i+1 + ": " + " [" + list.get(i).getStatusIcon() + "] " + list.get(i).description );
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
