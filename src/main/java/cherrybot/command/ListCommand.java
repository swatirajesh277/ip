package cherrybot.command;

import cherrybot.exception.CherryBotException;
import cherrybot.storage.Storage;
import cherrybot.ui.TaskList;
import cherrybot.ui.Ui;

import java.io.IOException;

/**
 * Represents a command to list all tasks in the task list.
 */
public class ListCommand extends Command {

    /**
     * Executes the command to list all tasks in the task list.
     * If there are no tasks, a message indicating the list is empty will be shown.
     * If tasks exist, they will be printed to the console.
     *
     * @param tasks The task list from which tasks will be retrieved and displayed.
     * @param ui The UI used to display error messages if any exception occurs.
     * @param storage The storage system, although not directly used in this method, is passed for consistency.
     * @throws IOException If an error occurs while interacting with storage (though not used directly here).
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        try {
            if (tasks.isEmptyTask()) {
                System.out.println("\tNo tasks in list");
            } else {
                System.out.println("\tHere are the tasks in your list:");
                for (int i = 0; i < tasks.size(); i++) {
                    System.out.println("\t" + (i + 1) + ". " + tasks.getTask(i).toString());
                }
            }
        } catch (CherryBotException e) {
            ui.showError(e.getMessage());
        }

    }
}

