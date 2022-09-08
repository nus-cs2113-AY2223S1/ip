package duke.command;

import java.util.Arrays;
import duke.exception.*;

public abstract class Command {
    public enum CommandType {
        EXIT, LIST, MARK, UNMARK, DEADLINE, EVENT, TODO, DELETE
    }

    protected CommandType commandType;
    protected Integer MIN_ARGUMENTS = null;
    protected Integer MAX_ARGUMENTS = null;
    protected String[] FLAGS;
    protected String rawArguments;
    protected String[] splitArguments;


    protected void checkArgumentLength() throws MissingArgumentException, ExtraArgumentException,
            MissingDescriptionException {

        if (MAX_ARGUMENTS != null && splitArguments.length > MAX_ARGUMENTS) {
            throw new ExtraArgumentException();
        }

        if (MIN_ARGUMENTS != null && splitArguments.length < MIN_ARGUMENTS) {
            throw new MissingArgumentException();
        }
    }


    private void checkFlags() throws MissingFlagException, MissingArgumentException {
        for (int i = 0; i < FLAGS.length; i++) {
            String flag = FLAGS[i];

            int indexOfFlag = rawArguments.indexOf(flag);
            if (indexOfFlag == -1) {
                throw new MissingFlagException();
            }

            if (i + 1 >= FLAGS.length) {
                break;
            }

            int indexOfNextFlag = rawArguments.indexOf(FLAGS[i + 1]);

            if ((indexOfFlag + flag.length() >= rawArguments.length()) ||
                indexOfFlag + flag.length() >= indexOfNextFlag) {
                throw new MissingArgumentException();
            }
        }
    }

    protected String[] splitArguments(String rawArguments) {
        //@@Author CodeVsColor
        //Reused from https://www.codevscolor.com/java-remove-empty-values-while-split
        //with minor modifications
        return Arrays.stream(rawArguments.split(" ")).filter(e -> e.trim().length() > 0).toArray(String[]::new);
        //@@author
    }

    protected abstract void checkArgument() throws NotIntegerException;

    protected abstract void parse();

    public CommandType getCommandType() {
        return commandType;
    }


    public void verifyAndParse() throws Exception {
        checkArgumentLength();
        checkFlags();
        checkArgument();
        parse();
    }

}
