package cherrybot.command;

import java.util.ArrayList;

import cherrybot.storage.Storage;
import cherrybot.task.Task;
import cherrybot.ui.TaskList;
import cherrybot.ui.Ui;

/**
 * Represents a command to find tasks in the task list by matching a given description.
 */
public class FindCommand extends Command {
    private String match;

    /**
     * Constructs a FindCommand with the specified match string.
     *
     * @param match The string to search for in the task descriptions.
     */
    public FindCommand(String match) {
        this.match = match;
    }

    /**
     * Executes the find command to search tasks that match the description.
     * It displays the results using the Ui.
     *
     * @param tasks The TaskList to search through.
     * @param ui The Ui to display the results.
     * @param storage The Storage to manage tasks, though not used in this command.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ArrayList<Task> matchList = tasks.findTask(match);
        ui.showFind(matchList);
    }

}
