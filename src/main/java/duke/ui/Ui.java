package duke.ui;

public class Ui {
    static final String LOGO = "   _____                .__   \n" +
            "  /  _  \\ ___  ___ ____ |  |  \n" +
            " /  /_\\  \\\\  \\/  // __ \\|  |  \n" +
            "/    |    \\>    <\\  ___/|  |__\n" +
            "\\____|__  /__/\\_ \\\\___  >____/\n";

    public static void greetUser() {
        System.out.println(LOGO);
        String greeting = "____________________________________________________________\n"
                + "Hello! My name is Axel. :-)\n"
                + "How may I help you today?\n"
                + "____________________________________________________________";
        System.out.println(greeting);
    }

    public static void sayByeToUser() {
        String message = "____________________________________________________________\n"
                + "Goodbye. Hope to see you again soon!\n"
                + "____________________________________________________________";
        System.out.println(message);
    }
}
