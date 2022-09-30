package controller;

import tasks.Task;

import java.io.File;
import java.util.ArrayList;
import java.io.FileWriter;

public class Storage {
    private String filepath;
    private String folderpath;

    public Storage(String filepath, String folderpath) {
        this.filepath = filepath;
        this.folderpath = folderpath;
    }

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
