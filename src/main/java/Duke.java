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
        String a = in.nextLine();
        ArrayList<Task> list = new ArrayList<>();
        while(!a.equals("bye")){
            if(a.equals("list")){
                for(int i = 1; i <= list.size(); i++){
                    System.out.print(i+". ");
                    list.get(i-1).printTask();
                }
            }
            else if (a.contains("mark")){
                int num = Integer.parseInt(a.substring(a.indexOf(" ")+1))-1;
                if(num>list.size()){
                    System.out.println("Out of bounds of the list");
                }
                else{
                    if(a.contains("unmark")){
                        System.out.println("I have marked this task as not done yet");
                        list.get(num).setDone(false);
                        list.get(num).printTask();
                    }
                    else{
                        System.out.println("I have marked this task as complete");
                        list.get(num).setDone(true);
                        list.get(num).printTask();
                    }
                }

            }
            else {
                list.add(new Task(a, false));
                System.out.println("added: "+a);
            }
            a = in.nextLine();
        }
        System.out.println();

        System.out.println("Bye");
    }
}
