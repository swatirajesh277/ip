import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class CherryBot {
    public static void main(String[] args) {

        Scanner msg = new Scanner(System.in);
        String userInput;
        ArrayList<Task> tasks = new ArrayList<>();

        File dataDir = new File("./data");
        if (!dataDir.exists()) {
            dataDir.mkdir();
        }


        File f = new File("./data/cherrybot.txt");
        if (!f.exists()) {
            try {
                f.createNewFile();
            } catch (IOException e) {
                System.out.println("Error creating file: " + e.getMessage());
            }
        }

        try {
            Scanner fileReader = new Scanner(f);
            while (fileReader.hasNextLine()) {

                String line = fileReader.nextLine();
                Task t = stringToTask(line);
                tasks.add(t);
            }

            fileReader.close();

        } catch (FileNotFoundException e) {
            System.out.println("OOPSIE DAISY!!! An error has occurred in reading the file");
        }

        greet();

        while (true) {
            userInput = msg.nextLine();
            System.out.println("\t________________________________________");

            try {
                if (userInput.equals("bye")) {
                    System.out.println("\tBye. See you later alligator!");
                    System.out.println("\t________________________________________");

                    String file2 = "./data/cherrybot.txt";
                    try {
                        writeToFile(file2,list(userInput, tasks));
                    } catch (IOException e) {
                        System.out.println("Something went wrong: " + e.getMessage());
                    }
                    break;
                } else {
                    handleOtherThings(userInput, tasks);
                }
            } catch (CherryBotException e) {
                System.out.println("OOPSIE DAISY!!! " + e.getMessage());
            } catch (Exception e) {
                System.out.println("OOPSIE DAISY!!! Looks like something went wrong, bad luck " + e.getMessage());
            }
            System.out.println("\t________________________________________");
        }

        msg.close();
    }

    private static void greet() {
        System.out.println("\t________________________________________");
        System.out.println("Hello! I'm CherryBot");
        System.out.println("What can I do for you?");
        System.out.println("\t________________________________________");
    }

    private static void handleOtherThings(String userInput, ArrayList<Task> tasks) throws CherryBotException {
        if (userInput.equals("list")) {
            if (tasks.isEmpty()) {
                System.out.println("\tNo tasks in list");
            } else {
                System.out.println("\tHere are the tasks in your list:");
                for (int i = 0; i < tasks.size(); i++) {
                    System.out.println("\t" + (i + 1) + ". " + tasks.get(i).toString());
                }
            }

        } else if (userInput.trim().startsWith("delete")) {
            String[] splitCommand = userInput.trim().split(" ");

            if (splitCommand.length < 2) {
                throw new CherryBotException("Task number is missing");
            }

            String foo = splitCommand[1];
            int taskNumber = Integer.parseInt(foo);

            if (taskNumber > tasks.size() || taskNumber <= 0) {
                throw new CherryBotException("Task number is invalid");
            }

            Task del = tasks.remove(taskNumber - 1);
            System.out.println("\tNoted. I've removed this task:");
            System.out.println("\t\t" + del.toString());
            System.out.println("\tNow you have " + tasks.size() + " tasks in the list.");


        } else if (userInput.trim().startsWith("mark")) {
            String[] splitCommand = userInput.trim().split(" ");

            if (splitCommand.length < 2) {
                throw new CherryBotException("Task number is missing");
            }

            String foo = splitCommand[1];
            int taskNumber = Integer.parseInt(foo);

            if (taskNumber > tasks.size() || taskNumber <= 0) {
                throw new CherryBotException("Invalid task number");
            }
            tasks.get(taskNumber - 1).markAsDone();
            System.out.println("\tNice! I've marked this task as done:");
            System.out.println("\t\t" + tasks.get(taskNumber - 1).toString());

        } else if (userInput.trim().startsWith("unmark")) {
            String[] splitCommand = userInput.trim().split(" ");

            if (splitCommand.length < 2) {
                throw new CherryBotException("Task number is missing");
            }

            String foo = splitCommand[1];
            int taskNumber = Integer.parseInt(foo);

            if (taskNumber > tasks.size() || taskNumber <= 0) {
                throw new CherryBotException("Invalid task number");
            }

            tasks.get(taskNumber - 1).markAsNotDone();
            System.out.println("\tOkay, I've marked this task as not done yet:");
            System.out.println("\t\t" + tasks.get(taskNumber - 1).toString());

        } else if (userInput.trim().startsWith("todo")) {
            String description = userInput.trim().substring(5);

            if (description.isEmpty()) {
                throw new CherryBotException("Todo description cannot be empty");
            }

            tasks.add(new Todo(description));
            System.out.println("\tGot it. I've added this task:");
            System.out.println("\t\t" + tasks.get(tasks.size() - 1).toString());
            System.out.println("\tNow you have " + tasks.size() + " tasks in the list.");

        } else if (userInput.trim().startsWith("deadline")) {
            String activity = userInput.trim().substring(9);

            if (!activity.contains(" /by")) {
                throw new CherryBotException("Deadline command invalid : <description> /by <time>");
            }

            String[] splitCommand = activity.trim().split(" /by");
            String description = splitCommand[0];
            String by = splitCommand[1].trim();
            tasks.add(new Deadline(description, by));
            System.out.println("\tGot it. I've added this task:");
            System.out.println("\t\t" + tasks.get(tasks.size() - 1).toString());
            System.out.println("\tNow you have " + tasks.size() + " tasks in the list.");

        } else if (userInput.trim().startsWith("event")) {
            String activity = userInput.trim().substring(6);

            if (!activity.contains(" /from") && !activity.contains(" /to")) {
                throw new CherryBotException("Event command invalid : <description> /from <time> /to <time>");
            }

            String[] splitCommand = activity.trim().split("/");
            String description = splitCommand[0].trim();
            String start = splitCommand[1].trim().substring(5);
            String end = splitCommand[2].trim().substring(3);
            tasks.add(new Event(description, start, end));
            System.out.println("\tGot it. I've added this task:");
            System.out.println("\t\t" + tasks.get(tasks.size() - 1).toString());
            System.out.println("\tNow you have " + tasks.size() + " tasks in the list.");
        } else {
            throw new CherryBotException("Sorry I don't understand invalid command");
        }
    }

    private static Task stringToTask(String line) {
        if (line.charAt(1) == 'T') {
            String description = line.trim().substring(7);

            Todo t = new Todo(description);
            if (line.charAt(4) == 'X') {
                t.markAsDone();
            }
            return t;
        } else if (line.charAt(1) == 'D') {
            String[] splitCommand = line.trim().split(" \\(by: ");
            String by = splitCommand[1].split("\\)")[0].strip();
            String description = splitCommand[0].substring(7);

            Deadline d =  new Deadline(description, by);
            if (line.charAt(4) == 'X') {
                d.markAsDone();
            }
            return d;
        } else {
            String[] splitCommand1 = line.trim().split(" \\(from: ");
            String[] splitCommand2 = splitCommand1[1].trim().split("to: ");
            String description = splitCommand1[0].trim().substring(7);
            String start = splitCommand2[0].trim();
            String[] end = splitCommand2[1].trim().split("\\)");
            Event e = new Event(description, start, end[0]);
            if (line.charAt(4) == 'X') {
                e.markAsDone();
            }
            return e;
        }
    }

    private static void writeToFile(String filepath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filepath);
        fw.write(textToAdd);
        fw.close();
    }

    private static String list(String userInput, ArrayList<Task> tasks) {
        String s = "";
        for (int i = 0; i < tasks.size(); i++) {
            s += tasks.get(i).toString();
            s += "\n";
        }

        return s;
    }
}
