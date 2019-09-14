package duke.command;

import duke.core.Ui;
import duke.core.Storage;
import duke.helper.DukeException;
import duke.task.TaskList;
import duke.task.Task;
import java.util.ArrayList;

public class FindCommand extends Command {

    /**
     * Constructor for FindCommand which inherits from Command.
     *
     * @param inputCommand String that is parsed from the Parser.
     */
    public FindCommand(String inputCommand) {
        super(inputCommand);
    }

    /**
     * Reads and filters tasks based on the keyword of parsed input.
     *
     * @param tasks Array of Tasks.
     * @param ui Ui for printing output.
     * @param storage Storage to update textfile.
     * @return feedback message to update user.
     * @throws DukeException for incorrect user input.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        String[] inputsplit = this.inputCommand.split(" ");
        if (inputsplit.length <= 1) {
            throw new DukeException("OOPS!!! The description of a find Command cannot be empty.");
        } else if (inputsplit.length > 2) {
            throw new DukeException("OOPS!!! The find Command can only accept one keyword.");
        } else {
            ArrayList<Task> taskToFind = tasks.findTask(inputsplit[1]);
            return ui.printFind(taskToFind);
        }
    }

}
