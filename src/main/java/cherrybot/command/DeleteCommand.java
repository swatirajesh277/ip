package cherrybot.command;

import cherrybot.storage.Storage;
import cherrybot.task.Task;
import cherrybot.ui.TaskList;
import cherrybot.ui.Ui;
import cherrybot.exception.CherryBotException;

import java.io.IOException;

public class DeleteCommand extends Command {
    private int taskNumber;

    public DeleteCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

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
