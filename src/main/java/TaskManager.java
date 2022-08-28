public class TaskManager {
    public Task[] taskList = new Task[100];
    public int numOfTasks = 0;

    public void addTask(String input){
        taskList[numOfTasks] = new Task(input);
        numOfTasks++;
        System.out.println(
                "____________________________________________________________\n"
                        + "added: " + input + "\n"
                        + "____________________________________________________________\n"
        );
    }

    public void markTask(int input){
        if(input > 0 && input <= numOfTasks){
            taskList[input - 1].markAsDone();
        }
    }

    public void unmarkTask(int input){
        if(input > 0 && input <= numOfTasks){
            taskList[input - 1].markAsNotDone();
        }
    }

    public void printList(){
        System.out.println(
                "____________________________________________________________\n"
                        + "Here are the tasks in your list:"
        );
        for(int i = 0; i < numOfTasks; i ++) {
            System.out.println(
                    (i+1) + "."
                            + taskList[i].getStatusIcon()
                            + taskList[i].description
            );
        }
        System.out.println(
                "____________________________________________________________\n"
        );
    }
}
