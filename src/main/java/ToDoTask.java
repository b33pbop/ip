public class ToDoTask extends Task{
  public ToDoTask(String taskName) {
    super(taskName);
  }

  @Override
  public String getCompleteStatus() {
    return "[T]" + super.getCompleteStatus();
  }

  public String toString() {
    return getCompleteStatus() + super.toString();
  }
}
