package duke.core;

import duke.task.Task;
import java.util.Scanner;
import java.util.ArrayList;

public class Ui {
    Scanner scanner;

    /**
     * Constructor for User Interface Ui and creates one scanner everytime it starts up.
     */
    public Ui() {
        Scanner scanner = new Scanner(System.in);
        this.scanner = scanner;
    }

    /**
     * Prints out greeting message when Duke first starts up.
     */
    public String printHello() {
        return ("Hello! I'm Duke\nWhat can I do for you?\n");
    }

    /**
     * Reads user input. Duke class calls this method to read user's input.
     *
     * @return input which is a String that is read from the user's input which will later be passed to the Parser.
     */


    /**
     * Prints the list of tasks in TaskList. ListCommand class calls this function.
     *
     * @param textEntered is retrieved from TaskList class.
     */
    public String printRecord(ArrayList<Task> textEntered) {
        String printString = "Here are the tasks in your list: \n";
        for (int i = 0; i < textEntered.size(); i++) {
            printString = printString + ((i + 1) + "." + textEntered.get(i).toString() + "\n");
        }
        printString = printString + "\n";
        return printString;
    }

    /**
     * Prints message when Duke ends as ExitCommand is called.
     */
    public String printBye() {
        return "Bye. Hope to see you again soon!\n";
    }

    /**
     * Prints notification that the task has been added.
     */
    public String printAdd(Task t, int sizeOfTask) {
        String toPrint = ("Got it. I've added this task: \n  " + t.toString() + "\nNow you have " + sizeOfTask
                + " tasks in the list.");
        return toPrint;
    }

    /**
     * Prints notification that the task has been done.
     */
    public String printDone(Task t) {
        String toPrint = ("Nice! I've marked this task as done: \n");
        toPrint = toPrint + ("  " + t.toString() + "\n");
        return toPrint;
    }

    /**
     * Prints notification that the task has been deleted.
     */
    public String printDelete(Task t, int sizeOfTask) {
        return ("Noted. I've removed this task: \n" + "  " + t.toString() + "\n"
                + "Now you have " + sizeOfTask + " tasks in the list." + "\n");
    }

    /**
     * Prints list of tasks which has been filtered to include the keyword.
     */
    public String printFind(ArrayList<Task> inputList) {
        String toPrint = ("Here are the matching tasks in your list:\n");
        for (int i = 0; i < inputList.size(); i++) {
            toPrint = toPrint + ((i + 1) + "." + inputList.get(i).toString() + "\n");
        }
        return toPrint;
    }

    /**
     * Prints the task that has been updated.
     */
    public String printUpdate(Task t, int sizeOfTask) {
        String toPrint = ("Got it. I've updated this task: \n  " + t.toString() + "\nNow you have " + sizeOfTask
                + " tasks in the list.");
        return toPrint;
    }

}
