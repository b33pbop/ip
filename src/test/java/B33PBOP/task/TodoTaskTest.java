package B33PBOP.task;

import B33PBOP.exception.BotException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ToDoTaskTest {
    @Test
    public void testPrintCompleteStatus_notDone() {
        ToDoTask task = new ToDoTask("read book");
        String expected = "[T][ ] ";
        assertEquals(expected, task.printCompleteStatus());
    }

    @Test
    public void testPrintCompleteStatus_done() throws BotException {
        ToDoTask task = new ToDoTask("read book");
        task.markTaskComplete(); // Assuming Task has markAsDone()
        String expected = "[T][X] ";
        assertEquals(expected, task.printCompleteStatus());
    }

    @Test
    public void testToSaveFormat_notDone() {
        ToDoTask task = new ToDoTask("read book");
        String expected = "T | [ ] | read book";
        assertEquals(expected, task.toSaveFormat());
    }

    @Test
    public void testToSaveFormat_done() throws BotException {
        ToDoTask task = new ToDoTask("read book");
        task.markTaskComplete();
        String expected = "T | [X] | read book";
        assertEquals(expected, task.toSaveFormat());
    }
}
