package cherrybot.command;

import cherrybot.storage.Storage;
import cherrybot.task.Task;
import cherrybot.ui.TaskList;
import cherrybot.ui.Ui;
import cherrybot.exception.CherryBotException;

import java.io.IOException;

/**
 * Represents a command to mark a task as done in the task list.
 */
public class MarkCommand extends Command {
    private int taskNumber;

    /**
     * Constructs a MarkCommand to mark a task as done by its index in the task list.
     *
     * @param taskNumber The index of the task to be marked as done.
     */
    public MarkCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    /**
     * Executes the command to mark a task as done and shows confirmation Ui.
     * If the task is not found or there is an error, an error message will be displayed.
     *
     * @param tasks The task list where the task will be marked as done.
     * @param ui The UI that will display the confirmation or an error message.
     * @param storage The storage system that may be updated to reflect the task's new state.
     * @throws IOException If there is an issue while updating the storage.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        try {
            Task t = tasks.getTask(taskNumber);
            tasks.markAsDoneToTask(taskNumber);
            ui.showMarkTask(t, tasks);
        } catch (IndexOutOfBoundsException | CherryBotException e) {
            ui.showError(e.getMessage());
        }
    }
}
