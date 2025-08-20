public class DeadlineTask extends Task{
  private final String deadline;
  public DeadlineTask(String taskName, String deadline) {
    super(taskName);
    this.deadline = deadline;
  }

  @Override
  public String getCompleteStatus() {
    return "[D]" + super.getCompleteStatus();
  }

  @Override
  public String toString() {
    return getCompleteStatus() + super.toString() + " (by: " + this.deadline + ")";
  }
}
