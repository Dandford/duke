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

public class UpdateCommand extends Command {

    public UpdateCommand(String inputCommand) {
        super(inputCommand);
    }

    /**
     * Reads and updates description of time of ToDo, Event or Deadline based on the parsed input.
     *
     * @param tasks Array of Tasks.
     * @param ui Ui for printing output.
     * @param storage Storage to update textfile.
     * @return feedback message to update user.
     * @throws DukeException for incorrect user input
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            String[] inputsplit = this.inputCommand.split(" ", 3);
            if (inputsplit.length <= 2 || inputsplit[2].split(" ").length <= 1) {
                throw new DukeException("OOPS!!! The description of update is not complete.");
            }
            int taskNumber = Integer.parseInt(inputsplit[1]);
            if (taskNumber > tasks.getSize() || taskNumber <= 0) {
                throw new DukeException("OOPS!!! Invalid value for task update!");
            }
            Task taskToUpdate = tasks.getTask(taskNumber);
            updateTask(inputsplit[2], taskToUpdate);
            storage.writeToFile(tasks.getTaskList());
            return ui.printUpdate(taskToUpdate, tasks.getSize());
        } catch (NumberFormatException e) {
            throw new DukeException("OOPS!!! The description of update must have a number for the task to update.");
        }
    }

    /**
     * Updates description or time of the task.
     *
     * @param inputTask Details of update.
     * @param taskToUpdate Task to be updated.
     * @throws DukeException for incorrect user input
     */
    public void updateTask(String inputTask, Task taskToUpdate) throws DukeException {
        String[] updateDetails = inputTask.split(" ",2);
        String updateType = updateDetails[0].toLowerCase();
        switch (updateType) {
        case "descrip":
            taskToUpdate.updateDescrip(updateDetails[1]);
            break;
        case "time":
            if (taskToUpdate.getType().equalsIgnoreCase("[T]")) {
                throw new DukeException("ToDos do not have time!");
            }
            LocalDateTime ldt = DateTimeHelper.formatInput(updateDetails[1]);
            taskToUpdate.updateTime(ldt);
            break;
        default:
            throw new DukeException("OOPS!!! Please specify if you want to modify the description or time");
            break;
        }
    }
}
