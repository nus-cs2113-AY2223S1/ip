public class Duke {

    public static void greetUser(){
        String greeting = "____________________________________________________________" + System.lineSeparator()
        + "Hello! My name is Axel. :-)" + System.lineSeparator()
        + "How may I help you today?" + System.lineSeparator()
        + "____________________________________________________________" + System.lineSeparator()
        + "Goodbye. Hope to see you again soon!" + System.lineSeparator()
        + "____________________________________________________________";
        System.out.println(greeting);
    }
    public static void main(String[] args) {
        String logo = "   _____                .__   \n" +
                "  /  _  \\ ___  ___ ____ |  |  \n" +
                " /  /_\\  \\\\  \\/  // __ \\|  |  \n" +
                "/    |    \\>    <\\  ___/|  |__\n" +
                "\\____|__  /__/\\_ \\\\___  >____/\n";
        System.out.println(logo);
        greetUser();
    }
}
