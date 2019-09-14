package duke.task;

import duke.helper.DateTimeHelper;
import java.time.LocalDateTime;

public class Deadline extends Task {

    private static final String TASK_TYPE = "[D]";
    protected LocalDateTime deadlineTime;

    /**
     * Constructor for Deadline which inherits from Task.
     *
     * @param description String that is passed from the commands containing info about the Deadline.
     * @param by contains information about the deadlineTime from the user.
     */
    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.deadlineTime = by;
    }

    @Override
    public void updateTime(LocalDateTime updtTime) {
        this.deadlineTime = updtTime;
    }

    /**
     * Returns a string based on the deadline descriptions and formats the LocalDateTime output.
     * Abstract method implemented from parent Task.
     *
     * @return the description of the task to be placed into Storage class.
     */
    public String getDescription() {
        return this.description + "|" + DateTimeHelper.formatOutput(this.deadlineTime);
    }

    /**
     * Returns a string, referring to the type of task. Abstract method implemented from parent Task.
     *
     * @return the symbol for type of task, "[D]".
     */
    public String getType() {
        return TASK_TYPE;
    }

    /**
     * Returns a string containing full description of the deadline.
     *
     * @return string containing information of task to be printed out by ListCommand and Ui.
     */
    @Override
    public String toString() {
        return TASK_TYPE + super.getStatusIcon() + " " + super.toString()
                + " (by: " + DateTimeHelper.formatOutput(deadlineTime)  + ")";
    }
}
