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
                String[] inputs = input.split(" +", 2);
                if (inputs[0].equals("todo")){
                    dukeController.addToDo(inputs[1]);

                } else if (inputs[0].equals("event")) {
                    String[] eventInfo = inputs[1].split("/",2);
                    dukeController.addEvent(eventInfo[0],eventInfo[1]);
                } else if (inputs[0].equals("deadline")){
                    String[] deadlineInfo = inputs[1].split("/", 2);
                    dukeController.addDeadline(deadlineInfo[0], deadlineInfo[1]);
                }
            }

        }
    }
}
