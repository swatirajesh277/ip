package cherrybot.command;

import cherrybot.exception.CherryBotException;
import cherrybot.storage.Storage;
import cherrybot.ui.TaskList;
import cherrybot.ui.Ui;

import java.io.IOException;

public class ListCommand extends Command {

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

