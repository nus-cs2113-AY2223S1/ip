import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class FileHandler {

    public static ArrayList<String> Lines = new ArrayList<String>();

    public static void initFiles(){
        File dataDir = new File("./DataDir");
        if (!dataDir.exists()) dataDir.mkdir();

        try{
            File dataFile = new File("./DataDir/Data.txt");
            FileWriter myWriter = new FileWriter(dataFile);
            if (!dataFile.exists()) dataFile.createNewFile();
            myWriter.write("");
            myWriter.close();
        } catch (Exception e){
            System.out.println("Failed to create file!");
            return;
        }
    }

    public static void addTask(Task task){
        String taskType = task.taskType;
        try{
            FileWriter myWriter = new FileWriter("./DataDir/Data.txt", true);

            if (taskType == "T"){
                String line = "T | [ ] | " + task.description + "\n";
                myWriter.write(line);
                Lines.add(line);
            } else if (taskType == "E"){
                String line = "E | [ ] | " + task.description + " | " + task.getDateTime() + "\n";
                myWriter.write(line);
                Lines.add(line);
            } else if (taskType == "D"){
                String line = "D | [ ] | " + task.description + " | " + task.getDueDate() + "\n";
                myWriter.write(line);
                Lines.add(line);
            } 

            myWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            return;
        }
    }

    public static void markAsDone(int index){
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

    public static void markAsNotDone(int index){
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
