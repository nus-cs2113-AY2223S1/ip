package UI;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import TaskManager.TaskList;
import TaskManager.Task;

public class FileHandler {

    public static ArrayList<String> Lines = new ArrayList<String>();

    /*
     * Creates File and Directory if don't already exist
     * Run at start of program
     */
    public static void initFiles(){
        File dataDir = new File("./DataDir");
        if (!dataDir.exists()) dataDir.mkdir();

        try{
            File dataFile = new File("./DataDir/Data.txt");
            if (!dataFile.exists()) dataFile.createNewFile();
        } catch (Exception e){
            System.out.println("Failed to create file!");
        }
    }

    /*
     * Updates the taskList information based on previous entries into data file
     * Run at start of program
     */
    public static void loadList(){
        /*
         * Variables for iterating through data file
         */
        String currLine;
        String[] currLineList;
        int currIndex = 0;
        
        /*
         * Variables for each task when updating taskList
         */
        String taskType;
        String taskStatusIcon;
        String taskDescription;

        /*
         * Readers to read data file
         */
        FileReader fr;
        BufferedReader br;

        /*
         * Data File
         */
        File dataFile = new File("./DataDir/Data.txt");

        try{
            fr = new FileReader(dataFile);
            br = new BufferedReader(fr);

            /*
             * Reads all lines from data file
             * Extract task information from line
             * Inputs task into tasklist
             */
            while ((currLine = br.readLine()) != null){
                Lines.add(currLine + "\n");
                currLineList = currLine.split(" - ");


                taskType = currLineList[0];
                taskStatusIcon = currLineList[1].substring(1,currLineList[1].length()-1);
                taskDescription = currLineList[2].substring(0,currLineList[2].length());

                if (taskType.equals("E")){
                    String taskDateTime = currLineList[3].substring(0,currLineList[3].length()); 
                    TaskList.addEvent(taskDescription, taskDateTime);
                    if (taskStatusIcon.equals("X")) TaskList.getTaskAtIndex(currIndex).markAsDone();
                } else if (taskType.equals("D")){
                    String taskDateTime = currLineList[3].substring(0,currLineList[3].length()); 
                    TaskList.addDeadline(taskDescription, taskDateTime);
                    if (taskStatusIcon.equals("X")) TaskList.getTaskAtIndex(currIndex).markAsDone();
                } else if (taskType.equals("T")){
                    TaskList.addToDo(taskDescription);
                    if (taskStatusIcon.equals("X")) TaskList.getTaskAtIndex(currIndex).markAsDone();
                }
                currIndex++;
            }
            /*
             * Close Readers
             */
            fr.close();
            br.close();
        } catch (Exception e){
            return;
        }

    }

    /*
     * Writes task information as new line in data file
     */
    public static void addTask(Task task){
        String taskType = task.getTaskType();
        try{
            FileWriter myWriter = new FileWriter("./DataDir/Data.txt", true);

            if (taskType == "T"){
                String line = "T - [ ] - " + task.getDescription() + "\n";
                myWriter.write(line);
                Lines.add(line);
            } else if (taskType == "E"){
                String line = "E - [ ] - " + task.getDescription() + " - " + task.getDateTime() + "\n";
                myWriter.write(line);
                Lines.add(line);
            } else if (taskType == "D"){
                String line = "D - [ ] - " + task.getDescription() + " - " + task.getDueDate() + "\n";
                myWriter.write(line);
                Lines.add(line);
            } 
            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            return;
        }
    }

    /*
     * Removes task from data file
     */
    public static void deleteTask(int index){
        Lines.remove(index);

        try{
            //Erase Data
            File dataFile = new File("./DataDir/Data.txt");
            FileWriter myWriter = new FileWriter(dataFile);
            myWriter.write("");
            myWriter.close();

            //Replace Data
            dataFile = new File("./DataDir/Data.txt");
            myWriter = new FileWriter(dataFile, true);
            for (int i=0; i < Lines.size(); i++){
                myWriter.write(Lines.get(i));
            }
            myWriter.close();
        } catch (IOException e){
            System.out.println("An error occurred.");
            return;
        }
    }

    /*
     * Marks a task as done in data file
     * Overwrites entire file
     */
    public static void markAsDone(int index){
        //Replace Data in Lines list
        String oldLine = Lines.get(index);
        String newLine = oldLine.substring(0,5) 
                        + "X" 
                        + oldLine.substring(6, oldLine.length());
        Lines.set(index, newLine);

        try{
            //Erase Data
            File dataFile = new File("./DataDir/Data.txt");
            FileWriter myWriter = new FileWriter(dataFile);
            myWriter.write("");
            myWriter.close();

            //Replace Data
            dataFile = new File("./DataDir/Data.txt");
            myWriter = new FileWriter(dataFile, true);
            for (int i=0; i < Lines.size(); i++){
                myWriter.write(Lines.get(i));
            }
            myWriter.close();
        } catch (IOException e){
            System.out.println("An error occurred.");
            return;
        }
    }

    /*
     * Marks a task as not done in data file
     * Overwrites entire file
     */
    public static void markAsNotDone(int index){
        //Replace Data in Lines list
        String oldLine = Lines.get(index);
        String newLine = oldLine.substring(0,5) 
                        + " " 
                        + oldLine.substring(6, oldLine.length());
        Lines.set(index, newLine);

        try{
            //Erase Data
            File dataFile = new File("./DataDir/Data.txt");
            FileWriter myWriter = new FileWriter(dataFile);
            if (!dataFile.exists()) dataFile.createNewFile();
            myWriter.write("");
            myWriter.close();

            //Replace Data
            dataFile = new File("./DataDir/Data.txt");
            myWriter = new FileWriter(dataFile, true);
            for (int i=0; i < Lines.size(); i++){
                myWriter.write(Lines.get(i));
            }
            myWriter.close();
        } catch (IOException e){
            System.out.println("An error occurred.");
            return;
        }
    }
}
