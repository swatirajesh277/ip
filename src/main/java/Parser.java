import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Parser {

    public static Command parse(String msg) throws CherryBotException {
        if (msg.equals("bye")) {
            return new ByeCommand();

        } else if (msg.equals("list")) {
            return new ListCommand();

        } else if (msg.startsWith("delete")) {
            String[] splitCommand = msg.trim().split(" ");

            if (splitCommand.length < 2) {
                throw new CherryBotException("Task number is missing");
            }
            int taskNumber = Integer.parseInt(splitCommand[1]) - 1;
            return new DeleteCommand(taskNumber);

        } else if (msg.startsWith("mark")) {
            String[] splitCommand = msg.trim().split(" ");

            if (splitCommand.length < 2) {
                throw new CherryBotException("Task number is missing");
            }

            String foo = splitCommand[1];
            int taskNumber = Integer.parseInt(foo);
            return new MarkCommand(taskNumber - 1);

        } else if (msg.startsWith("unmark")) {
            String[] splitCommand = msg.trim().split(" ");

            if (splitCommand.length < 2) {
                throw new CherryBotException("Task number is missing");
            }

            String foo = splitCommand[1];
            int taskNumber = Integer.parseInt(foo);
            return new UnmarkCommand(taskNumber - 1);

        } else if (msg.startsWith("todo")) {
            String description = msg.trim().substring(5);

            if (description.isEmpty()) {
                throw new CherryBotException("Todo description cannot be empty");
            }

            Task t = new Todo(description);
            return new AddCommand(t);

        } else if (msg.startsWith("deadline")) {
            String activity = msg.trim().substring(8);

            if (!activity.contains(" /by")) {
                throw new CherryBotException("Deadline command invalid : <description> /by <time>");
            }

            String[] splitCommand = activity.trim().split(" /by");
            String description = splitCommand[0];
            LocalDateTime by = LocalDateTime.parse(splitCommand[1].trim(), DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
            Task t = new Deadline(description, by);
            return new AddCommand(t);

        } else if (msg.startsWith("event")) {
            String activity = msg.trim().substring(6);

            if (!activity.contains(" /from") && !activity.contains(" /to")) {
                throw new CherryBotException("Event command invalid : <description> /from <time> /to <time>");
            }

            String[] splitCommand = activity.trim().split("/");
            String description = splitCommand[0].trim();
            String start = splitCommand[1].trim().substring(5);
            String end = splitCommand[2].trim().substring(3);

            Task t = new Event(description, start, end);
            return new AddCommand(t);

        } else {
            throw new CherryBotException("Sorry I don't understand invalid command");
        }
    }

}
