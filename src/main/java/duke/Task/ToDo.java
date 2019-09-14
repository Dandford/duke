package duke.task;

import java.time.LocalDateTime;

public class ToDo extends Task {

    private static final String TASK_TYPE = "[T]";

    /**
     * Constructor for ToDo which inherits from Task.
     *
     * @param description String that is passed from the commands containing info about the Task.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Abstract method implemented from parent Task.
     *
     * @return the symbol for type of task, "[T]".
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
        return this.description;
    }

    /**
     * toString() method.
     *
     * @return string containing information of task to be printed out by ListCommand and Ui.
     */
    @Override
    public String toString() {
        return TASK_TYPE + super.getStatusIcon() + " " +  super.toString();
    }

}
