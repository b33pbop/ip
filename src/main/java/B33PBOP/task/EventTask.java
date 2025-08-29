package B33PBOP.task;

import B33PBOP.exception.BotException;
import B33PBOP.exception.InvalidEventEndDateException;
import B33PBOP.util.DateTimeParser;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class EventTask extends Task {
    private static final DateTimeFormatter DISPLAY_FORMAT =
            DateTimeFormatter.ofPattern("MMM d yyyy HH:mm");
    private static final DateTimeFormatter STORAGE_FORMAT =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    private LocalDateTime from;
    private LocalDateTime to;

    public EventTask(String taskName, String from, String to) throws BotException {
        super(taskName);
        try {
            this.from = DateTimeParser.parseDateTime(from);
            if (to.matches("\\d{1,2}:\\d{2}(:\\d{2})?")) {
                String[] date = from.split(" ");
                this.to = DateTimeParser.combineDateAndTime(date[0], to);
            } else {
                this.to = DateTimeParser.parseDateTime(to);
            }

            if (this.from.isAfter(this.to)) {
                throw new InvalidEventEndDateException("How is your event ending before it starts???\n");
            }

        } catch (DateTimeParseException e) {
            System.out.println("Invalid date format\n");
        }
    }

    @Override
    public String printCompleteStatus() {
        return "[E]" + super.printCompleteStatus();
    }

    @Override
    public String toSaveFormat() {
        return "E | "
                + super.printCompleteStatus() + "| "
                + getTaskName() + " | "
                + this.from.format(STORAGE_FORMAT) + " to " + this.to.format(STORAGE_FORMAT);
    }

    @Override
    public String toString() {
        return super.toString()
                + " (from: "
                + this.from.format(DISPLAY_FORMAT)
                + " to: "
                + this.to.format(DISPLAY_FORMAT)
                + ")";
    }
}
