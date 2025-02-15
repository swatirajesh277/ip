package cherrybot.ui;
import cherrybot.exception.CherryBotException;
import cherrybot.task.Task;
import cherrybot.task.Todo;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertThrows;
public class TaskListTest {
    private TaskList taskList;

    @BeforeEach
    public void setUp() {
        taskList = new TaskList();
    }
    @Test
    public void addTask_doneWithValidIndex_success() throws CherryBotException {
        Task trial = new Todo("read book");
        taskList.addTask(trial);
        assertEquals(1, taskList.size());
        assertEquals(trial, taskList.getTask(0));
    }

    @Test
    public void deleteTask_doneWithValidIndex_success() throws CherryBotException {
        Task trial = new Todo("read book");
        Task trial2 = new Todo("grocery shopping");
        taskList.addTask(trial);
        taskList.addTask(trial2);
        taskList.deleteTask(0);
        assertEquals(1, taskList.size());
        assertEquals(trial2, taskList.getTask(0));
    }

    @Test
    public void deleteTask_doneWithInValidIndex_failure() throws CherryBotException {
        Task trial = new Todo("read book");
        taskList.addTask(trial);
        CherryBotException e = assertThrows(CherryBotException.class, () ->
        { taskList.deleteTask(5);
        });

        assertEquals("Invalid task number. Please enter a number between 1 and " + taskList.size(), e.getMessage());

    }

    @Test
    public void deleteTask_doneWithNoItemInList_failure() throws CherryBotException {
        CherryBotException e = assertThrows(CherryBotException.class, () ->
        { taskList.deleteTask(5);
        });

        assertEquals("You cannot delete something that does not exist", e.getMessage());

    }

    @Test
    public void markTask_doneWithNoItemInList_failure() throws CherryBotException {
        CherryBotException e = assertThrows(CherryBotException.class, () ->
        { taskList.markAsDoneToTask(5);
        });

        assertEquals("You cannot mark something that does not exist", e.getMessage());

    }

    @Test
    public void unmarkTask_doneWithInValidIndex_failure() throws CherryBotException {
        Task trial = new Todo("read book");
        taskList.addTask(trial);
        CherryBotException e = assertThrows(CherryBotException.class, () ->
        { taskList.markAsNotDoneToTask(5);
        });

        assertEquals("Invalid task number. Please enter a number between 1 and " + taskList.size(), e.getMessage());

    }


}
