import java.util.*;

public class B33PBOP {
  private static final String horizontalLine = "_".repeat(75);
  private static final List<Task> taskList = new ArrayList<>();

  public static void main(String[] args) {
    B33PBOP.greet();
    B33PBOP.runCommand();
  }

  public static void greet() {
    String greetings = horizontalLine + "\n"
            + "I'm B33PBOP...\n"
            + "What do you want?\n"
            + horizontalLine + "\n";
    System.out.println(greetings);
  }

  public static void runCommand() {
    Scanner sc = new Scanner(System.in);
    while (true) {
      String input = sc.nextLine().trim();
      String[] inputParts = input.split(" ", 2);
      String command = inputParts[0].trim().toLowerCase();  // normalize command
      String arg = (inputParts.length > 1) ? inputParts[1] : "";

      try {
        switch (command) {
          case "bye":
            byeResponse();
            sc.close();
            return; // exit loop

          case "list":
            listResponse();
            break;

          case "mark":
          case "unmark":
            markResponse(arg);
            break;

          case "todo":
          case "deadline":
          case "event":
            addTaskResponse(input); // full input goes to TaskFactory
            break;

          case "delete":
            deleteTaskResponse(arg);
            break;

          default:
            throw new InvalidCommandException("What even is '" + input + "'?\n");
        }
      } catch (BotException error) {
        String errorMsg = horizontalLine + "\n"
                + error.getMessage()
                + horizontalLine + "\n";
        System.out.println(errorMsg);
      }
    }
  }

  public static Task addTask(String taskDescription) throws BotException {
    Task newTask = TaskFactory.createTask(taskDescription);
    taskList.add(newTask);
    return newTask;
  }

  public static Task deleteTask(int taskId) throws BotException {
    int taskIdx = taskId - 1;
    if (taskList.isEmpty()) {
      throw new TaskListIndexOutOfBoundException("Your list is literally empty\n");
    }

    if (taskId > taskList.size()) {
      throw new InvalidArgumentException("That task don't exist, do you even know what you added??\n");
    } else if (taskId < 1) {
      throw new InvalidArgumentException("Are you drunk? Task " + taskId + "?\n");
    } else {
      return taskList.remove(taskIdx);
    }
  }

  public static String showTaskList() {
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < taskList.size(); i++) {
      int idx = i + 1;
      Task curTask = taskList.get(i);
      String task = idx + "." + curTask + "\n";
      sb.append(task);
    }
    return "You really need help remembering all these?\n" + sb;
  }

  public static void byeResponse() {
    String response = horizontalLine + "\n"
            + "Please leave me alone\n"
            + horizontalLine + "\n";
    System.out.println(response);
  }

  public static void listResponse() {
    String response = horizontalLine + "\n"
            + showTaskList()
            + horizontalLine + "\n";
    System.out.println(response);
  }

  public static void markResponse(String arg) throws BotException {
    try {
      int taskIndex = Integer.parseInt(arg) - 1;
      Task task = taskList.get(taskIndex);
      String response = horizontalLine + "\n"
              + task.toggleCompleteStatus()
              + horizontalLine + "\n";
      System.out.println(response);
    } catch (NumberFormatException | IndexOutOfBoundsException e) {
      throw new InvalidArgumentException("That task don't even exist");
    }
  }

  public static void addTaskResponse(String taskDescription) throws BotException {
    Task newTask = addTask(taskDescription);
    String response = horizontalLine + "\n"
            + "This will be the last time I'm adding this for you:\n "
            + "+ " + newTask + "\n"
            + horizontalLine + "\n";
    System.out.println(response);
  }

  public static void deleteTaskResponse(String deletedTask) throws BotException {
    Task task = deleteTask(Integer.parseInt(deletedTask));
    String response = horizontalLine + "\n"
            + "Thank god, you should really keep deleting tasks:\n"
            + "- " + task + "\n"
            + horizontalLine + "\n";
    System.out.println(response);
  }
}
