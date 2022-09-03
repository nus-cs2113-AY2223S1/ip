package duke.error.exceptions;

import duke.Duke;

public class ListTooManyCommandsException extends CustomException {
    @Override
    public String getExceptionMessage() {
        return String.format("There were unrecognized arguments after "
                + "the <%1$s> command. Please try the <%1$s> command"
                + " again by itself.", Duke.COMMAND_LIST);
    }
}
