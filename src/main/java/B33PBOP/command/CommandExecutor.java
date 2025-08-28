package B33PBOP.command;

import B33PBOP.exception.BotException;

public interface CommandExecutor {
    boolean execute(String arg) throws BotException;
}
