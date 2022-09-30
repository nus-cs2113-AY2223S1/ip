package controller;

import tasks.Task;

import java.io.File;
import java.util.ArrayList;
import java.io.FileWriter;

/**
 * Represents the storage of information relating to the tasklist
 */
public class Storage {
    private String filepath;
    private String folderpath;

    public Storage(String filepath, String folderpath) {
        this.filepath = filepath;
        this.folderpath = folderpath;
    }

    /**
     * Retreives the file storing user's tasks from the previous run
     * of the program. If no such file is found, makes the appropriate file
     * 
     * @return File representing the saved information
     */
    public File getMakeFile() {
        try {
            
            File folder = new File(folderpath);
            File file = new File(filepath);
            
            if (!folder.exists() && !folder.isDirectory()) {
                // need to create folder as determined by the path string
                folder.mkdirs();
                file.createNewFile();
                return file;
            }
            else if (!file.exists() && folder.isDirectory()) {
                // need to create file as determined by the path string
                file.createNewFile();
                return file;
            }
            return file;

        } catch (Exception e) {
            System.out.println("File i/o error.");
            return null;
        }
    }

    /**
     * Writes the information in the tasks array to the file
     * in the specified location for storage
     * 
     * @param tasks the current list of tasks to be written into the file
     */
    public void updateFile(ArrayList<Task> tasks) { 
        try {
            File f = new File(this.filepath);
            if (f.exists()) {
                f.delete();
            }
            f.createNewFile();

            FileWriter fw = new FileWriter(f);
            for (int i = 0; i < tasks.size(); i++) {
                fw.write(tasks.get(i).createFileString() + "\n");
            }

            fw.close();
        } catch (Exception e) {
            System.out.println("File i/o error");
        }
    }

}
