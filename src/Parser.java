public class Parser extends Constants{
    public Task taskFromFile(String task) throws Error {
        String[] reconstruct = task.split(" \\| ");
        switch (reconstruct[0]) {
            case "T":
                return new Todo(reconstruct[2], reconstruct[1].equals("X"));
            case "E":
                return new Event(reconstruct[2], reconstruct[3], reconstruct[1].equals("X"));
            case "D":
                return new Deadline(reconstruct[2], reconstruct[3], reconstruct[1].equals("X"));
            default:
                throw new Error(FILE_TASK_CONVERSION_ERROR);
        }
    }

    public Task task(String taskType, String details) throws Error {
        String[] separateDetails;
        String description;
        String time;
        switch (taskType) {
            case "todo":
                return new Todo(details, false);

            case "deadline":
                separateDetails = details.split(" /by ", 2);
                if (separateDetails.length != 2) {
                    throw new Error(DEADLINE_FORMAT_ERROR);
                }
                else if (separateDetails[0].equals("")) {
                    throw new Error(DEADLINE_DESCRIPTION_ERROR);
                }
                description = separateDetails[0];
                time = "(by:" + separateDetails[1] + ")";
                return new Deadline(description, time, false);

            case "event":
                separateDetails = details.split(" /at ", 2);
                if (separateDetails.length != 2) {
                    throw new Error(EVENT_FORMAT_ERROR);
                }
                else if (separateDetails[0].equals("")) {
                    throw new Error(EVENT_DESCRIPTION_ERROR);
                }
                description = separateDetails[0];
                time = "(at:" + separateDetails[1] + ")";
                return new Event(description, time, false);
            default:
                throw new Error(INVALID_COMMAND);
        }
    }

}
