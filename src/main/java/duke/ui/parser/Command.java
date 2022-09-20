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
    protected ArrayList<String> FLAGS;
    protected String rawArguments;
    protected ArrayList<String> splitArguments;


    protected void checkArgumentLength() throws MissingArgumentException, ExtraArgumentException,
            MissingDescriptionException {

        if (maxArguments != null && splitArguments.size() > maxArguments) {
            throw new ExtraArgumentException();
        }

        if (minArguments != null && splitArguments.size() < minArguments) {
            throw new MissingArgumentException();
        }
    }


    private void checkFlags() throws MissingFlagException, MissingArgumentException {
        for (int i = 0; i < FLAGS.size(); i++) {
            String flag = FLAGS.get(i);

            int indexOfFlag = rawArguments.indexOf(flag);
            if (indexOfFlag == -1) {
                throw new MissingFlagException();
            }

            if (i + 1 >= FLAGS.size()) {
                break;
            }

            int indexOfNextFlag = rawArguments.indexOf(FLAGS.get(i + 1));

            if ((indexOfFlag + flag.length() >= rawArguments.length()) ||
                indexOfFlag + flag.length() >= indexOfNextFlag) {
                throw new MissingArgumentException();
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

    protected abstract void checkArgument() throws NotIntegerException;

    protected abstract void parse() throws DukeDateTimeFormatException;

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
