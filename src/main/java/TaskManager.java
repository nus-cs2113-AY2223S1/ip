public class TaskManager {

    private static final int TASKS_LIMIT = 100;
    public static Task[] Tasks = new Task[TASKS_LIMIT];
    private static int tasksCount = 0;

    public TaskManager() {
    }

    public static int getTasksCount() {
        return tasksCount;
    }

    public static void addTask(Command taskType, String arguments) {
        tasksCount++;
        int separatorIndex = arguments.indexOf('/');
        switch (taskType) {
        case TODO:
            Tasks[tasksCount] = new ToDo(arguments);
            break;
        case EVENT:
            Tasks[tasksCount] = new Event(arguments.substring(0, separatorIndex - 1), arguments.substring(separatorIndex + 1));
            break;
        case DEADLINE:
            Tasks[tasksCount] = new Deadline(arguments.substring(0, separatorIndex - 1), arguments.substring(separatorIndex + 1));
            break;
        default:
            break;
        }
    }

    public static String listTask(int taskNumber) {
        return Tasks[taskNumber].listTask();
    }

    public static void markAsDone(int taskNumber) {
        Tasks[taskNumber].markAsDone();
    }

    public static void markAsNotDone(int taskNumber) {
        Tasks[taskNumber].markAsNotDone();
    }

}
