package B33PBOP.task;

import B33PBOP.exception.BotException;
import B33PBOP.exception.InvalidArgumentException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DeadlineTask extends Task{
    private static final DateTimeFormatter DISPLAY_FORMAT =
            DateTimeFormatter.ofPattern("MMM d yyyy");
    private final LocalDate DEADLINE;

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
    public boolean existsInTaskDescription(String keyword) {
        if (keyword == null || keyword.isEmpty()) {
            return false;
        }

        if (getTaskName().toLowerCase().contains(keyword.toLowerCase())) {
            return true;
        }

        String deadlineString = this.DEADLINE.format(DISPLAY_FORMAT).toLowerCase();
        if (deadlineString.contains(keyword.toLowerCase())) {
            return true;
        }

        try {
            LocalDate parsedDate = LocalDate.parse(keyword);
            if (parsedDate.equals(this.DEADLINE)) {
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
