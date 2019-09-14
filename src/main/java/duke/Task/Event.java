package duke.task;

import duke.helper.DateTimeHelper;
import java.time.LocalDateTime;

public class Event extends Task {

    private static final String TASK_TYPE = "[E]";
    protected LocalDateTime eventTime;

    /**
     * Constructor for Event which inherits from Task.
     *
     * @param description String that is passed from the commands containing info about the Task.
     * @param eventTime contains information about the eventTime from the user.
     */
    public Event(String description, LocalDateTime eventTime) {
        super(description);
        this.eventTime = eventTime;
    }

    @Override
    public void updateTime(LocalDateTime updtTime) {
        this.eventTime = updtTime;
    }

    /**
     * Abstract method implemented from parent Task.
     *
     * @return the symbol for type of task, "[E]".
     */
    public String getType() {
        return TASK_TYPE;
    }

    /**
     * Abstract method implemented from parent Task.
     *
     * @return the description of the task to be placed into Storage class.
     */
    public String getDescription() {
        return this.description + "|" + DateTimeHelper.formatOutput(this.eventTime);
    }

    /**
     * toString() method.
     *
     * @return string containing information of task to be printed out by ListCommand and Ui.
     */
    @Override
    public String toString() {
        return TASK_TYPE + super.getStatusIcon() + " " + super.toString() + " (at: "
                + DateTimeHelper.formatOutput(eventTime) + ")";
    }
}
