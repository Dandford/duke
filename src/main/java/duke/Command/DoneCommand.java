package duke.command;

import duke.core.Ui;
import duke.core.Storage;
import duke.helper.DukeException;
import duke.task.TaskList;
import duke.task.Task;

import java.lang.reflect.InvocationTargetException;

public class DoneCommand extends Command {

    /**
     * Constructor for DoneCommand which inherits from Command.
     *
     * @param inputCommand String that is parsed from the Parser.
     */
    public DoneCommand(String inputCommand) {
        super(inputCommand);
    }

    /**
     * Marks as done the aforementioned task from TaskList based on the number parsed from inputcommand.
     *
     * @param tasks Array of Tasks.
     * @param ui Ui for printing output.
     * @param storage Storage to update textfile.
     * @return feedback message to update user.
     * @throws DukeException for incorrect user input.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            String[] inputsplit = this.inputCommand.split(" ", 2);
            if (inputsplit.length <= 1) {
                throw new DukeException("OOPS!!! The description of done must have a value.");
            }
            int taskNumber = Integer.parseInt(inputsplit[1]);
            if (taskNumber > tasks.getSize() || taskNumber <= 0) {
                throw new DukeException("OOPS!!! Invalid value for task done!");
            }
            if (tasks.getTask(taskNumber).getIsDone() == true) {
                throw new DukeException("OOPS!!! Task is already done!");
            }
            Task t = tasks.getTask(taskNumber);
            t.markIsDone();
            storage.writeToFile(tasks.getTaskList());
            return ui.printDone(t);
        } catch (NumberFormatException e) {
            throw new DukeException("OOPS!!! The description of done must be a number.");
        }
    }

}
