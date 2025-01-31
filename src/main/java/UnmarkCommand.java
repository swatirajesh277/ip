import java.io.IOException;

public class UnmarkCommand extends Command {
    private int taskNumber;

    public UnmarkCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        Task t = tasks.getTask(taskNumber);
        t.markAsNotDone();
        ui.showUnmarkTask(t, tasks);
    }
}
