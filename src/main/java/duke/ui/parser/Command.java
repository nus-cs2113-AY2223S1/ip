package duke.ui.parser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

import duke.exception.*;

public abstract class Command {
    public enum CommandType {
        EXIT, LIST, MARK, UNMARK, DEADLINE, EVENT, TODO, DELETE, FIND
    }

    protected CommandType commandType;
    protected Integer minArguments = null;
    protected Integer maxArguments = null;
    protected ArrayList<String> flags;
    protected String rawArguments;
    protected ArrayList<String> splitArguments;


    protected void checkArgumentLength() throws DukeMissingArgumentException, DukeExtraArgumentException,
            DukeMissingDescriptionException {

        if (maxArguments != null && splitArguments.size() > maxArguments) {
            throw new DukeExtraArgumentException(rawArguments);
        }

        if (minArguments != null && splitArguments.size() < minArguments) {
            throw new DukeMissingArgumentException(rawArguments);
        }

        if (flags.size() > 0 && rawArguments.indexOf(flags.get(0)) == 0) {
            throw new DukeMissingDescriptionException();
        }
    }


    private void checkFlags() throws DukeMissingFlagException, DukeMissingArgumentException {
        for (int i = 0; i < flags.size(); i++) {
            String flag = flags.get(i);

            int indexOfFlag = rawArguments.indexOf(flag);
            if (indexOfFlag == -1) {
                throw new DukeMissingFlagException();
            }

            if (i + 1 >= flags.size()) {
                break;
            }

            int indexOfNextFlag = rawArguments.indexOf(flags.get(i + 1));

            if ((indexOfFlag + flag.length() >= rawArguments.length()) ||
                indexOfFlag + flag.length() >= indexOfNextFlag) {
                throw new DukeMissingArgumentException(rawArguments);
            }
        }
    }

    protected ArrayList<String> splitArguments(String rawArguments) {
        //@@Author CodeVsColor
        //Reused from https://www.codevscolor.com/java-remove-empty-values-while-split
        //with minor modifications
        return Arrays.stream(rawArguments.split(" "))
                .filter(e -> e.trim().length() > 0)
                .collect(Collectors.toCollection(ArrayList::new));

        //@@author
    }

    protected abstract void checkArgumentType() throws DukeNotIntegerException;

    protected abstract void parse() throws DukeDateTimeFormatException;

    public CommandType getCommandType() {
        return commandType;
    }


    public void verifyAndParse() throws Exception {
        checkArgumentLength();
        checkFlags();
        checkArgumentType();
        parse();
    }

}
