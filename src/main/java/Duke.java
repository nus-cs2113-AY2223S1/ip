import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        System.out.println("Hello, my name is Duke! \n What can I do for you?");

        Scanner myObj = new Scanner(System.in);

        Task[] todoList = new Task[100];
        String userInput;
        String subUserInput;
        int markedValue = 0;
        boolean bye = false;

        Commands commandType;
        while (!bye){
            userInput = myObj.nextLine() + " ";
            subUserInput = userInput.substring(0, userInput.indexOf(' '));
            try{
                markedValue = Integer.parseInt(userInput.substring(userInput.indexOf(' ')).replaceAll("\\s+","")) - 1;
            }
            catch(Exception ignored){

            }

            try{
                commandType = Commands.valueOf(subUserInput.toUpperCase());
                switch (commandType) {
                    case LIST:
                        System.out.println("____________________________________________________________");
                        for (int i = 0; i < todoList.length; i++) {
                            if (todoList[i] != null) {
                                System.out.println("\t" + (i + 1) + ") [" + todoList[i].getStatusIcon() +"] " + todoList[i].getDescription());
                            } else {
                                break;
                            }
                        }
                        System.out.println("____________________________________________________________");
                        break;

                    case BYE :
                        bye = true;
                        break;

                    case MARK :
                        todoList[markedValue].markAsDone();
                        System.out.println("____________________________________________________________");
                        System.out.println("Nice! I've marked this task as done:");
                        System.out.println("[" +todoList[markedValue].getStatusIcon() +"]  " + todoList[markedValue].getDescription());
                        System.out.println("____________________________________________________________");
                        break;

                    case UNMARK :
                        todoList[markedValue].unMark();
                        System.out.println("____________________________________________________________");
                        System.out.println("OK, I've marked this task as not done yet:");
                        System.out.println("[" +todoList[markedValue].getStatusIcon() +"]  " + todoList[markedValue].getDescription());
                        System.out.println("____________________________________________________________");
                }
            }
            catch(Exception e){
                for(int i = 0; i < todoList.length; i++)
                    if(todoList[i] == null) {
                        Task newTask = new Task(userInput);
                        todoList[i] = newTask;
                        break;
                    }
                System.out.println("____________________________________________________________");
                System.out.println("\t Added: " + userInput);
                System.out.println("____________________________________________________________");
            }
        }
        System.out.println("____________________________________________________________");
        System.out.println("\tBye. Hope to see you soon!");
        System.out.println("____________________________________________________________");
    }

    enum Commands{
        LIST,
        BYE,
        MARK,
        UNMARK
    }

    public static class Task {
        protected String description;
        protected boolean isDone;

        public Task(String description) {
            this.description = description;
            this.isDone = false;
        }

        public void markAsDone(){
            this.isDone = true;
        }

        public void unMark(){
            this.isDone = false;
        }

        public String getStatusIcon() {
            return (isDone ? "X" : ""); // mark done task with X
        }

        public String getDescription() {
            return description;
        }
    }
}
