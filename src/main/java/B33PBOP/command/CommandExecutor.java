package B33PBOP.command;

import B33PBOP.exception.BotException;

/**
 * CommandExecutor is an interface for commands that B33PBOP takes.
 */
public interface CommandExecutor {
    /**
     * Executes the user commands
     * @param taskDescription User input for the task.
     * @return Returns a boolean, true when command is non-terminating, false when command is terminating (BYE command).
     * @throws BotException If there is an error with user input.
     */
    boolean execute(String taskDescription) throws BotException;
}
