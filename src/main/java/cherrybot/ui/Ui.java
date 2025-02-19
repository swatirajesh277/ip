package cherrybot.ui;

import java.util.ArrayList;
import java.util.Scanner;

import cherrybot.exception.CherryBotException;
import cherrybot.task.Task;


/**
 * This class handles all Ui related operations.
 */
public class Ui {

    /**
     * Displays a greeting message.
     */
    public static void greeting() {
        System.out.println("\t________________________________________");
        System.out.println("   ____   _   _  U _____ u   ____      ____     __   __   ____     U  ___ u _____  \n" +
                "U /\"___| |'| |'| \\| ___\"|/U |  _\"\\ uU |  _\"\\ u  \\ \\ / /U | __\")u    \\/\"_ \\/|_ \" _| \n" +
                "\\| | u  /| |_| |\\ |  _|\"   \\| |_) |/ \\| |_) |/   \\ V /  \\|  _ \\/    | | | |  | |   \n" +
                " | |/__ U|  _  |u | |___    |  _ <    |  _ <    U_|\"|_u  | |_) |.-,_| |_| | /| |\\  \n" +
                "  \\____| |_| |_|  |_____|   |_| \\_\\   |_| \\_\\     |_|    |____/  \\_)-\\___/ u |_|U  \n" +
                " _// \\\\  //   \\\\  <<   >>   //   \\\\_  //   \\\\_.-,//|(_  _|| \\\\_       \\\\   _// \\\\_ \n" +
                "(__)(__)(_\") (\"_)(__) (__) (__)  (__)(__)  (__)\\_) (__)(__) (__)     (__) (__) (__)");
        System.out.println("Hello! I'm CherryBot");
        System.out.println("What can I do for you?");
        System.out.println("\t________________________________________");
    }

    /**
     * Displays a goodbye message when the program exits.
     */
    public static void goodbye() {
        System.out.println("\tBye. See you later alligator!");
    }

    /**
     * Displays a confirmation that a task has been added to the task list.
     *
     * @param t The task that was added.
     * @param list The task list that the task has been added to.
     */
    public static void showAdd(Task t, TaskList list) {
        System.out.println("\tGot it. I've added this task:");
        System.out.println("\t\t" + t.toString());
        System.out.println("\tNow you have " + list.size() + " tasks in the list.");
    }

    /**
     * Displays a confirmation that a task has been marked as done.
     *
     * @param t The task that was marked as done.
     * @param list The task list that the task belongs to.
     */
    public static void showMarkTask(Task t, TaskList list) {
        System.out.println("\tNice! I've marked this task as done:");
        System.out.println("\t\t" + t.toString());
    }

    /**
     * Displays a confirmation that a task has been marked as not done.
     *
     * @param t The task that was marked as not done.
     * @param list The task list that the task belongs to.
     */
    public static void showUnmarkTask(Task t, TaskList list) {
        System.out.println("\tOkay, I've marked this task as not done yet:");
        System.out.println("\t\t" + t.toString());
    }

    /**
     * Displays a confirmation that a task has been deleted from the task list.
     *
     * @param t The task that was deleted.
     * @param list The task list after the task has been removed.
     */
    public static void showDeleteTask(Task t, TaskList list) {
        System.out.println("\tNoted. I've removed this task:");
        System.out.println("\t\t" + t.toString());
        System.out.println("\tNow you have " + list.size() + " tasks in the list.");
    }

    /**
     * Converts the task list into a string format suitable for saving to a file.
     *
     * @param list The task list to convert.
     * @return A string representation of the task list.
     */
    public static String listFormatForFile(TaskList list) {
        String s = "";
        for (int i = 0; i < list.size(); i++) {
            try {
                s += list.getTask(i).toString();
                s += "\n";
            } catch (CherryBotException e) {
                System.out.println("Error while retrieving task at index " + i + ": " + e.getMessage());
            }
        }

        return s;
    }

    /**
     * Displays an error message indicating that something went wrong during loading.
     */
    public static void showLoadingError() {
        System.out.println("OOPSIE DAISY!!! Looks like something went wrong...");
    }

    /**
     * Reads and returns a command entered by the user.
     *
     * @return The user input as a string.
     */

    public static String readCommand() {
        Scanner scanner = new Scanner(System.in);
        String command = scanner.nextLine();
        return command;
    }

    /**
     * Displays an error message with a specific message.
     *
     * @param message The error message to display.
     */
    public void showError(String message) {
        System.out.println("OOPSIE DAISY!!!" + message);
    }

    /**
     * Displays the list of tasks that match the search keyword.
     * If no tasks are found, it displays a message indicating no matches were found.
     *
     * @param list The list of tasks that match the search criteria.
     */
    public void showFind(ArrayList<Task> list) {
        if (list.isEmpty()) {
            System.out.println("No matching tasks found!");
        } else {
            System.out.println("Here are the matching tasks in your list: ");
            for (int i = 0; i < list.size(); i++) {
                System.out.println("\t" + (i + 1) + ". " + list.get(i).toString());
            }
        }
    }

    public void sortedTasks(ArrayList<Task> list) {
        System.out.println("Here are the tasks in your list in a sorted order according to deadlines: ");
        for (int i = 0; i < list.size(); i++) {
            System.out.println("\t" + (i + 1) + ". " + list.get(i).toString());
        }
    }
}
