package cherrybot.command;

import cherrybot.storage.Storage;
import cherrybot.ui.TaskList;
import cherrybot.ui.Ui;

import java.io.IOException;
import javafx.application.Platform;
import java.util.Timer;
import java.util.TimerTask;


/**
 * Represents a command to exit the program.
 * This command handles the process of saving the task list to the storage
 * and displaying a farewell message via the UI before exiting.
 */

public class ByeCommand extends Command {

    /**
     * Executes the command to exit the program by saving the task list to the file
     * and displaying a goodbye message to the user.
     *
     * @param tasks The task list to be saved to the storage.
     * @param ui The UI that will display the goodbye message.
     * @param storage The storage system that handles writing the task list to a file.
     * @throws IOException If there is an issue while saving the task list to the file.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        try {
            ui.goodbye();
            storage.writeToFile(ui.listFormatForFile(tasks));
            new Timer().schedule(new TimerTask() {
                public void run() {
                    Platform.exit();
                }
            }, 2500);

        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }


    /**
     * Returns true to indicate that the command should exit the program.
     *
     * @return true, indicating the program should exit.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
