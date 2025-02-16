package cherrybot.ui;

import java.io.IOException;
import java.util.Scanner;

import cherrybot.command.Command;
import cherrybot.exception.CherryBotException;
import cherrybot.storage.Storage;



/**
 * Main class for CherryBot
 * This class handles the running of the bot, reading user commands, executing commands, and handling errors.
 */
public class CherryBot {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructs a CherryBot instance with the given file path for storing tasks.
     * Initializes the UI, storage, and task list.
     *
     * @param filePath The path to the file where tasks are stored.
     */
    public CherryBot(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);

        tasks = new TaskList(storage.loadFile(filePath));
    }

    /**
     * Generates a response for the user's chat message.
     */
    public String getResponse(String input) {
        String response = "";
        try {
            Scanner userInput = new Scanner(input);
            boolean isExit= false;

            while(!isExit && userInput.hasNextLine()) {
                String fullCommand = userInput.nextLine();
                ui.showLine(); // show the divider line ("_______")
                Command c = Parser.parse(fullCommand);

                if (c != null) {
                    c.execute(tasks, ui, storage);
                }

                isExit = c.isExit();
            }
            userInput.close();

        } catch (CherryBotException e) {
            ui.showError(e.getMessage());
        } catch (IOException e) {
            ui.showError(e.getMessage());
        } finally {
            ui.showLine();
        }
        return response;
    }

    /**
     * Runs the CherryBot application, managing the main loop of user interactions.
     * Reads user input, parses commands, executes them, and handles any exceptions.
     * The loop continues until the user issues an exit command.
     */
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

    /**
     * The entry point for running the CherryBot app.
     * Creates an instance of CherryBot and starts its execution.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        Storage.createFileIfNotExists();
        new CherryBot("./data/cherrybot.txt").run();
    }




}
