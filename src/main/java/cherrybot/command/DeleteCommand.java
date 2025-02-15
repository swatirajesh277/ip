package cherrybot.command;

import cherrybot.storage.Storage;
import cherrybot.task.Task;
import cherrybot.ui.TaskList;
import cherrybot.ui.Ui;
import cherrybot.exception.CherryBotException;

import java.io.IOException;

/**
 * Represents a command to delete a task from the task list.
 */
public class DeleteCommand extends Command {
    private int taskNumber;

    /**
     * Constructs a DeleteCommand to delete a task by its index in the task list.
     *
     * @param taskNumber The index of the task to be deleted.
     */
    public DeleteCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }


    /**
     * Executes the command to delete a task from the task list and shows confirmation Ui.
     * If the task is not found or there is an error, an error message will be shown.
     *
     * @param tasks The task list where the task will be deleted from.
     * @param ui The UI that will display the confirmation or an error message.
     * @param storage The storage system that may be updated to reflect the task deletion.
     * @throws IOException If there is an issue while updating the storage.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        try {
            Task t = tasks.getTask(taskNumber);
            tasks.deleteTask(taskNumber);
            ui.showDeleteTask(t, tasks);
        } catch (IndexOutOfBoundsException | CherryBotException e) {
            ui.showError(e.getMessage());
        }
    }
}
