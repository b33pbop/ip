public class Task {
  private boolean completeStatus = false;
  private final String taskName;

  public Task(String taskName) {
    this.taskName = taskName;
  }

  public String toggleCompleteStatus() {
    this.completeStatus = !this.completeStatus;
    String statusMessage;
    if (this.completeStatus) {
      statusMessage = "Ugh. Can't you do this yourself?\n"
              + getCompleteStatus() + this.taskName + "\n";
    } else {
      statusMessage = "Make up your mind...\n"
              + getCompleteStatus() + this.taskName + "\n";
    }
    return statusMessage;
  }

  public String getCompleteStatus() {
    return this.completeStatus ? "[X] " : "[ ] ";
  }

  @Override
  public String toString() {
    return this.taskName;
  }
}
