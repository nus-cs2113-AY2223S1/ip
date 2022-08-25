public class Duke {
    public static void main(String[] args) {
        DukeController dukeController = new DukeController();
        dukeController.printWelcome();
        while (true){
            String input = dukeController.getInput();
            if (input.equals("bye")){
                dukeController.printGoodbye();
                return;
            } else if (input.equals("list")){
                dukeController.printTaskList();
            } else if (input.startsWith("mark")){
                int index = Integer.parseInt(input.substring(5));
                dukeController.markTask(index);
            } else if (input.startsWith("unmark")) {
                int index = Integer.parseInt(input.substring(7));
                dukeController.unmarkTask(index);
            } else {
                dukeController.add(input);
            }

        }
    }
}
