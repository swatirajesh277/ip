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

            if (userInput.equals("bye")) {
                System.out.println("\tBye. See you later alligator!");
                System.out.println("\t________________________________________");
                break;

            } else if (userInput.equals("list")) {
                 if (itemsCount == 0) {
                     System.out.println("\tNo tasks in list");
                 } else {
                     System.out.println("\tHere are the tasks in your list: ");
                     for (int i = 0; i < itemsCount; i++) {
                         System.out.println("\t" + (i+1) + ". " + tasks[i].toString());
                     }
                 }

            } else if (userInput.trim().startsWith("mark")) {
                String[] splitCommand = userInput.trim().split(" ");
                String foo = splitCommand[1];
                int taskNumber = Integer.parseInt(foo);
                tasks[taskNumber - 1].markAsDone();
                System.out.println("\tNice! I've marked this task as done: ");
                System.out.println("\t\t" + tasks[taskNumber - 1].toString());
                
            } else if (userInput.trim().startsWith("unmark")) {
                String[] splitCommand = userInput.trim().split(" ");
                String foo = splitCommand[1];
                int taskNumber = Integer.parseInt(foo);
                tasks[taskNumber - 1].markAsNotDone();
                System.out.println("\tOkay, I've marked this task as not done yet: ");
                System.out.println("\t\t" + tasks[taskNumber - 1].toString());

            } else {
                tasks[itemsCount] = new Task(userInput);
                itemsCount++;
                System.out.println("\t" + "added: " + userInput);
            }

            System.out.println("\t________________________________________");
        }

        msg.close();

    }
}
