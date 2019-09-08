package duke;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.layout.Region;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import duke.core.Storage;
import duke.core.Ui;
import duke.core.Parser;
import duke.command.Command;
import duke.helper.DukeException;
import duke.task.TaskList;
import duke.task.Task;
import java.util.ArrayList;

public class Duke {
    private ArrayList<Task> textEntered;
    private Storage storage;
    private TaskList tasks;
    private Ui userIF;

    /**
     * Constructor for Duke which is called in main. Creates a new Ui and Storage. If Storage can't find the file
     * duke.txt it will throw an exception, in which case a new TaskList will be created that is empty.
     */
    public Duke() {
        this.userIF = new Ui();
        this.storage = new Storage();
        try {
            tasks = new TaskList(storage.outputFileContents());
        } catch (DukeException e) {
            e.getMessage();
            tasks = new TaskList();
        }
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            return c.execute(tasks, userIF, storage);
        } catch (DukeException e) {
            return e.getMessage();
        }
    }

    public String sayHello() {
        return userIF.printHello();
    }

}
