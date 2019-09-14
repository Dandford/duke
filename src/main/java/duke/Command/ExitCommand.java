package duke.command;

import duke.core.Ui;
import duke.core.Storage;
import duke.helper.DukeException;
import duke.task.TaskList;

public class ExitCommand extends Command {

    /**
     * Constructor for ExitCommand which inherits from Command.
     *
     * @param inputCommand String that is parsed from the Parser.
     */
    public ExitCommand(String inputCommand) {
        super(inputCommand);
    }

    /**
     * Assigns the boolean shouldExit as true, thus terminating the for loop and closing Duke.
     *
     * @param tasks Array of Tasks.
     * @param ui Ui for printing output.
     * @param storage Storage to update textfile.
     * @return feedback message to update user.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        this.shouldExit = true;
        return ui.printBye();
    }
}
