public class TaskFactory {
  public static Task createTask(String taskDescription) {
    taskDescription = taskDescription.trim();
    if (taskDescription.contains("/from") && taskDescription.contains("/to")) {
      String[] inputParts = taskDescription.split("/from", 2);
      String taskName = inputParts[0].trim();
      String period = inputParts[1].trim();
      String[] times = period.split("/to", 2);
      String from = times[0].trim();
      String to = times[1].trim();

      return new EventTask(taskName, from, to);
    } else if (taskDescription.contains("/by")) {
      String[] inputParts = taskDescription.split("/by", 2);
      String taskName = inputParts[0].trim();
      String deadline = inputParts[1].trim();

      return new DeadlineTask(taskName, deadline);
    } else {
      String[] inputParts = taskDescription.split(" ", 2);
      String taskName = inputParts[1].trim();
      return new ToDoTask(taskName);
    }
  }
}
