package duke.task;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> taskArray;

    public TaskList(ArrayList<Task> taskArray) {
        this.taskArray = taskArray;
    }

    public TaskList() {
        this.taskArray = new ArrayList<Task>();
    }

    public int getSize() {
        return taskArray.size();
    }

    public ArrayList<Task> getTaskList() {
        return this.taskArray;
    }

    public Task getTask(int num) {
        return taskArray.get(num - 1);
    }

    public void addToRecord(Task t) {
        this.taskArray.add(t);
    }

    public Task removeTask(int num) {
        return this.taskArray.remove(num - 1);
    }


}
