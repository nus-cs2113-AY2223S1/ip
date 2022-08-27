public class TaskManager {
    protected Task[] tasks;
    protected int taskCount;

    public TaskManager() {
        tasks = new Task[100];
        taskCount = 0;
    }

    public void addTodo(String input) {
        String myTask = input.substring(5);
        tasks[taskCount] = new Todo(myTask);
        System.out.println("Added: " + tasks[taskCount].toString());
        taskCount++;
        System.out.println("Total tasks = " + taskCount);
    }

    public void addDeadline(String input) {
        int indexSlash = input.indexOf('/');
        if (indexSlash == -1) {
            return;
        }
        String myTask = input.substring(9, indexSlash-1);
        String by = input.substring(indexSlash+4);
        tasks[taskCount] = new Deadline(myTask, by);
        System.out.println("Added: " + tasks[taskCount].toString());
        taskCount++;
        System.out.println("Total tasks = " + taskCount);
    }

    public void addEvent(String input) {
        int indexSlash = input.indexOf('/');
        if (indexSlash == -1) {
            return;
        }
        String myTask = input.substring(6, indexSlash-1);
        String at = input.substring(indexSlash+4);
        tasks[taskCount] = new Event(myTask, at);
        System.out.println("Added: " + tasks[taskCount].toString());
        taskCount++;
        System.out.println("Total tasks = " + taskCount);
    }

    public void markAsDone(String input) {
        int taskNum = Integer.parseInt(input.substring(input.length() - 1));
        taskNum--;
        if (taskNum >= 0 && taskNum < taskCount) {
            tasks[taskNum].isDone = true;
            System.out.println("Marked: ");
            System.out.println(tasks[taskNum].toString());
        }
    }

    public void removeMark(String input) {
        int taskNum = Integer.parseInt(input.substring(input.length() - 1));
        taskNum--;
        if (taskNum >= 0 && taskNum < taskCount) {
            tasks[taskNum].isDone = false;
            System.out.println("Unmarked: ");
            System.out.println(tasks[taskNum].toString());
        }
    }

    public void print() {
        for (int i = 0; i < taskCount; i++) {
            System.out.print(i+1);
            System.out.print(". ");
            System.out.println(tasks[i].toString());
        }
    }

}
