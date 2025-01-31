import java.io.IOException;

public class AddCommand extends Command {
    private Task t;
    public AddCommand(Task t) {
        this.t = t;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        tasks.addTask(t);
        ui.showAdd(t, tasks);
    }

}
