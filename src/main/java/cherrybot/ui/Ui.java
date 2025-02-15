package cherrybot.ui;

import java.util.Scanner;

import cherrybot.exception.CherryBotException;
import cherrybot.task.Task;

public class Ui {

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

    public static void goodbye() {
        System.out.println("\tBye. See you later alligator!");
        System.out.println("\t________________________________________");
    }

    public static void showLine() {
        System.out.println("\t________________________________________");
    }

    public static void showAdd(Task t, TaskList list) {
        System.out.println("\tGot it. I've added this task:");
        System.out.println("\t\t" + t.toString());
        System.out.println("\tNow you have " + list.size() + " tasks in the list.");
    }

    public static void showMarkTask(Task t, TaskList list) {
        System.out.println("\tNice! I've marked this task as done:");
        System.out.println("\t\t" + t.toString());
    }

    public static void showUnmarkTask(Task t, TaskList list) {
        System.out.println("\tOkay, I've marked this task as not done yet:");
        System.out.println("\t\t" + t.toString());
    }

    public static void showDeleteTask(Task t, TaskList list) {
        System.out.println("\tNoted. I've removed this task:");
        System.out.println("\t\t" + t.toString());
        System.out.println("\tNow you have " + list.size() + " tasks in the list.");
    }

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

    public static void showLoadingError() {
        System.out.println("OOPSIE DAISY!!! Looks like something went wrong...");
    }

    public static String readCommand() {
        Scanner scanner = new Scanner(System.in);
        String command = scanner.nextLine();
        return command;
    }

    public void showError(String message) {
        System.out.println("OOPSIE DAISY!!!" + message);
    }
}
