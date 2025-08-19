public class TaskFactory {
  public static Task createTask(String taskDescription) {
    taskDescription = taskDescription.trim();
    String[] inputParts = taskDescription.split(" ", 2);
//    String taskType = inputParts[0].trim();
    String description = inputParts[1].trim();

    if (taskDescription.contains("/from") && taskDescription.contains("/to")) {
      String[] descriptionArray = description.split("/from", 2);
      String taskName = descriptionArray[0].trim();
      String[] startEndArray = descriptionArray[1].split("/to", 2);
      String from = startEndArray[0].trim();
      String to = startEndArray[1].trim();
      return new EventTask(taskName, from, to);
    } else if (taskDescription.contains("/by")) {
      String[] descriptionArray = description.split("/by", 2);
      String taskName = descriptionArray[0].trim();
      String deadline = descriptionArray[1].trim();

      return new DeadlineTask(taskName, deadline);
    } else {
      return new ToDoTask(description);
    }
  }
}
