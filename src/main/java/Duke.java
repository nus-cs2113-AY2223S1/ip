public class Duke {
    public static Display window = new Display();
    public static ToDoList toDoList = new ToDoList();
    public static void main(String[] args) {
        greet();
        programBody();
        exit();
    }

    public static void programBody(){
        while(true){
            String input = window.getInput();
            if (input.equalsIgnoreCase("bye")) {
                break;
            }
            else if (input.equalsIgnoreCase("list")) {
                window.print(toDoList.toString());
            }
            else if (stringContains(input, "mark")){
                int itemIndex = Integer.parseInt(input.split(" ")[1]) - 1;
                toDoList.mark(itemIndex);
                window.print("Marked task \"" + toDoList.getTextOfItem(itemIndex) + "\" as done.");
            }
            else if (stringContains(input,"unmark")){
                int itemIndex = Integer.parseInt(input.split(" ")[1]) - 1;
                toDoList.unmark(itemIndex);
                window.print("Marked task \"" + toDoList.getTextOfItem(itemIndex) + "\" as not yet done.");
            }
            else {
                toDoList.addItem(input);
                window.print("Added \"" + input.trim() + "\" to your list.");
            }
        }
    }

    private static void greet(){
        window.print("Good morning!\nWhat would you like to do today?");
    }

    private static void exit(){
        window.print("Alright, see you around then!");
    }

    private static boolean stringContains(String input, String substring){
        return input.split(" ")[0].equalsIgnoreCase(substring);
    }
}
