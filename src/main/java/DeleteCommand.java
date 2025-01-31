import java.io.IOException;

public class DeleteCommand extends Command {
    private int taskNumber;

    public DeleteCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        Task t = tasks.getTask(taskNumber);
        tasks.deleteTask(taskNumber);
        ui.showDeleteTask(t, tasks);
    }
}
