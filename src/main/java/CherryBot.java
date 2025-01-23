import java.util.Scanner;

public class CherryBot {
    public static void main(String[] args) {
        System.out.println("\t________________________________________");
        System.out.println("Hello! I'm CherryBot");
        System.out.println("What can I do for you?");
        System.out.println("\t________________________________________");

        Scanner msg = new Scanner(System.in);
        String userInput;
        Task[] tasks = new Task[100];
        int itemsCount = 0;

        while(true) {
            userInput = msg.nextLine();
            System.out.println("\t________________________________________");

            try {
                if (userInput.equals("bye")) {
                    System.out.println("\tBye. See you later alligator!");
                    System.out.println("\t________________________________________");
                    break;

                } else if (userInput.equals("list")) {
                    if (itemsCount == 0) {
                        System.out.println("\tNo tasks in list");
                    } else {
                        System.out.println("\tHere are the tasks in your list:");
                        for (int i = 0; i < itemsCount; i++) {
                            System.out.println("\t" + (i+1) + ". " + tasks[i].toString());
                        }
                    }

                } else if (userInput.trim().startsWith("mark")) {
                    String[] splitCommand = userInput.trim().split(" ");

                    if (splitCommand.length < 2) {
                        throw new CherryBotException("Task number is missing");
                    }

                    String foo = splitCommand[1];
                    int taskNumber = Integer.parseInt(foo);

                    if (taskNumber > itemsCount || taskNumber <= 0) {
                        throw new CherryBotException("Invalid task number");
                    }
                    tasks[taskNumber - 1].markAsDone();
                    System.out.println("\tNice! I've marked this task as done:");
                    System.out.println("\t\t" + tasks[taskNumber - 1].toString());

                } else if (userInput.trim().startsWith("unmark")) {
                    String[] splitCommand = userInput.trim().split(" ");

                    if (splitCommand.length < 2) {
                        throw new CherryBotException("Task number is missing");
                    }

                    String foo = splitCommand[1];
                    int taskNumber = Integer.parseInt(foo);

                    if (taskNumber > itemsCount || taskNumber <= 0) {
                        throw new CherryBotException("Invalid task number");
                    }
                    
                    tasks[taskNumber - 1].markAsNotDone();
                    System.out.println("\tOkay, I've marked this task as not done yet:");
                    System.out.println("\t\t" + tasks[taskNumber - 1].toString());

                } else if (userInput.trim().startsWith("todo")) {
                    String description = userInput.trim().substring(5);

                    if (description.isEmpty()) {
                        throw new CherryBotException("Todo description cannot be empty");
                    }
                    
                    tasks[itemsCount] = new Todo(description);
                    itemsCount++;
                    System.out.println("\tGot it. I've added this task:");
                    System.out.println("\t\t" + tasks[itemsCount - 1].toString());
                    System.out.println("\tNow you have " + itemsCount + " tasks in the list.");

                } else if (userInput.trim().startsWith("deadline")) {
                    String activity = userInput.trim().substring(9);

                    if (!activity.contains(" /by")) {
                        throw new CherryBotException("Deadline command invalid : <description> /by <time>");
                    }

                    String[] splitCommand = activity.trim().split(" /by");
                    String description = splitCommand[0];
                    String by = splitCommand[1].trim();
                    tasks[itemsCount] = new Deadline(description, by);
                    itemsCount++;
                    System.out.println("\tGot it. I've added this task:");
                    System.out.println("\t\t" + tasks[itemsCount - 1].toString());
                    System.out.println("\tNow you have " + itemsCount + " tasks in the list.");

                } else if (userInput.trim().startsWith("event")) {
                    String activity = userInput.trim().substring(6);

                    if (!activity.contains(" /from") && !activity.contains(" /to")) {
                        throw new CherryBotException("Event command invalid : <description> /from <time> /to <time>");
                    }
                    
                    String[] splitCommand = activity.trim().split("/");
                    String description = splitCommand[0].trim();
                    String start = splitCommand[1].trim().substring(5);
                    String end = splitCommand[2].trim().substring(3);
                    tasks[itemsCount] = new Event(description, start, end);
                    itemsCount++;
                    System.out.println("\tGot it. I've added this task:");
                    System.out.println("\t\t" + tasks[itemsCount - 1].toString());
                    System.out.println("\tNow you have " + itemsCount + " tasks in the list.");
                } else {
                    throw new CherryBotException("Sorry I don't understand invalid command");
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
}
