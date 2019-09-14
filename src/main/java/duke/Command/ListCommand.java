package duke.command;

import duke.core.Ui;
import duke.core.Storage;
import duke.helper.DukeException;
import duke.task.TaskList;

public class ListCommand extends Command {

    /**
     * Constructor for ListCommand which inherits from Command.
     *
     * @param inputCommand String that is parsed from the Parser.
     */
    public ListCommand(String inputCommand) {
        super(inputCommand);
    }

    /**
     * Prints the list of tasks which is obtained from TaskList tasks.
     *
     * @param tasks TaskList passed from the Duke main class, containing Array of Tasks.
     * @param ui Ui passed from the Duke main class, responsible for printing output to the user and obtaining input.
     * @param storage Storage passed from the Duke main class, responsible for updating duke.txt after every command.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        return ui.printRecord(tasks.getTaskList());
    }
}
