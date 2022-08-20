public class Task {
    private String name;

    private static int tasksCount = 0;

    public Task(String name) {
        setName(name);
        //itemsCount++;
    }

    public void setName(String name) {
        this.name = name;
        tasksCount++;
    }

    public String getName() {
        return name;
    }

    public static int getTasksCount() {
        return tasksCount;
    }

    public static void listAllItems() {
    }
}
