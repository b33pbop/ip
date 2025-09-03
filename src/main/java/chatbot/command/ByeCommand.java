package chatbot.command;

import chatbot.ui.UI;

/**
 * ByeCommand implements the CommandExecutor interface and handles the bye command.
 */
public class ByeCommand implements CommandExecutor {
    private final UI ui;

    public ByeCommand(UI ui) {
        this.ui = ui;
    }

    @Override
    public String execute(String arg) {
        return this.ui.byeResponse();
    }
}
