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
     * public method called by Duke.run() in the for loop as part of the Command Pattern.
     * Needs to be implemented as inherited from Command abstract method.
     * Uses Storage Class to write to file duke.txt, Ui class to print responses and TaskList to store the arraylist.
     *
     * @param tasks TaskList passed from the Duke main class, containing Array of Tasks.
     * @param ui Ui passed from the Duke main class, responsible for printing output to the user and obtaining input.
     * @param storage Storage passed from the Duke main class, responsible for updating duke.txt after every command.
     * @throws DukeException which can come from two sources, itself when the description passed in by the user is not
     * complete and if the values for delete is invalid or the Storage Class.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        String[] inputsplit = inputCommand.split(" ", 2);
        try {
            if (inputsplit.length <= 1) {
                throw new DukeException("OOPS!!! The description of delete must have a value.");
            } else if (Integer.parseInt(inputsplit[1]) > tasks.getSize() || Integer.parseInt(inputsplit[1]) <= 0) {
                throw new DukeException("OOPS!!! Invalid value for task delete!");
            } else {
                int num = Integer.parseInt(inputsplit[1]);
                Task t = tasks.removeTask(num);
                storage.writeToFile(tasks.getTaskList());
                return ui.printDelete(t, tasks.getSize());
            }
        } catch (NumberFormatException e) {
            throw new DukeException("OOPS!!! The description of delete must be a number.");
        }
    }
}
