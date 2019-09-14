package duke.command;

import duke.core.Ui;
import duke.core.Storage;
import duke.helper.DukeException;
import duke.helper.DateTimeHelper;
import duke.task.TaskList;
import duke.task.ToDo;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import java.time.LocalDateTime;

public class AddCommand extends Command {

    /**
     * Constructor for AddCommand which inherits from Command.
     *
     * @param inputCommand String that is parsed from the Parser.
     */
    public AddCommand(String inputCommand) {
        super(inputCommand);
    }

    /**
     * Reads and creates new ToDo, Event or Deadline based on the parsed input.
     *
     * @param tasks Array of Tasks.
     * @param ui Ui for printing output.
     * @param storage Storage to update textfile.
     * @return feedback message to update user.
     * @throws DukeException for incorrect user input.
     */
    //note private can't be accessed by child
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        String[] inputsplit = this.inputCommand.split(" ", 2);
        String typeTask = inputsplit[0].toLowerCase();
        if (inputsplit.length <= 1) {
            throw new DukeException("OOPS!!! The description of a " + typeTask + " cannot be empty.");
        }
        Task taskToAdd;
        String[] descripSplit;
        LocalDateTime ldt;
        switch (typeTask) {
        case "todo":
            taskToAdd = new ToDo(inputsplit[1]);
            break;
        case "deadline":
            descripSplit = inputsplit[1].split(" /by ", 2);
            ldt = DateTimeHelper.formatInput(descripSplit[1]);
            taskToAdd = new Deadline(descripSplit[0], ldt);
            break;
        case "event":
            descripSplit = inputsplit[1].split(" /at ", 2);
            ldt = DateTimeHelper.formatInput(descripSplit[1]);
            taskToAdd = new Event(descripSplit[0], ldt);
            break;
        default:
            throw new DukeException("OOPS!!! Please specify a valid type of task!");
        }
        tasks.addToRecord(taskToAdd);
        storage.writeToFile(tasks.getTaskList());
        return ui.printAdd(taskToAdd, tasks.getSize());
    }

}
