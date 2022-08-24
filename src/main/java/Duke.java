public class Duke {
    public static void main(String[] args) {

        String image = "        .--'''''''''--.\n" +
                "     .'      .---.      '.\n" +
                "    /    .-----------.    \\\n" +
                "   /        .-----.        \\\n" +
                "   |       .-.   .-.       |\n" +
                "   |      /   \\ /   \\      |\n" +
                "    \\    | .-. | .-. |    /\n" +
                "     '-._| | | | | | |_.-'\n" +
                "         | '-' | '-' |\n" +
                "          \\___/ \\___/\n" +
                "       _.-'  /   \\  `-._\n" +
                "     .' _.--|     |--._ '.\n" +
                "     ' _...-|     |-..._ '\n" +
                "            |     |\n" +
                "            '.___.'\n" +
                "              | |\n" +
                "             _| |_\n" +
                "            /\\( )/\\\n" +
                "           /  ` '  \\";
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);

        String greetingMessage = "______________________________________\n" +
                "Hello! I'm\n" +
                logo +
                "What can I do for you?\n" +
                "______________________________________\n" +
                "Bye. Hope to see you again soon!\n" +
                "______________________________________\n";

        System.out.println(greetingMessage + image);
    }
}
