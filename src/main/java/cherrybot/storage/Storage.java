package cherrybot.storage;

import cherrybot.task.Deadline;
import cherrybot.task.Event;
import cherrybot.task.Task;
import cherrybot.task.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Handles reading from and writing to the storage file for CherryBot's tasks.
 */
public class Storage {
    private String filepath;

    /**
     * Constructs a Storage object with the specified file path.
     *
     * @param filepath The path to the file where all tasks will be stored.
     */
    public Storage(String filepath) {
        this.filepath = filepath;
    }

    /**
     * Creates the necessary directories and the storage file if they do not exist.
     */
    public static void createFileIfNotExists() {
        File dataDir = new File("./data");
        if (!dataDir.exists()) {
            dataDir.mkdir();
        }


        File f = new File("./data/cherrybot.txt");
        if (!f.exists()) {
            try {
                f.createNewFile();
            } catch (IOException e) {
                System.out.println("Error creating file: " + e.getMessage());
            }
        }
    }

    /**
     * Loads tasks from the specified file into an ArrayList.
     * Each line in the file represents a task and is converted into the appropriate Task object.
     *
     * @param filepath The path to the file containing tasks.
     * @return An ArrayList of Task objects read from the file.
     */
    public static ArrayList<Task> loadFile(String filepath) {
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            File f = new File("./data/cherrybot.txt");
            Scanner fileReader = new Scanner(f);
            while (fileReader.hasNextLine()) {

                String line = fileReader.nextLine();
                Task t = stringToTask(line);
                tasks.add(t);
            }

            fileReader.close();

        } catch (FileNotFoundException e) {
            System.out.println("OOPSIE DAISY!!! An error has occurred in reading the file");
        }
        return tasks;
    }

    /**
     * Converts a string representation of a task into an actual Task object.
     *
     * @param line The string representation of the task.
     * @return The corresponding Task object created from the string.
     */
    private static Task stringToTask(String line) {
        if (line.charAt(1) == 'T') {
            String description = line.trim().substring(7);

            Todo t = new Todo(description);
            if (line.charAt(4) == 'X') {
                t.markAsDone();
            }
            return t;
        } else if (line.charAt(1) == 'D') {
            String[] splitCommand = line.trim().split(" \\(by: ");
            String finDate = splitCommand[1].replace(")", "");

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
            LocalDateTime by = LocalDateTime.parse(finDate.trim(), formatter);
            String description = splitCommand[0].substring(7);

            Deadline d =  new Deadline(description, by);
            if (line.charAt(4) == 'X') {
                d.markAsDone();
            }
            return d;
        } else {
            String[] splitCommand = line.trim().split(" \\(from: ");
            String[] splitTimes = splitCommand[1].replace(")", "").split(" to: ");

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
            LocalDateTime start = LocalDateTime.parse(splitTimes[0].trim(), formatter);
            LocalDateTime end = LocalDateTime.parse(splitTimes[1].trim(), formatter);
            String description = splitCommand[0].substring(7).trim();

            Event e = new Event(description, start, end);
            if (line.charAt(4) == 'X') {
                e.markAsDone();
            }
            return e;
        }
    }

    /**
     * Writes the specified text to the storage file.
     * This can be used to update the task list in the file.
     *
     * @param textToAdd The text to be written to the file.
     * @throws IOException If there is an issue writing to the file.
     */
    public static void writeToFile(String textToAdd) throws IOException {
        FileWriter fw = new FileWriter("./data/cherrybot.txt");
        fw.write(textToAdd);
        fw.close();
    }



}
