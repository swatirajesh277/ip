package cherrybot.ui;

import java.io.IOException;

import cherrybot.command.Command;
import cherrybot.exception.CherryBotException;
import cherrybot.storage.Storage;



public class CherryBot {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public CherryBot(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.loadFile(filePath));
        } catch (Exception e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.greeting();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine(); // show the divider line ("_______")
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (CherryBotException e) {
                ui.showError(e.getMessage());
            } catch (IOException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    public static void main(String[] args) {
        new CherryBot("./data/cherrybot.txt").run();
    }

}
