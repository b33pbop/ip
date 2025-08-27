import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DeadlineTask extends Task{
    private static final DateTimeFormatter DISPLAY_FORMAT =
            DateTimeFormatter.ofPattern("MMM d yyyy");
    private LocalDate deadline;

    public DeadlineTask(String taskName, String deadline) {
        super(taskName);
        try {
            this.deadline = LocalDate.parse(deadline);
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date format: " + deadline);
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
                + this.deadline.format(DISPLAY_FORMAT);
    }

    @Override
    public String toString() {
        return super.toString()
                + " (by: "
                + this.deadline.format(DISPLAY_FORMAT)
                + ")";
    }
}
