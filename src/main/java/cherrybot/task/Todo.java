package cherrybot.task;


/**
 * Represents a "to-do" task with a description and completion status.
 */

public class Todo extends Task {

    /**
     * Constructs a "to-do" task with the specified description.
     *
     * @param description The description of the to-do task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns a string representation of the "to-do" task, including its status and description.
     *
     * @return A string representing the to-do task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

}
