package chatbot.ui;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

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
 * B33PBOP is a GUI task management bot.
 * It supports commands to add tasks, delete tasks, list tasks, mark/unmark task completion and exit.
 */
public class B33pbop {
    private final Map<Command, CommandExecutor> commandMap = new HashMap<>();
    private final UI ui;
    private final TaskList myTasks; // List of tasks managed by the bot

    /**
     * Constructor for B33pbop, initializes ui, task list and storage.
     */
    public B33pbop() {
        this.ui = new UI();
        this.myTasks = new TaskList();

        Storage tempStorage;
        try {
            tempStorage = new Storage();
            myTasks.loadTasks(tempStorage.getStorageFile());
        } catch (IOException e) {
            tempStorage = null;
        }
        Storage storage = tempStorage;
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

    private void registerCommands(Storage storage) {
        assert ui != null && myTasks != null : "UI and TaskList must be initialized";

        commandMap.put(Command.BYE, new ByeCommand(ui));
        commandMap.put(Command.LIST, new ListCommand(myTasks, ui));
        commandMap.put(Command.TODO, new AddTaskCommand(myTasks, ui, storage));
        commandMap.put(Command.DEADLINE, new AddTaskCommand(myTasks, ui, storage));
        commandMap.put(Command.EVENT, new AddTaskCommand(myTasks, ui, storage));
        commandMap.put(Command.DELETE, new DeleteTaskCommand(myTasks, ui, storage));
        commandMap.put(Command.MARK, new MarkTaskCommand(myTasks, ui, storage));
        commandMap.put(Command.UNMARK, new UnmarkTaskCommand(myTasks, ui, storage));
        commandMap.put(Command.FIND, new FindTasksCommand(myTasks, ui));
    }

    /**
     * Returns a String of the bot greeting.
     *
     * @return String with a greet message.
     */
    public String getGreeting() {
        return ui.greetResponse();
    }

    /**
     * Return a String representation of the bot response to user commands.
     *
     * @param input User input into the chatbot; must not be null or empty.
     * @return String response based on user input.
     */
    public String getResponse(String input) {
        assert input != null && !input.isEmpty() : "Input must not be null or empty";

        String[] inputParts = input.split(" ", 2);
        String cmdStr = inputParts[0].trim();
        try {
            Command command = parseCommand(cmdStr);
            // Use the commandMap to get the executor
            CommandExecutor executor = commandMap.get(command);
            if (executor == null) {
                return "What even is '" + input + "'?\n";
            }
            return executor.execute(input);

        } catch (BotException e) {
            return ui.runErrorMessage(e.getMessage());
        }
    }

    /**
     * Returns a string command into the corresponding Command enum.
     *
     * @param cmdStr The string representation of the command; must not be null or empty.
     * @return The corresponding enum value of the cmdStr.
     * @throws InvalidCommandException If the string does not match any valid command.
     */
    private static Command parseCommand(String cmdStr) throws InvalidCommandException {
        assert cmdStr != null && !cmdStr.isEmpty() : "Command string cannot be null or empty";
        try {
            return Command.valueOf(cmdStr.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new InvalidCommandException("What even is '" + cmdStr + "'?\n");
        }
    }
}
