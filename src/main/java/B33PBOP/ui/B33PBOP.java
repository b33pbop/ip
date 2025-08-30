package B33PBOP.ui;

import B33PBOP.command.*;
import B33PBOP.exception.BotException;
import B33PBOP.exception.InvalidCommandException;
import B33PBOP.storage.Storage;
import B33PBOP.task.TaskList;

import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Map;

/**
 * B33PBOP.ui.B33PBOP is a command-line task management bot.
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
        FIND,
        BYE
    }
    private final Map<Command, CommandExecutor> COMMAND_MAP = new HashMap<>();
    private final UI UI;
    private final TaskList MY_TASKS; // List of tasks managed by the bot

    /**
     * Main entry point of B33PBOP.ui.B33PBOP.
     * Prints greetings and starts command processing.
     * @param args Command-line arguments
     */
    public static void main(String[] args) {
        new B33PBOP().start();
    }

    public B33PBOP() {
        this.UI = new UI();
        this.MY_TASKS = new TaskList();

        Storage storage = null;
        try {
            storage = new Storage();
            MY_TASKS.loadTasks(storage.getStorageFile());
        } catch (IOException e) {
            System.out.println("Error initializing storage: " + e.getMessage());
        }

        registerCommands(storage);
    }

    private void registerCommands(Storage storage) {
        COMMAND_MAP.put(Command.BYE, new ByeCommand(UI));
        COMMAND_MAP.put(Command.LIST, new ListCommand(MY_TASKS, UI));
        COMMAND_MAP.put(Command.TODO, new AddTaskCommand(MY_TASKS, UI, storage));
        COMMAND_MAP.put(Command.DEADLINE, new AddTaskCommand(MY_TASKS, UI, storage));
        COMMAND_MAP.put(Command.EVENT, new AddTaskCommand(MY_TASKS, UI, storage));
        COMMAND_MAP.put(Command.DELETE, new DeleteTaskCommand(MY_TASKS, UI, storage));
        COMMAND_MAP.put(Command.MARK, new MarkTaskCommand(MY_TASKS, UI));
        COMMAND_MAP.put(Command.UNMARK, new UnmarkTaskCommand(MY_TASKS, UI));
        COMMAND_MAP.put(Command.FIND, new FindTasksCommand(MY_TASKS, UI));
    }
    
    public void start() {
        this.UI.showGreetResponse();
        runCommand();
    }
    
    /**
     * Reads and processes user commands in a loop until the BYE command is entered.
     * Commands are parsed and delegated to corresponding response methods.
     */
    public void runCommand() {
        Scanner sc = new Scanner(System.in);
        boolean isExit = true;
        while (isExit) {
            String input = sc.nextLine().trim();
            String[] inputParts = input.split(" ", 2);
            String cmdStr = inputParts[0].trim();

            try {
                Command command = parseCommand(cmdStr);
                // Use the COMMAND_MAP to get the executor
                CommandExecutor executor = COMMAND_MAP.get(command);
                if (executor != null) {
                    isExit = executor.execute(input);
                } else {
                    throw new InvalidCommandException("What even is '" + input + "'?\n");
                }
            } catch (BotException e) {
                UI.showRunErrorMessage(e.getMessage());
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
}

