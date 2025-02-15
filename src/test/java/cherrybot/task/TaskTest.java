package cherrybot.task;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskTest {

    @Test
    public void checkMark_testIsMarkedCorrectly() {
        Task todo = new Todo("Do assignment");
        todo.markAsDone();
        assertEquals("X", todo.getStatusIcon());
    }

    @Test
    public void checkUnmark_testIsUnmarkedCorrectly() {
        Task todo = new Todo("Clean room");
        todo.markAsDone();
        todo.markAsNotDone();
        assertEquals(" ", todo.getStatusIcon());
    }
}
