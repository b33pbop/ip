import java.util.*;

public class B33PBOP {
  private static final String horizontalLine = "_".repeat(50);
  private static final List<Task> taskList = new ArrayList<>();

  public static void main(String[] args) {
    B33PBOP.greet();
    B33PBOP.echo();
  }

  public static void greet() {
    String greetings =
            horizontalLine + "\n"
                    + "I'm B33PBOP...\n"
                    + "What do you want?\n"
                    + horizontalLine + "\n";
    System.out.println(greetings);
  }

  public static void echo() {
    Scanner sc = new Scanner(System.in);
    while (true) {
      String input = sc.nextLine().trim().toLowerCase();
      String response;

      switch (input) {
        case "bye":
          response =
                  horizontalLine + "\n"
                          + "Please leave me alone\n"
                          + horizontalLine + "\n";
          System.out.println(response);
          break;
        case "list":
          response =
                  horizontalLine + "\n"
                          + showTaskList()
                          + horizontalLine + "\n";
          System.out.println(response);
          continue;
        default:
          response =
                  horizontalLine + "\n"
                          + "added: " + input + "\n"
                          + horizontalLine + "\n";
          addTask(input);
          System.out.println(response);
          continue;
      }
      break;
    }
    sc.close();
  }

  public static void addTask(String taskName) {
    Task newTask = new Task(taskName);
    taskList.add(newTask);
  }

  public static String showTaskList() {
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < taskList.size(); i++) {
      int idx = i + 1;
      String task = idx + ". " + taskList.get(i) + "\n";
      sb.append(task);
    }
    return sb.toString();
  }
}
