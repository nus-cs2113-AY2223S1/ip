public class TaskManager {
    private Task[] tasks = new Task[100];
    private int tasksCount = 0;

    public void addTask(String type, String description){
        if(type.equals("todo")){
            tasks[tasksCount] = new Todo(description);
        }
        else{
            String[] text = description.split(" ");
            String task = "";
            int indexOfTime = 0;
            for(int i = 0; i < text.length; i++){
                if(text[i].contains("/")){
                    indexOfTime = i;
                    break;
                }
                task = task + " " + text[i];
            }
            indexOfTime++;
            String time = "";
            for(int i = indexOfTime; i < text.length; i++){
                time = time + " " + text[i];
            }
            if(type.equals("deadline")){
                tasks[tasksCount] = new Deadline(task, time);
            }
            else if(type.equals("event")){
                tasks[tasksCount] = new Event(task, time);
            }
        }
        String acknowledgement = "____________________________________________________________" + System.lineSeparator()
                + "Got it. I've added this task: " + System.lineSeparator()
                + " " + tasks[tasksCount].toString() + System.lineSeparator();
        System.out.println(acknowledgement);
        tasksCount++;
        System.out.println("Now you have " + tasksCount + " tasks in the list.");
        System.out.println("____________________________________________________________");
    }

    public void listTasks(){
        System.out.println("____________________________________________________________");
        System.out.println("Here are the tasks in your list:");
        for(int i = 0; i < tasksCount; i++){
            System.out.println((i+1) + "." + tasks[i].toString());
        }
        System.out.println("____________________________________________________________");
    }

    public void markAsDone(int taskNumber){
        int taskIndex = taskNumber - 1;
        tasks[taskIndex].isDone = true;
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("[X] " + tasks[taskIndex].description);
        System.out.println("____________________________________________________________");
    }

    public void markAsUndone(int taskNumber){
        int taskIndex = taskNumber - 1;
        tasks[taskIndex].isDone = false;
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println("[] " + tasks[taskIndex].description);
        System.out.println("____________________________________________________________");
    }
}
