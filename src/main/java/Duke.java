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
            else {
                toDoList.addItem(input);
                window.print("Added \"" + input.trim() + "\" to your list.");
            }
        }
    }

    private static void greet(){
        window.print("Good morning!\nWhat would you like to do today?");
        //window.print("Added jog a looooooooooooooooooooooot\" to your list.");
    }

    private static void exit(){
        window.print("Alright, see you around then!");
    }
}
