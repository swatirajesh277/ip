import java.io.IOException;

public class ByeCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        try {
            ui.goodbye();
            storage.writeToFile(ui.listFormatForFile(tasks));
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
