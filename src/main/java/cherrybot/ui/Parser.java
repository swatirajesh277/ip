package cherrybot.ui;



import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import cherrybot.command.*;
import cherrybot.exception.CherryBotException;
import cherrybot.task.Deadline;
import cherrybot.task.Event;
import cherrybot.task.Task;
import cherrybot.task.Todo;
;


/**
 * Parses user input commands and returns the appropriate command object.
 * This class is responsible for interpreting and validating user commands,
 * such as "list", "mark", "deadline", "event", and "to-do".
 */
public class Parser {

    /**
     * Parses a user input message and returns a corresponding command object.
     *
     * @param msg The user input message.
     * @return A Command object representing the user's intent.
     * @throws CherryBotException If the input command is invalid or cannot be parsed.
     */
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

            if (msg.length() < 5 || msg.charAt(4) != ' ') {
                throw new CherryBotException("Invalid format! The todo command must be followed by a space and a description.");
            }

            String description = msg.substring(5).trim();

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
            String description = splitCommand[0].trim();

            try {
                LocalDateTime by = LocalDateTime.parse(splitCommand[1].trim(), DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
                Task t = new Deadline(description, by);
                return new AddCommand(t);
            } catch (DateTimeParseException e) {
                throw new CherryBotException("Invalid date format! Please use dd/MM/yyyy HH:mm");
            }

        } else if (msg.startsWith("event")) {
            String activity = msg.trim().substring(5);

            if (!activity.contains(" /from") && !activity.contains(" /to")) {
                throw new CherryBotException("Event command invalid : <description> /from <time> /to <time>");
            }

            try {
                String[] splitCommand = activity.split(" /from ");
                String description = splitCommand[0].trim();

                String[] timeParts = splitCommand[1].split(" /to ");

                if (timeParts.length < 2) {
                    throw new CherryBotException("Event command inavlid : Missing /to time");
                }

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
                LocalDateTime start = LocalDateTime.parse(timeParts[0].trim(), formatter);
                LocalDateTime end = LocalDateTime.parse(timeParts[1].trim(), formatter);

                Task t = new Event(description, start, end);
                return new AddCommand(t);

            } catch (DateTimeParseException e) {
                throw new CherryBotException("Invalid date format! Please use dd/MM/yyyy HH:mm");
            }

        } else if (msg.startsWith("find")) {
            if (msg.length() < 5 || msg.charAt(4) != ' ') {
                throw new CherryBotException("Invalid format! The find command must be followed by a space and a match string.");
            }

            String description = msg.substring(5).trim();

            return new FindCommand(description);
        } else {
            throw new CherryBotException("Sorry I don't understand invalid command");
        }
    }
}
