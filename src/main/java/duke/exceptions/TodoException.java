package duke.exceptions;
/** Thrown when TodoCommand input does not follow the format:
 *      todo [description]
 */
public class TodoException extends Exception {
    @Override
    public String getMessage() {
        return "OOPS!!! Todo command must follow the format:todo <description>";
    }
}
