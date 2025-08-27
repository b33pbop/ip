public class EventTask extends Task {
    private final String from;
    private final String to;
    public EventTask(String taskName, String from, String to) {
        super(taskName);
        this.from = from;
        this.to = to;
    }

    @Override
    public String printCompleteStatus() {
        return "[E]" + super.printCompleteStatus();
    }

    @Override
    public String toString() {
        return super.toString() + " (from: " + this.from + " to: " + this.to + ")";
    }
}
