
import TaskList;
import Storage;
public class TodoAddCommand extends Command {
    private String taskDescription;
    public TodoAddCommand(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws CherryBotException {
        tasks.addTask(new Todo(taskDescription));
        ui.showTodoAdd();


    }

}
