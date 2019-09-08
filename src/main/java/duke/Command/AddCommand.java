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
     * public method called by Duke.run() in the for loop as part of the Command Pattern.
     * Needs to be implemented as inherited from Command abstract method.
     * Uses Storage Class to write to file duke.txt, Ui class to print responses and TaskList to store the arraylist.
     *
     * @param tasks TaskList passed from the Duke main class, containing Array of Tasks.
     * @param ui Ui passed from the Duke main class, responsible for printing output to the user and obtaining input.
     * @param storage Storage passed from the Duke main class, responsible for updating duke.txt after every command.
     * @throws DukeException which can come from two sources, itself if the description of the Task is empty or
     * from the Storage Class.
     */
    //note private can't be accessed by child
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        String[] inputsplit = this.inputCommand.split(" ", 2);
        String typeTask = inputsplit[0];
        if (inputsplit.length <= 1) {
            throw new DukeException("OOPS!!! The description of a " + typeTask + " cannot be empty.");
        }
        Task taskToAdd;
        if (typeTask.equalsIgnoreCase("todo")) {
            taskToAdd = new ToDo(inputsplit[1]);
        } else if (typeTask.equalsIgnoreCase("deadline")) {
            String[] descripSplit = inputsplit[1].split(" /by ", 2);
            LocalDateTime ldt = DateTimeHelper.formatInput(descripSplit[1]);
            taskToAdd = new Deadline(descripSplit[0],ldt);
        } else {
            assert typeTask.equalsIgnoreCase("event");
            String[] descripSplit = inputsplit[1].split(" /at ", 2);
            LocalDateTime ldt = DateTimeHelper.formatInput(descripSplit[1]);
            taskToAdd = new Event(descripSplit[0], ldt);
        }
        tasks.addToRecord(taskToAdd);
        storage.writeToFile(tasks.getTaskList());
        return ui.printAdd(taskToAdd, tasks.getSize());
    }

}
