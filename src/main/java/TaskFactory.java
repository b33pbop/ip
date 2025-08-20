public class TaskFactory {
  public static Task createTask(String taskDescription) throws BotException {
    taskDescription = taskDescription.trim();
    String[] inputParts = taskDescription.split(" ", 2);
    String taskType = inputParts[0].trim();
    String description = (inputParts.length > 1) ? inputParts[1].trim() : "";

    switch (taskType) {
      case "event":
        if (taskDescription.contains("/from") && taskDescription.contains("/to")) {
          String[] descriptionArray = description.split("/from", 2);
          String taskName = descriptionArray[0].trim();
          String[] startEndArray = descriptionArray[1].split("/to", 2);
          if (startEndArray.length < 2 || startEndArray[0].trim().isEmpty() || startEndArray[1].trim().isEmpty()) {
            throw new InvalidArgumentException("Do you not know when YOUR event starts and ends...\n");
          } else {
            String from = startEndArray[0].trim();
            String to = startEndArray[1].trim();
            return new EventTask(taskName, from, to);
          }
        } else {
          throw new InvalidCommandException("What are you even trying to say...\n");
        }

      case "deadline":
        if (taskDescription.contains("/by")) {
          String[] descriptionArray = description.split("/by", 2);
          if (descriptionArray.length < 2 || descriptionArray[1].trim().isEmpty()) {
            throw new InvalidArgumentException("Deadline task but you don't know the deadline...\n");
          } else {
            String taskName = descriptionArray[0].trim();
            String deadline = descriptionArray[1].trim();
            return new DeadlineTask(taskName, deadline);
          }
        } else {
          throw new InvalidCommandException("Can you even type properly...\n");
        }

      case "todo":
        if (description.isEmpty()){
          throw new IncompleteArgumentException("To do... what?\n");
        } else {
          return new ToDoTask(description);
        }

      default:
        throw new InvalidCommandException("I have zero clue what you are trying to say\n");
    }
  }
}
