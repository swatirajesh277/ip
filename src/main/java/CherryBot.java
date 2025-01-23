import java.util.Scanner;
public class CherryBot {
    public static void main(String[] args) {
        System.out.println("\t________________________________________");
        System.out.println("Hello! I'm CherryBot");
        System.out.println("What can I do for you?");
        System.out.println("\t________________________________________");

        Scanner msg = new Scanner(System.in);
        String userInput;
        String[] li = new String[100];

        while(true) {
            userInput = msg.nextLine();

            if (userInput.equals("bye")) {
                System.out.println("\t________________________________________");
                System.out.println("\tBye. See you later alligator!");
                System.out.println("\t________________________________________");
                break;
            } else {
                System.out.println("\t________________________________________");
                System.out.println("\t" + "added: " + userInput);
                System.out.println("\t________________________________________");
            }
        }
        msg.close();

    }
}
