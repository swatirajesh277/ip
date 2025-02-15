package cherrybot.command;

import cherrybot.storage.Storage;
import cherrybot.ui.TaskList;
import cherrybot.ui.Ui;

import java.io.IOException;


/**
 * Represents an abstract command that can be executed in the chatbot.
 * All specific commands should extend this class
 */
public abstract class Command {

    /**
     * Executes the command with the given task list, UI, and storage.
     * The implementation of this method should define the specific action for the command.
     *
     * @param tasks The task list that may be modified or referenced by the command.
     * @param ui The UI component that displays messages.
     * @param storage The storage system that may be accessed by the command for reading or writing data.
     * @throws IOException If there is an issue during the execution of the command, such as saving data to a file.
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws IOException;

    /**
     * Indicates whether this command should terminate the program.
     * By default, this method returns false, meaning the program should not exit.
     * Subclasses can override this method to specify when the program should exit.
     *
     * @return true if the command should cause the program to exit; false otherwise.
     */
    public boolean isExit() {
        return false;
    }
}
