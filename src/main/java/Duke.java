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
        ArrayList<String> list = new ArrayList<>();
        while(!a.equals("bye")){
            if(a.equals("list")){
                for(int i = 1; i <= list.size(); i++){
                    System.out.println(i+". "+list.get(i-1));
                }
            }
            else {
                list.add(a);
                System.out.println("added: "+a);
            }
            a = in.nextLine();
        }
        System.out.println();

        System.out.println("Bye");
    }
}
