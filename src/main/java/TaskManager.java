public class TaskManager {
    private Task[] tasks = new Task[100];
    private int tasksCount = 0;

    public void addTask(String description){
        tasks[tasksCount] = new Task(description);
        String acknowledgement = "____________________________________________________________" + System.lineSeparator()
                + "added: " + tasks[tasksCount].description + System.lineSeparator()
                + "____________________________________________________________";
        System.out.println(acknowledgement);
        tasksCount++;
    }

    public void listTasks(){
        System.out.println("____________________________________________________________");
        System.out.println("Here are the tasks in your list:");
        for(int i = 0; i < tasksCount; i++){
            System.out.println((i+1) + ".[" + tasks[i].getStatusIcon() + "] " + tasks[i].description);
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
