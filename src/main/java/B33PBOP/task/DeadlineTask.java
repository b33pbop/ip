package B33PBOP.task;

import B33PBOP.exception.BotException;
import B33PBOP.exception.InvalidArgumentException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;


/**
 * DeadlineTask is a subclass of Task.
 * The class handles creation of a deadline task when user uses the deadline command.
 */
public class DeadlineTask extends Task {
    private static final DateTimeFormatter DISPLAY_FORMAT =
            DateTimeFormatter.ofPattern("MMM d yyyy");
    private final LocalDate DEADLINE;

    /**
     * Deadline Task Constructor
     * @param taskName Name of the task
     * @param deadline Deadline of the task
     * @throws BotException If there is an invalid argument
     */
    public DeadlineTask(String taskName, String deadline) throws BotException {
        super(taskName);
        try {
            this.DEADLINE = LocalDate.parse(deadline);
        } catch (DateTimeParseException e) {
            throw new InvalidArgumentException("Invalid date format for deadline: " + deadline +"\n");
        }
    }

    @Override
    public String printCompleteStatus() {
        return "[D]" + super.printCompleteStatus();
    }

    @Override
    public String toSaveFormat() {
        return "D | "
                + super.printCompleteStatus() + "| "
                + getTaskName() + " | "
                + this.DEADLINE;
    }

    @Override
    public String toString() {
        return super.toString()
                + " (by: "
                + this.DEADLINE.format(DISPLAY_FORMAT)
                + ")";
    }
}
