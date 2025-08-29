package B33PBOP.ui;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;

public class TaskFunctionTest {
    @Test
    public void testAddTodo() throws Exception {
        // Simulate user input
        String simulatedInput = "todo read book\nbye\n";
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));

        // Capture output
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        // Run your chatbot
        new B33PBOP().start();

        // Check the output
        String output = outputStream.toString().replace("\r\n", "\n");
        Path expectedOutputPath = Path.of("src/test/resources/B33PBOP/ui/todo_output_expected.txt");
        String expectedOutput = Files.readString(expectedOutputPath);
        expectedOutput = expectedOutput.replace("\r\n", "\n");
        assertEquals(expectedOutput, output); // example assertion
    }

    @Test
    public void testAddDeadlineTask() throws Exception {
        // Simulate user input
        String simulatedInput = "deadline return book /by 2026-10-10\nbye\n";
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));

        // Capture output
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        // Run your chatbot
        new B33PBOP().start();

        // Check the output
        String output = outputStream.toString().replace("\r\n", "\n");
        Path expectedOutputPath = Path.of("src/test/resources/B33PBOP/ui/deadline_output_expected.txt");
        String expectedOutput = Files.readString(expectedOutputPath);
        expectedOutput = expectedOutput.replace("\r\n", "\n");
        assertEquals(expectedOutput, output); // example assertion
    }

    @Test
    public void testAddEventTask() throws Exception {
        // Simulate user input
        String simulatedInput = """
                event class /from 2025-05-11 08:00:45 /to 11:00
                event class /from 2025-05-11 08:00 /to 11:00
                event work /from 05/11/2025 08:00 /to 05/12/2025 11:00
                bye
                """;
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));

        // Capture output
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        // Run your chatbot
        new B33PBOP().start();

        // Check the output
        String output = outputStream.toString().replace("\r\n", "\n");
        Path expectedOutputPath = Path.of("src/test/resources/B33PBOP/ui/event_output_expected.txt");
        String expectedOutput = Files.readString(expectedOutputPath);
        expectedOutput = expectedOutput.replace("\r\n", "\n");
        assertEquals(expectedOutput, output); // example assertion
    }
}
