package cherrybot.command;


import cherrybot.storage.Storage;
import cherrybot.ui.TaskList;
import cherrybot.ui.Ui;

/**
 * Represents a command to sort tasks in the task list.
 */

public class SortCommand extends Command {

    /**
     * Executes the sort command to sort tasks.
     * It displays the results using the Ui.
     *
     * @param tasks The TaskList to sort.
     * @param ui The Ui to display the results.
     * @param storage The Storage to manage tasks, though not used in this command.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.sortTask();
        ui.sortedTasks(tasks.getTasks());
    }
}
