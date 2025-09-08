package chatbot.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import chatbot.exception.BotException;
import chatbot.exception.InvalidArgumentException;

/**
 * DeadlineTask is a subclass of Task.
 * The class handles creation of a deadline task when user uses the deadline command.
 */
public class DeadlineTask extends Task {
    private static final DateTimeFormatter DISPLAY_FORMAT =
            DateTimeFormatter.ofPattern("MMM d yyyy");
    private final LocalDate deadline;

    /**
     * Deadline Task Constructor
     * @param taskName Name of the task
     * @param deadline Deadline of the task
     * @throws BotException If there is an invalid argument
     */
    public DeadlineTask(String taskName, String deadline) throws BotException {
        super(taskName);

        try {
            this.deadline = LocalDate.parse(deadline);
        } catch (DateTimeParseException e) {
            throw new InvalidArgumentException("Invalid date format for deadline: " + deadline + "\n");
        }
    }

    @Override
    public String printCompleteStatus() {
        return "[D]" + super.printCompleteStatus();
    }

    @Override
    public boolean existsInTaskDescription(String keyword) {
        if (keyword == null || keyword.isEmpty()) {
            return false;
        }

        if (getTaskName().toLowerCase().contains(keyword.toLowerCase())) {
            return true;
        }

        String deadlineString = this.deadline.format(DISPLAY_FORMAT).toLowerCase();
        if (deadlineString.contains(keyword.toLowerCase())) {
            return true;
        }

        try {
            LocalDate parsedDate = LocalDate.parse(keyword);
            if (parsedDate.equals(this.deadline)) {
                return true;
            }
        } catch (DateTimeParseException e) {
            // ignore
        }

        return false;
    }

    @Override
    public String toSaveFormat() {
        return "D | "
                + super.printCompleteStatus() + "| "
                + getTaskName() + " | "
                + this.deadline;
    }

    @Override
    public String toString() {
        return super.toString()
                + " (by: "
                + this.deadline.format(DISPLAY_FORMAT)
                + ")";
    }
}
