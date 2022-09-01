import java.util.Scanner;
import java.util.Vector;

public class Duke {
    public class Task {
        protected String description;
        protected boolean isDone;
        protected String taskType;
        protected String taskDate;
    
        public Task(String description,boolean isDone, String taskType, String taskDate) { // initiating an instance
            this.description = description;
            this.isDone = isDone;
            this.taskType = taskType; // todo,ddl,event
            this.taskDate = taskDate; // 

        }

        public String getTaskType(){
            return (this.taskType);
        }
    
        public String getStatusIcon() {
            return (isDone ? "X" : " "); // mark done task with X
        }
    
        public void markAsDone(){
            this.isDone = true;
        }

        public void markAsNotDone(){
            this.isDone = false;
        }

        public String getDescription(){
            return this.description;

        }

        public String getDate(){
            return this.taskDate;
    }
}


    // Driver code
    public static void main(String[] args) {
        // Greeting message
        String logo = " ____        _        \n"
                    + "|  _ \\ _   _| | _____ \n"
                    + "| | | | | | | |/ / _ \\\n"
                    + "| |_| | |_| |   <  __/\n"
                    + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke_HTT. \n What can I do for you?");
        
        // Take user inputt
        Scanner scan = new Scanner ( System.in ); 
        String inData = scan.nextLine();

        // Processing input - 1st time
        Vector<Task> tasks = new Vector<>();
        
        if (inData.equals("bye")){
            System.out.println(" \n Bye. Hope to see you again soon!");
        } else if (inData.equals("list")){
            for (Task task:tasks){
                System.out.println("Here are the tasks in your list: \n" 
                                    + (tasks.indexOf(task) + 1) 
                                    + "." + "[" + task.getStatusIcon() + "] " 
                                    + task.getDescription());   
            }
        } else{
            Task t = new Task(inData);
            tasks.add(t);
            System.out.println("added: " + t.getDescription());
        }

        // Processing input - loop
        Boolean isBye = false;

        while (!isBye) {

            inData = scan.nextLine();
            
            if (inData.equals("bye")){
                isBye = true;
                System.out.println(" \n Bye. Hope to see you again soon!");
            } else if (inData.equals("list")){
                for (Task task:tasks){
                    System.out.println("Here are the tasks in your list: \n" 
                                        + (tasks.indexOf(task) + 1) 
                                        + "[" + task.getDescription + "]"
                                        + "." + "[" + task.getStatusIcon() + "] " 
                                        + task.getDescription());
                }
            } else if (inData.contains("mark") && !inData.contains("unmark")){
                int inDataIndex = Integer.parseInt(inData.substring(5));
                inDataIndex--;
                tasks.get(inDataIndex).markAsDone();
                Task task = tasks.get(inDataIndex);
                System.out.println("Nice! I've marked this task as done: \n" + "[" 
                + task.getStatusIcon() + "] " + task.getDescription());
            } else if (inData.contains("unmark")){
                int inDataIndex = Integer.parseInt(inData.substring(7));
                inDataIndex--;
                tasks.get(inDataIndex).markAsNotDone();
                Task task = tasks.get(inDataIndex);
                System.out.println("OK, I've marked this task as not done yet: \n" + "[" 
                + task.getStatusIcon() + "] " + task.getDescription());
            } else if (inData.substring(0,4).equals("todo")){
                Task t = new Task(inData.substring(4),false,"T");
                tasks.add(t);
                System.out.println("Got it. I've added this task: \n" + "["
                + task.getDescription + "]" + "[" + task.getStatusIcon() + "] "
                + tasl.getDescription());
                System.out.println("Now you have %2d tasks in the list.".formatted(tasks.size()));
            } else if (inData.substring(0,8).equals("deadline")){
                int taskEndIndex = inData.indexOf("/");
                Task t = new Task(inData.substring(9,taskEndIndex),false,"D",
                inData.substring(taskEndIndex+1));
                System.out.println("Got it. I've added this task: \n" + "["
                + task.getDescription + "]" + "[" + task.getStatusIcon() + "] "
                + task.getDescription() + "by: " + task.getDate());
                System.out.println("Now you have %2d tasks in the list.".formatted(tasks.size()));
            } else if (inData.substring(0,5).equals("event")){
                int taskEndIndex = inData.indexOf("/");
                Task t = new Task(inData.substring(5,taskEndIndex),false,"E",
                inData.substring(taskEndIndex+1));
                System.out.println("Got it. I've added this task: \n" + "["
                + task.getDescription + "]" + "[" + task.getStatusIcon() + "] "
                + task.getDescription() + task.getDate());
                System.out.println("Now you have %2d tasks in the list.".formatted(tasks.size()));
            } else{
                Task t = new Task(inData);
                tasks.add(t);
                System.out.println("added: " + t.getDescription());
            }
        }  
    }
}
