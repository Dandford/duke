package duke.core;

import duke.helper.DateTimeHelper;
import duke.helper.DukeException;
import duke.task.Task;
import duke.task.ToDo;
import duke.task.Deadline;
import duke.task.Event;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {

    //stores information of the data in the duke.txt file.
    final String fileName = "C:\\Users\\dandf\\Pictures\\CS2103-Duke\\duke/data/duke.txt";
    final String direcName = "C:\\Users\\dandf\\Pictures\\CS2103-Duke\\duke/data";

    /**
     * Writes the updated contents from TaskList.
     * First checks if the file or directory exists, if it doesn't create a new empty file.
     * public method called by Command and it's child classes each time Command.execute is run.
     *
     * @param taskEntered ArrayList of Tasks obtained from the TaskList class.
     * @throws DukeException which occurs if there is problem in loading the duke.txt.
     */
    public void writeToFile(ArrayList<Task> taskEntered) throws DukeException {
        try {
            File taskStorage = new File(fileName);
            File directoryStorage = new File(direcName);
            if (!taskStorage.getAbsoluteFile().exists()) {
                directoryStorage.mkdir();
                taskStorage.createNewFile();
            }
            FileWriter taskWrite = new FileWriter(fileName);
            String s = writeFromArray(taskEntered);
            taskWrite.write(s);
            taskWrite.close();
        } catch (IOException e) {
            throw new DukeException("OOPS!!! Error occured when loading file.");
        }
    }

    /**
     * Uses StringBuffer to append string of tasks obtained from taskEntered into format required to put into duke.txt.
     * public method called by writeToFile, which is called each time Command.execute is run.
     *
     * @param taskEntered ArrayList of Tasks obtained from the TaskList class.
     */
    public String writeFromArray(ArrayList<Task> taskEntered) {
        StringBuffer toWrite = new StringBuffer("");
        for (int i = 0; i < taskEntered.size(); i++) {
            Task t = taskEntered.get(i);
            String appendedString = t.getType() + "|" + t.getIsDone() + "|" + t.getDescription() + "\n";
            toWrite.append(appendedString);
        }
        return toWrite.toString();
    }

    /**
     * Reads & Understands the string from duke.txt and creates a arraylist of tasks based on the strings in duke.txt.
     * First checks if the file or directory exists, if it doesn't create a new empty file.
     * public method called by duke when it first starts up.
     *
     * @return ArrayList of Tasks which will be assigned to TaskList class.
     * @throws DukeException which occurs if there is problem in loading the duke.txt.
     */
    public ArrayList<Task> outputFileContents() throws DukeException {
        try {
            File taskStorage = new File(fileName);
            File directoryStorage = new File(direcName);
            //checks if the file exists
            if (!taskStorage.getAbsoluteFile().exists()) {
                directoryStorage.mkdir();
                taskStorage.createNewFile();
            }
            Scanner s = new Scanner(taskStorage); // create a Scanner using the File as the source
            ArrayList<Task> retrievedTask = new ArrayList<Task>();
            while (s.hasNext()) {
                //note that | is known as || in java
                String[] inputsplit = s.nextLine().split("\\|");
                String taskType = inputsplit[0];
                Task t;
                if (taskType.equalsIgnoreCase("[T]")) {
                    t = new ToDo(inputsplit[2]);
                } else if (taskType.equalsIgnoreCase("[D]")) {
                    LocalDateTime ldt = DateTimeHelper.formatInput(inputsplit[3]);
                    t = new Deadline(inputsplit[2], ldt);
                } else {
                    assert taskType.equalsIgnoreCase("[E]") : "Error in save file";
                    LocalDateTime ldt = DateTimeHelper.formatInput(inputsplit[3]);
                    t = new Event(inputsplit[2], ldt);
                }
                this.checkIfDone(t,inputsplit[1]);
                retrievedTask.add(t);
            }
            return retrievedTask;
        } catch (Exception e) {
            throw new DukeException("OOPS!!! I'm sorry, but file not found :-(");
        }
    }

    /**
     * Checks if the task is done by calling markIsDone();
     * Called by outputFileContents and checks the string which is retrieved from duke.txt if the task is Done.
     */
    public void checkIfDone(Task t, String booleanValue) {
        if (booleanValue.equalsIgnoreCase("True")) {
            t.markIsDone();
        }
    }
}
