package chatbot.ui;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import chatbot.command.AddTaskCommand;
import chatbot.command.ByeCommand;
import chatbot.command.CommandExecutor;
import chatbot.command.DeleteTaskCommand;
import chatbot.command.FindTasksCommand;
import chatbot.command.ListCommand;
import chatbot.command.MarkTaskCommand;
import chatbot.command.UnmarkTaskCommand;
import chatbot.exception.BotException;
import chatbot.exception.InvalidCommandException;
import chatbot.storage.Storage;
import chatbot.task.TaskList;




/**
 * B33PBOP is a command-line task management bot.
 * It supports commands to add tasks, delete tasks, list tasks, mark/unmark task completion and exit.
 */
public class B33pbop {
    private final Map<Command, CommandExecutor> commandMap = new HashMap<>();
    private final UI ui;
    private final TaskList myTasks; // List of tasks managed by the bot

    /**
     * Constructor for B33Pbop, initializes ui, task list and storage
     */
    public B33pbop() {
        this.ui = new UI();
        this.myTasks = new TaskList();

        Storage storage = null;
        try {
            storage = new Storage();
            myTasks.loadTasks(storage.getStorageFile());
        } catch (IOException e) {
            System.out.println("Error initializing storage: " + e.getMessage());
        }

        registerCommands(storage);
    }
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
    /**
     * Main entry point of B33PBOP.
     * Prints greetings and starts command processing.
     * @param args Command-line arguments
     */
    public static void main(String[] args) {
        new B33pbop().start();
    }

    private void registerCommands(Storage storage) {
        commandMap.put(Command.BYE, new ByeCommand(ui));
        commandMap.put(Command.LIST, new ListCommand(myTasks, ui));
        commandMap.put(Command.TODO, new AddTaskCommand(myTasks, ui, storage));
        commandMap.put(Command.DEADLINE, new AddTaskCommand(myTasks, ui, storage));
        commandMap.put(Command.EVENT, new AddTaskCommand(myTasks, ui, storage));
        commandMap.put(Command.DELETE, new DeleteTaskCommand(myTasks, ui, storage));
        commandMap.put(Command.MARK, new MarkTaskCommand(myTasks, ui));
        commandMap.put(Command.UNMARK, new UnmarkTaskCommand(myTasks, ui));
        commandMap.put(Command.FIND, new FindTasksCommand(myTasks, ui));
    }

    /**
     * Starts the bot
     */
    public void start() {
        this.ui.showGreetResponse();
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
                // Use the commandMap to get the executor
                CommandExecutor executor = commandMap.get(command);
                if (executor != null) {
                    isExit = executor.execute(input);
                } else {
                    throw new InvalidCommandException("What even is '" + input + "'?\n");
                }
            } catch (BotException e) {
                ui.showRunErrorMessage(e.getMessage());
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
