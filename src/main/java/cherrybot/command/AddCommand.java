package cherrybot.command;

import cherrybot.storage.Storage;
import cherrybot.task.Task;
import cherrybot.ui.TaskList;
import cherrybot.ui.Ui;

import java.io.IOException;

/**
 * Represents a command to add a task to the task list.
 */
public class AddCommand extends Command {
    private Task t;

    /**
     * Constructs an AddCommand to add a specific task.
     *
     * @param t The task to be added to the task list.
     */
    public AddCommand(Task t) {
        this.t = t;
    }


    /**
     * Executes the command to add a task to the task list and displays the confirmation Ui.
     *
     * @param tasks The task list where the task will be added.
     * @param ui The UI that will display the confirmation.
     * @param storage The storage system that handles saving the task list.
     * @throws IOException If there is an issue while saving tasks to the storage.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        tasks.addTask(t);
        ui.showAdd(t, tasks);
    }

}
