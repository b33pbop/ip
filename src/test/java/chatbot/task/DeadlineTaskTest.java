package chatbot.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import chatbot.exception.BotException;
import chatbot.exception.InvalidArgumentException;

public class DeadlineTaskTest {

    @Test
    public void testPrintCompleteStatus_incompleteTask() throws BotException {
        DeadlineTask task = new DeadlineTask("submit report", "2025-09-01");
        assertEquals("[D][ ] ", task.printCompleteStatus());
    }

    @Test
    public void testPrintCompleteStatus_completedTask() throws BotException {
        DeadlineTask task = new DeadlineTask("submit report", "2025-09-01");
        task.markTaskComplete();
        assertEquals("[D][X] ", task.printCompleteStatus());
    }

    @Test
    public void testToSaveFormat_incompleteTask() throws BotException {
        DeadlineTask task = new DeadlineTask("submit report", "2025-09-01");
        assertEquals("D | [ ] | submit report | 2025-09-01",
                task.toSaveFormat());
    }

    @Test
    public void testToSaveFormat_completedTask() throws BotException {
        DeadlineTask task = new DeadlineTask("submit report", "2025-09-01");
        task.markTaskComplete();
        assertEquals("D | [X] | submit report | 2025-09-01",
                task.toSaveFormat());
    }

    @Test
    public void testToString_incompleteTask() throws BotException {
        DeadlineTask task = new DeadlineTask("submit report", "2025-09-01");
        assertEquals("[D][ ] submit report (by: Sep 1 2025)", task.toString());
    }

    @Test
    public void testToString_completedTask() throws BotException {
        DeadlineTask task = new DeadlineTask("submit report", "2025-09-01");
        task.markTaskComplete();
        assertEquals("[D][X] submit report (by: Sep 1 2025)", task.toString());
    }

    @Test
    public void testInvalidDate_throwsException() {
        Exception exception = assertThrows(InvalidArgumentException.class, () -> {
            new DeadlineTask("submit report", "invalid-date");
        });
        assertEquals("Invalid date format for deadline: invalid-date\n",
                exception.getMessage());
    }
}
