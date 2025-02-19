package cherrybot.task;
import java.time.LocalDateTime;
import java.time.chrono.ChronoLocalDate;
import java.time.chrono.ChronoLocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event task with a description, completion status, start date time, and end date time.
 */
public class Event extends Task {

    protected LocalDateTime start;
    protected LocalDateTime end;
    private static final DateTimeFormatter OUTPUT_FORMAT = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");

    /**
     * Constructs an Event task with the specified description, status, start date time, and end date time.
     *
     * @param description The description of the event task.
     * @param start The start date time of the event.
     * @param end The end date time of the event.
     */
    public Event(String description, LocalDateTime start, LocalDateTime end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    /**
     * Returns a string representation of the event task, including its status, description, start date time, and end date time.
     *
     * @return A string representing the event task.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + start.format(OUTPUT_FORMAT) + " to: " + end.format(OUTPUT_FORMAT) + ")";
    }


    public LocalDateTime getStart() {
        return this.start;
    }
}
