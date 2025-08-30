package B33PBOP.task;

import B33PBOP.exception.BotException;
import B33PBOP.exception.InvalidEventEndDateException;
import B33PBOP.util.DateTimeParser;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;


/**
 * EventTask is a subclass of Task.
 * The class handles creation of an event task when user uses the event command.
 */
public class EventTask extends Task {
    // Date format when displayed to the user
    private static final DateTimeFormatter DISPLAY_FORMAT =
            DateTimeFormatter.ofPattern("MMM d yyyy HH:mm");
    // Date format when stored in Storage
    private static final DateTimeFormatter STORAGE_FORMAT =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    private LocalDateTime from;
    private LocalDateTime to;

    /**
     * Event Task constructor
     * @param taskName Name of task
     * @param from Start datetime of the event
     * @param to Ending datetime of the event
     * @throws BotException If the datetime of to is before from
     */
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
    public boolean existsInTaskDescription(String keyword) {
        if (keyword == null || keyword.isEmpty()) {
            return false;
        }
        String lowerKeyword = keyword.toLowerCase();
        return getTaskName().toLowerCase().contains(lowerKeyword)
                || from.format(DISPLAY_FORMAT).toLowerCase().contains(lowerKeyword)
                || to.format(DISPLAY_FORMAT).toLowerCase().contains(lowerKeyword);
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
