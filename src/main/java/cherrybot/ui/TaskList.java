package cherrybot.ui;

import cherrybot.task.Task;
import cherrybot.exception.CherryBotException;

import java.util.ArrayList;

/**
 * Represents a list of tasks and provides methods for managing and manipulating the tasks.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Creates a TaskList with the provided list of tasks.
     *
     * @param tasks The list of tasks to initialize the TaskList with.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Creates an empty TaskList.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Adds a new task to the list.
     *
     * @param task The task to add to the list.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Deletes the task at the specified task number.
     *
     * @param taskNumber The task number (index) of the task to delete.
     * @throws CherryBotException If the task number is invalid or if the task list is empty.
     */
    public void deleteTask(int taskNumber) throws CherryBotException {
        if (tasks.isEmpty()) {
            throw new CherryBotException("You cannot delete something that does not exist");
        }
        if (taskNumber < 0 || taskNumber >= tasks.size()) {
            throw new CherryBotException("Invalid task number. Please enter a number between 1 and " + tasks.size());
        }
        tasks.remove(taskNumber);
    }

    /**
     * Marks the task at the specified task number as done.
     *
     * @param taskNumber The task number (index) of the task to mark as done.
     * @throws CherryBotException If the task number is invalid, or if the task list is empty.
     */
    public void markAsDoneToTask(int taskNumber) throws CherryBotException {
        if (tasks.isEmpty()) {
            throw new CherryBotException("You cannot mark something that does not exist");
        }
        if (taskNumber < 0 || taskNumber >= tasks.size()) {
            throw new CherryBotException("Invalid task number. Please enter a number between 1 and " + tasks.size());
        }
        tasks.get(taskNumber).markAsDone();
    }

    /**
     * Marks the task at the specified task number as not done.
     *
     * @param taskNumber The task number (index) of the task to mark as not done.
     * @throws CherryBotException If the task number is invalid, or if the task list is empty.
     */
    public void markAsNotDoneToTask(int taskNumber) throws CherryBotException {
        if (tasks.isEmpty()) {
            throw new CherryBotException("You cannot unmark something that does not exist");
        }
        if (taskNumber < 0 || taskNumber >= tasks.size()) {
            throw new CherryBotException("Invalid task number. Please enter a number between 1 and " + tasks.size());
        }
        tasks.get(taskNumber).markAsNotDone();
    }

    /**
     * Retrieves the task at the specified task number.
     *
     * @param taskNumber The task number (index) of the task to retrieve.
     * @return The task at the specified task number.
     * @throws CherryBotException If the task number is invalid.
     */
    public Task getTask(int taskNumber) throws CherryBotException {
        if (taskNumber < 0 || taskNumber >= tasks.size()) {
            throw new CherryBotException("Invalid task number. Please enter a number between 1 and " + tasks.size());
        }
        return tasks.get(taskNumber);
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return The size of the task list.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Checks if the task list is empty.
     *
     * @return true if the task list is empty, false otherwise.
     */
    public boolean isEmptyTask() {
        return tasks.isEmpty();
    }

    /**
     * Finds tasks in the task list that match a given string in their description.
     *
     * @param match The string to search for in the task descriptions.
     * @return A list of tasks whose descriptions contain the specified match string.
     */
    public ArrayList<Task> findTask(String match) {
        ArrayList<Task> result = new ArrayList<>();

        for (Task t : tasks) {
            if (t.getDescription().contains(match)) {
                result.add(t);
            }
        }

        return result;
    }


}
