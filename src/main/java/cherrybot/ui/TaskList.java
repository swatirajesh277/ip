package cherrybot.ui;

import cherrybot.task.Task;
import cherrybot.exception.CherryBotException;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void deleteTask(int taskNumber) throws CherryBotException {
        if (tasks.isEmpty()) {
            throw new CherryBotException("You cannot delete something that does not exist");
        }
        if (taskNumber < 0 || taskNumber >= tasks.size()) {
            throw new CherryBotException("Invalid task number. Please enter a number between 1 and " + tasks.size());
        }
        tasks.remove(taskNumber);
    }

    public void markAsDoneToTask(int taskNumber) throws CherryBotException {
        if (tasks.isEmpty()) {
            throw new CherryBotException("You cannot mark something that does not exist");
        }
        if (taskNumber < 0 || taskNumber >= tasks.size()) {
            throw new CherryBotException("Invalid task number. Please enter a number between 1 and " + tasks.size());
        }
        tasks.get(taskNumber).markAsDone();
    }

    public void markAsNotDoneToTask(int taskNumber) throws CherryBotException {
        if (tasks.isEmpty()) {
            throw new CherryBotException("You cannot unmark something that does not exist");
        }
        if (taskNumber < 0 || taskNumber >= tasks.size()) {
            throw new CherryBotException("Invalid task number. Please enter a number between 1 and " + tasks.size());
        }
        tasks.get(taskNumber).markAsNotDone();
    }

    public Task getTask(int taskNumber) throws CherryBotException {
        if (taskNumber < 0 || taskNumber >= tasks.size()) {
            throw new CherryBotException("Invalid task number. Please enter a number between 1 and " + tasks.size());
        }
        return tasks.get(taskNumber);
    }

    public int size() {
        return tasks.size();
    }

    public boolean isEmptyTask() {
        return tasks.isEmpty();
    }


}
