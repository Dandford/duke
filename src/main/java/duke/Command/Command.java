package duke.command;

import duke.core.Ui;
import duke.core.Storage;
import duke.helper.DukeException;
import duke.task.TaskList;

public abstract class Command {
    protected String inputCommand;
    protected boolean shouldExit;

    /**
     * Constructor for Command which will be inherited by all other commands.
     * shouldExit is the boolean that will stop the for loop and exit the program, by default is false.
     *
     * @param inputCommand String that is parsed from the Parser.
     */
    public Command(String inputCommand) {
        this.inputCommand = inputCommand;
        this.shouldExit = false;
    }

    /**
     * Checks if the for loop should continue.
     *
     * @return true which will stop the for loop and exit the program.
     */
    public boolean shouldExit() {
        return this.shouldExit;
    }

   /**
    * Executes the commands on Duke.
    *
    * @return feedback message to update user.
    */
    public abstract String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;

}
