import java.io.IOException;

public class MarkCommand extends Command {
    private int taskNumber;

    public MarkCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        Task t = tasks.getTask(taskNumber);
        t.markAsDone();
        ui.showMarkTask(t, tasks);
    }
}
