package cherrybot.command;

import java.util.ArrayList;

import cherrybot.storage.Storage;
import cherrybot.task.Task;
import cherrybot.ui.TaskList;
import cherrybot.ui.Ui;

public class FindCommand extends Command {
    private String match;

    public FindCommand(String match) {
        this.match = match;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ArrayList<Task> matchList = tasks.findTask(match);
        ui.showFind(matchList);
    }

}
