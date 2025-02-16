package cherrybot.ui;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
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
    private String commandType;

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
    //Googled how to store System.out and then learnt the theory
    // and practicals of how to use PrintStream using chatgpt
    //have attached the pdf of conversations with chatgpt (theory lesson)
    public String getResponse(String input) {
        String response;
        ByteArrayOutputStream outputReal = new ByteArrayOutputStream();
        PrintStream newOut = new PrintStream(outputReal);
        PrintStream originalOut = System.out;  //store original print statement in a print stream

        try {
            System.setOut(newOut); //Redirect system.out to the new print stream

            if (input.isEmpty()) {
                ui.showError("Invalid command! Did you realize you sent an empty text");
            }

            Scanner userInput = new Scanner(input);
            boolean isExit= false;

            while(!isExit && userInput.hasNextLine()) {
                String fullCommand = userInput.nextLine();
                ui.showLine(); // show the divider line ("_______")
                Command c;
                try {
                     c = Parser.parse(fullCommand);
                    if (c == null) {
                        throw new CherryBotException("Invalid command! Did you realize you sent an empty text");
                    }
                } catch (CherryBotException e) {
                    ui.showError(e.getMessage());
                    return "OOPSIE DAISY!!!" + e.getMessage();
                }

                c.execute(tasks, ui, storage);
                commandType = c.getClass().getSimpleName();

                isExit = c.isExit();
            }
            userInput.close();
            response = outputReal.toString().trim();

        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            System.setOut(originalOut);
        }
        return response;
    }

    public String getCommandType() {
        return commandType;
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
