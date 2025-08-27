import java.util.Scanner;

/**
 * B33PBOP is a command-line task management bot.
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
    private static final TaskList myTasks = new TaskList();
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
                    myTasks.handleMarkTaskComplete(arg);
                    markTaskCompleteResponse(myTasks.getTask(Integer.parseInt(arg)));
                    break;

                case UNMARK:
                    myTasks.handleUnmarkTaskComplete(arg);
                    unmarkTaskCompleteResponse(myTasks.getTask(Integer.parseInt(arg)));
                    break;

                case TODO:
                case DEADLINE:
                case EVENT:
                    addTaskResponse(input);
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
                + myTasks.showTaskList()
                + HORIZONTAL_LINE + "\n";
        System.out.println(response);
    }

    /**
     * Marks a task as complete based on its index.
     */
    public static void markTaskCompleteResponse(Task task) {
        String response = HORIZONTAL_LINE + "\n"
                + "Ugh. Can't you do this yourself?\n"
                + task + "\n"
                + HORIZONTAL_LINE + "\n";
        System.out.println(response);
    }

    /**
     * Unmarks a task as complete based on its index.
     */
    public static void unmarkTaskCompleteResponse(Task task) {
        String response = HORIZONTAL_LINE + "\n"
                + "Make up your mind...\n"
                + task + "\n"
                + HORIZONTAL_LINE + "\n";
        System.out.println(response);
    }

    /**
     * Prints the bot's response when any add task command is executed.
     * @param taskDescription Description of the task to be added.
     * @throws BotException If the task creation fails.
     */
    public static void addTaskResponse(String taskDescription) throws BotException {
        Task newTask = myTasks.addTask(taskDescription);
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
        Task task = myTasks.deleteTask(Integer.parseInt(taskDescription));
        String response = HORIZONTAL_LINE + "\n"
                + "Thank god, you should really keep deleting tasks:\n"
                + "- " + task + "\n"
                + HORIZONTAL_LINE + "\n";
        System.out.println(response);
    }
}

