import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks = new ArrayList<>();

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public TaskList() {
        this.tasks = null;
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void deleteTask(int taskNumber) {
        tasks.remove(taskNumber);
    }

    public void markAsDoneToTask(int taskNumber) {
        tasks.get(taskNumber).markAsDone();
    }

    public void markAsNotDoneToTask(int taskNumber) {
        tasks.get(taskNumber).markAsNotDone();
    }

    public Task getTask(int taskNumber) {
        return tasks.get(taskNumber);
    }

    public int size() {
        return tasks.size();
    }

    public boolean isEmptyTask() {
        return tasks.isEmpty();
    }


}
