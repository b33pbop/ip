import java.util.*;

public class B33PBOP {
  private static final String horizontalLine = "_".repeat(75);
  private static final List<Task> taskList = new ArrayList<>();

  public static void main(String[] args) {
    B33PBOP.greet();
    B33PBOP.runCommand();
  }

  public static void greet() {
    String greetings =
            horizontalLine + "\n"
                    + "I'm B33PBOP...\n"
                    + "What do you want?\n"
                    + horizontalLine + "\n";
    System.out.println(greetings);
  }

  public static void runCommand() {
    Scanner sc = new Scanner(System.in);
    while (true) {
      String input = sc.nextLine().trim().toLowerCase();
      String[] inputParts = input.split(" ", 2);
      String command = inputParts[0].trim();
      String arg = (inputParts.length > 1) ? inputParts[1] : "";

      switch (command) {
        case "bye":
          byeResponse();
          break;
        case "list":
          listResponse();
          continue;
        case "mark":
        case "unmark":
          markResponse(arg);
          continue;
        default:
          addTaskResponse(input);
          continue;
      }
      break;
    }
    sc.close();
  }

  public static Task addTask(String taskDescription) {
    Task newTask = TaskFactory.createTask(taskDescription);
    taskList.add(newTask);
    return newTask;
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

  public static void markResponse(String arg) {
    int taskIndex = Integer.parseInt(arg) - 1;
    Task task = taskList.get(taskIndex);
    String response = horizontalLine + "\n"
            + task.toggleCompleteStatus()
            + horizontalLine + "\n";
    System.out.println(response);
  }

  public static void addTaskResponse(String taskDescription) {
    Task newTask = addTask(taskDescription);
    String response = horizontalLine + "\n"
            + "This will be the last time i'm adding this for you:\n " + newTask + "\n"
            + horizontalLine + "\n";
    System.out.println(response);
  }
}
