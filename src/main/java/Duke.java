package main.java;

import java.util.*;
public class Duke {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you");
        String inp = in.nextLine();
        ArrayList<Task> list = new ArrayList<>();
        while(!inp.equals("bye")){
            if(inp.equals("list")) {
                for (int i = 1; i <= list.size(); i++) {
                    System.out.print(i + ". ");
                    System.out.println(list.get(i - 1));
                }
            }
            else if (inp.contains("mark")){
                int num = Integer.parseInt(inp.substring(inp.indexOf(" ")+1))-1;
                if(num>list.size()){
                    System.out.println("Out of bounds of the list");
                }
                else{
                    if(inp.contains("unmark")){
                        System.out.println("I have marked this task as not done yet");
                        list.get(num).setDone(false);
                        System.out.println(list.get(num));
                    }
                    else{
                        System.out.println("I have marked this task as complete");
                        list.get(num).setDone(true);
                        System.out.println(list.get(num));
                    }
                }

            }
            else {
                String comm = inp.substring(0, inp.indexOf(" ")).toLowerCase();
                String tas = inp.substring(inp.indexOf(" ")+1);

                if(comm.equals("deadline")) {
                    String name = tas.substring(0, tas.indexOf("/")-1);
                    String time = tas.substring(tas.indexOf("/"));
                    String date = time.substring(time.indexOf(" ")+1);
                    list.add(new Deadline(name, false, date));
                }
                else if(comm.equals("event")){
                    String name = tas.substring(0, tas.indexOf("/")-1);
                    String time = tas.substring(tas.indexOf("/"));
                    String date = time.substring(time.indexOf(" ")+1);
                    list.add(new Event(name, false, date));
                }
                else if(comm.equals("todo")) {
                    list.add(new Todo(tas, false));
                }
                else{
                    list.add(new Task(inp, false));

                }
                System.out.println("Got it. I have added this task: ");
                System.out.println(list.get(list.size()-1));
                System.out.println("Now you have "+list.size()+" tasks in your list.");
            }
            inp = in.nextLine();
        }
        System.out.println();

        System.out.println("Bye");
    }
}
