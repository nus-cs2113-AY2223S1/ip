public class List {
    private static int amountOfItems = 0;
    private static Task[] tasks;


    public List() {
        tasks = new Task[100];
    }

    public int getListSize() {
        return amountOfItems;
    }

    public void addTask(String input) {
        if (input.contains("/by") || input.split(" ")[0].equals("deadline")) {
            if (input.split(" ", 2)[0].equals("deadline")) {
                input = input.split(" ", 2)[1];
            }
            String[] details = input.split(" /by ");
            tasks[amountOfItems] = new Deadline(details[0], details[1]);
            tasks[amountOfItems] = new Deadline(details[0], details[1]);
        } else if (input.contains("/at") || input.split(" ")[0].equals("event")) {
            if (input.split(" ", 2)[0].equals("event")) {
                input = input.split(" ", 2)[1];
            }
            String[] details = input.split(" /at ");
            tasks[amountOfItems] = new Event(details[0], details[1]);
        } else {
            if (input.split(" ", 2)[0].equals("todo")) {
                input = input.split(" ", 2)[1];
            }
            tasks[amountOfItems] = new Todo(input);
        }
        Message.printingHorizontalLine();
        System.out.println("Got it. I've added this task:");
        System.out.println(tasks[amountOfItems]);
        System.out.println("Now you have " + (amountOfItems + 1) + " tasks in the list.");
        Message.printingHorizontalLine();
        amountOfItems++;
    }

    public void printingList() {
        Message.printingHorizontalLine();
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < amountOfItems; i++) {
            System.out.print(i + 1 + ". ");
            System.out.println(tasks[i]);
        }
        Message.printingHorizontalLine();
    }

    public void markingItem(int i) {
        tasks[i-1].markDone();
    }

    public void unmarkingItem(int i) {
        tasks[i-1].unmarkDone();
    }


}
