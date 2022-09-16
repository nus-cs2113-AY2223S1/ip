package duke.datafile;

import java.io.File;
import java.util.ArrayList;
import java.io.IOException;

public abstract class DataFile {
    protected String dirPath;
    protected String newDirName;
    protected File dataFile;
    protected File dataDir;

    public DataFile() {
        this.dirPath = "data/duke.txt";
        this.newDirName = "data";
        this.dataFile = new File(dirPath);
        this.dataDir = new File(newDirName);
    }

    /**
     * Gets the data file if it does not exist or inform the User that it already exist.
     *
     * @return dirAndFileStatus that is a string to inform the user if the data dir or file exist or not.
     */
    public String getDataFileStatus() {
        String dirAndFileStatus;
        boolean isResult = dataDir.mkdir();
        boolean isNotDataDir = !dataDir.exists() && isResult;
        if (isNotDataDir) {
            dirAndFileStatus = createDir();
        } else {
            dirAndFileStatus = createDataFile();
        }
        return dirAndFileStatus;
    }

    private String createDir() {
        String dirAndFileStatus;
        dirAndFileStatus =  "\t Data folder to store the data file does not exist "
                + "but I have made one for you.\n";
        dirAndFileStatus = dirAndFileStatus + createDataFile();
        return dirAndFileStatus;
    }

    /**
     * Creates a data file if it is not present within the hard disk.
     *
     * @return fileStatus which to inform the User that the data is present or not.
     */
    public String createDataFile() {
        String fileStatus;
        try {
            fileStatus = getFileStatus();
        } catch (IOException e) {
            fileStatus = "\t Error is trying to locate or make data file.";
        }
        return fileStatus;
    }

    private String getFileStatus() throws IOException {
        String fileStatus;
        boolean isFileCreated = dataFile.createNewFile();
        if (isFileCreated) {
            fileStatus = "\t Data file was not found but I have made one for you.";
        } else {
            fileStatus = "\t Data file already exist in your hard disk, you are ready to go!";
        }
        return fileStatus;
    }

    public abstract void writeToFile(String taskDetail) throws IOException;
    public abstract void saveToFile(String taskDetail);
    public abstract ArrayList<String> storeDataFileContents();

}
