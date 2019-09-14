package duke.command;

import duke.core.Ui;
import duke.core.Storage;
import duke.helper.DukeException;
import duke.task.Task;
import duke.task.TaskList;

public class DeleteCommand extends Command {

    /**
     * Constructor for DeleteCommand which inherits from Command.
     *
     * @param inputCommand String that is parsed from the Parser.
     */
    public DeleteCommand(String inputCommand) {
        super(inputCommand);
    }

    /**
     * Deletes the aforementioned task from TaskList based on the number parsed from inputcommand.
     *
     * @param tasks Array of Tasks.
     * @param ui Ui for printing output.
     * @param storage Storage to update textfile.
     * @return feedback message to update user.
     * @throws DukeException for incorrect user input.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        String[] inputsplit = inputCommand.split(" ", 2);
        try {
            if (inputsplit.length <= 1) {
                throw new DukeException("OOPS!!! The description of delete must have a value.");
            }
            int taskNumber = Integer.parseInt(inputsplit[1]);
            if (taskNumber > tasks.getSize() || taskNumber <= 0) {
                throw new DukeException("OOPS!!! Invalid value for task delete!");
            }
            Task t = tasks.removeTask(taskNumber);
            storage.writeToFile(tasks.getTaskList());
            return ui.printDelete(t, tasks.getSize());
        } catch (NumberFormatException e) {
            throw new DukeException("OOPS!!! The description of delete must be a number.");
        }
    }
}
