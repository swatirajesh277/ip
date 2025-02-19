package cherrybot.command;


import cherrybot.storage.Storage;
import cherrybot.ui.TaskList;
import cherrybot.ui.Ui;

public class SortCommand extends Command {

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.sortTask();
        ui.sortedTasks(tasks.getTasks());
    }
}
