package duke.task;

import java.time.LocalDateTime;

public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructor for Task which will be inherited by Deadline Event and ToDo. Default boolean for isDone is false.
     *
     * @param description String that is passed from the commands containing info about the Task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Called to obtain information if the task is completed.
     *
     * @return boolean of whether task is completed.
     */
    public boolean getIsDone() {
        return this.isDone;
    }

    /**
     * Called to change information if the task is completed. Modifies the isDone boolean value to true.
     */
    public void markIsDone() {
        this.isDone = true;
    }

    public void updateDescrip(String text) {
        this.description = text;
    }

    /**
     * Called to provide tick or cross symbols to be printed out.
     *
     * @return String of tick or cross symbols.
     */
    public String getStatusIcon() {
        return (isDone ? "[" + "\u2713" + "]" : "[" + "\u2718" + "]"); //return tick or X symbols
    }

    public void updateTime(LocalDateTime updtTime) {
        return;
    }

    /**
     * Called to provide description of event.
     *
     * @return description of event.
     */
    public String toString() {
        return this.description;
    }

    public abstract String getType();

    public abstract String getDescription();

//    public abstract void updateTime(LocalDateTime ldt);

}
