public class Duke {
    public static void main(String[] args) {
        newLine();
        welcomeGreeting();
        newLine();
        goodbyeGreeting();
        newLine();
    }

    private static void newLine(){
        for (int i = 0; i < 60; i++) {
            System.out.print('_');
        }
        System.out.print('\n');
    }

    private static void welcomeGreeting(){
        System.out.println(" Hello! I'm Duke");
        System.out.println(" What can I do for you?");
    }

    private static void goodbyeGreeting(){
        System.out.println(" Bye. Hope to see you again soon!");
    }
}
