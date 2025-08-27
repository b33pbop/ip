import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * B33PBOP is a command-lin task management bot.
 * It supports commands to add tasks, delete tasks, list tasks, mark/unmark task completion and exit.
 */
public class B33PBOP {

    /**
     * Enum representing valid commands.
     */
    public enum Command {
        TODO,
        DEADLINE,
        EVENT,
        MARK,
        UNMARK,
        DELETE,
        LIST,
        BYE
    }

    // Horizontal line used to format bot response.
    private static final String HORIZONTAL_LINE = "_".repeat(75);

    // List of tasks managed by the bot
    private static final List<Task> myTasks = new ArrayList<>();

    /**
     * Main entry point of B33PBOP.
     * Prints greetings and starts command processing.
     * @param args Command-line arguments
     */
    public static void main(String[] args) {
        B33PBOP.greet();
        B33PBOP.runCommand();
    }

    /**
     * Prints the initial greeting message when the bot starts
     */
    public static void greet() {
        String greetings = HORIZONTAL_LINE + "\n"
                + "I'm B33PBOP...\n"
                + "What do you want?\n"
                + HORIZONTAL_LINE + "\n";
        System.out.println(greetings);
    }

    /**
     * Reads and processes user commands in a loop until the BYE command is entered.
     * Commands are parsed and delegated to corresponding response methods.
     */
    public static void runCommand() {
        // Scanner to read user input from terminal
        Scanner sc = new Scanner(System.in);
        while (true) {
            String input = sc.nextLine().trim();
            String[] inputParts = input.split(" ", 2);
            String cmdStr = inputParts[0].trim();
            String arg = (inputParts.length > 1) ? inputParts[1] : "";

            try {
                Command command = parseCommand(cmdStr);

                switch (command) {
                case BYE:
                    byeResponse();
                    sc.close();
                    return; // exit loop

                case LIST:
                    listResponse();
                    break;

                case MARK:
                case UNMARK:
                    markResponse(arg);
                    break;

                case TODO:
                case DEADLINE:
                case EVENT:
                    addTaskResponse(input); // full input goes to TaskFactory
                    break;

                case DELETE:
                    deleteTaskResponse(arg);
                    break;

                default:
                    throw new InvalidCommandException("What even is '" + input + "'?\n");
                }
            } catch (BotException error) {
                String errorMsg = HORIZONTAL_LINE + "\n"
                        + error.getMessage()
                        + HORIZONTAL_LINE + "\n";
                System.out.println(errorMsg);
            }
        }
    }

    /**
     * Creates a new task based on the given task description and adds it to the task list.
     * @param taskDescription Description of the task to be added.
     * @return A new Task object.
     * @throws BotException If the task creation fails.
     */
    public static Task addTask(String taskDescription) throws BotException {
        Task newTask = TaskFactory.createTask(taskDescription);
        myTasks.add(newTask);
        return newTask;
    }

    /**
     * Deletes a task based on its ID from the task list.
     * @param taskId 1-based index of the task to delete.
     * @return The deleted Task object.
     * @throws BotException If the task is empty or the taskId is invalid.
     */
    public static Task deleteTask(int taskId) throws BotException {
        int taskIdx = taskId - 1;
        if (myTasks.isEmpty()) {
            throw new TaskListIndexOutOfBoundException("Your list is literally empty\n");
        }

        if (taskId > myTasks.size()) {
            throw new InvalidArgumentException("That task don't exist, do you even know what you added??\n");
        } else if (taskId < 1) {
            throw new InvalidArgumentException("Are you drunk? Task " + taskId + "?\n");
        } else {
            return myTasks.remove(taskIdx);
        }
    }

    /**
     * Returns a formatted string representation of all tasks in the task list.
     * @return A string that lists all tasks with their indices (1-indexed).
     */
    public static String showTaskList() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < myTasks.size(); i++) {
            int idx = i + 1;
            Task curTask = myTasks.get(i);
            String task = idx + "." + curTask + "\n";
            sb.append(task);
        }
        return "You really need help remembering all these?\n" + sb;
    }

    /**
     * Converts a string command into the corresponding Command enum.
     * @param cmdStr The string representation of the command.
     * @return The corresponding enum value of the cmdStr.
     * @throws InvalidCommandException If the string does not match any valid command.
     */
    private static Command parseCommand(String cmdStr) throws InvalidCommandException {
        try {
            return Command.valueOf(cmdStr.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new InvalidCommandException("What even is '" + cmdStr + "'?\n");
        }
    }

    /**
     * Prints the exit message when the BYE command is executed.
     */
    public static void byeResponse() {
        String response = HORIZONTAL_LINE + "\n"
                + "Please leave me alone\n"
                + HORIZONTAL_LINE + "\n";
        System.out.println(response);
    }

    /**
     * Prints the current list of tasks in a formatted way when the LIST command is executed.
     */
    public static void listResponse() {
        String response = HORIZONTAL_LINE + "\n"
                + showTaskList()
                + HORIZONTAL_LINE + "\n";
        System.out.println(response);
    }

    /**
     * Marks or unmarks a task as complete based on its index.
     * @param taskIdx 1-based index of the task to be marked/unmarked.
     * @throws BotException If index is invalid of the task does not exist.
     */
    public static void markResponse(String taskIdx) throws BotException {
        try {
            int taskIndex = Integer.parseInt(taskIdx) - 1;
            Task task = myTasks.get(taskIndex);
            String response = HORIZONTAL_LINE + "\n"
                    + task.toggleCompleteStatus()
                    + HORIZONTAL_LINE + "\n";
            System.out.println(response);
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw new InvalidArgumentException("That task don't even exist");
        }
    }


    /**
     * Prints the bot's response when any add task command is executed.
     * @param taskDescription Description of the task to be added.
     * @throws BotException If the task creation fails.
     */
    public static void addTaskResponse(String taskDescription) throws BotException {
        Task newTask = addTask(taskDescription);
        String response = HORIZONTAL_LINE + "\n"
                + "This will be the last time I'm adding this for you:\n "
                + "+ " + newTask + "\n"
                + HORIZONTAL_LINE + "\n";
        System.out.println(response);
    }

    /**
     * Prints the bot's response when the DELETE command is executed.
     * @param taskDescription Description of the task to be deleted.
     * @throws BotException If the task deletion fails.
     */
    public static void deleteTaskResponse(String taskDescription) throws BotException {
        Task task = deleteTask(Integer.parseInt(taskDescription));
        String response = HORIZONTAL_LINE + "\n"
                + "Thank god, you should really keep deleting tasks:\n"
                + "- " + task + "\n"
                + HORIZONTAL_LINE + "\n";
        System.out.println(response);
    }
}

