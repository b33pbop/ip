package B33PBOP.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DeadlineTask extends Task{
    private static final DateTimeFormatter DISPLAY_FORMAT =
            DateTimeFormatter.ofPattern("MMM d yyyy");
    private final LocalDate DEADLINE;

    public DeadlineTask(String taskName, String deadline) {
        super(taskName);
        try {
            this.DEADLINE = LocalDate.parse(deadline);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid date format for deadline: " + deadline);
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
