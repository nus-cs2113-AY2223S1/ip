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
            splitInputs = inputs.split(" ");
            if(inputs.equals("list")){
                doPrintlist();
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
            return (isDone ? "X" : " "); // mark done task with X
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
