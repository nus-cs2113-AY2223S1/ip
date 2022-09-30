package main.java;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    static ArrayList<Task> list = new ArrayList<>();
    /*public Storage(TaskList tasks){
        list = tasks.getAllTasks();
    }*/
    public static void listToFile(TaskList tasks){
        list = tasks.getAllTasks();
        try{
            File f = new File("docs/duke.txt");
            try{
                if(!(f.createNewFile())){
                    f.delete();
                    f = new File("docs/duke.txt");
                }
            }
            catch(IOException e){

            }
            FileWriter fw = new FileWriter(f);
            BufferedWriter bw = new BufferedWriter(fw);
            for(Task t : list){
                String don;
                if(t.getDone()){
                    don = "True";
                }
                else{
                    don = "False";
                }
                if(t.classInfo().equals("Todo") || t.classInfo().equals("Task")){
                    bw.write("T," + don + "," + t.getName() + System.lineSeparator());
                }
                else if(t.classInfo().equals("Event")){
                    bw.write("E,"+don+","+t.getName()+","+t.getDate()+System.lineSeparator());
                }
                else if(t.classInfo().equals("Deadline")){
                    bw.write("D,"+don+","+t.getName()+","+t.getDate()+System.lineSeparator());
                }
            }
            bw.flush();
            bw.close();
        }
        catch (IOException e){
            System.out.println("Problem with file");
        }

    }
    public ArrayList<Task> fileToList(){
        File f = new File("docs/duke.txt");

        try{
            if(!(f.createNewFile())){
                Scanner sc = new Scanner(f);
                while(sc.hasNextLine()) {
                    String line = sc.nextLine();
                    String[] lin = line.split(",");
                    Boolean temp;
                    temp = lin[1].equals("True");
                    if(lin[0].equals("T")){
                        list.add(new Todo(lin[2], temp));
                    }
                    else if(lin[0].equals("D")){
                        list.add(new Deadline(lin[2], temp, lin[3]));
                    }
                    else if(lin[0].equals("E")){
                        list.add(new Event(lin[2], temp, lin[3]));
                    }
                }
            }
            return list;
        }
        catch (IOException e){
            System.out.println("Problem occurred with the file");
            return list;
        }
    }
}
