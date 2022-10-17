package duke.command;

import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

public class HelpCommand extends Command {
    public static final String COMMAND = "help";
    public static final String MESSAGE_HELP = "help\t - provide a quick user guide";
    public static final String MESSAGE_DUKE_DESCRIPTION = System.lineSeparator()
            + "Duke - your CLI based Personal Assistant Task Manager" + System.lineSeparator();
    public static final String MESSAGE_AUTHOR = System.lineSeparator()
            + "Full Documentation at https://github.com/CheahHaoYi/ip" + System.lineSeparator();

    public HelpCommand() {

    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.addLine(MESSAGE_DUKE_DESCRIPTION);
        ui.addLine(AddCommand.getHelpMessage());
        ui.addLine(DeleteCommand.getHelpMessage());
        ui.addLine(ExitCommand.getHelpMessage());
        ui.addLine(FindCommand.getHelpMessage());
        ui.addLine(HelpCommand.getHelpMessage());
        ui.addLine(ListCommand.getHelpMessage());
        ui.addLine(MarkCommand.getHelpMessage());
        ui.addLine(UnmarkCommand.getHelpMessage());
        ui.addLine(MESSAGE_AUTHOR);

        ui.printUi();
    }

    @Override
    public boolean isExit() {
        return false;
    }

    public static String getHelpMessage() {
        return MESSAGE_HELP;
    }


}
