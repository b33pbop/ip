package B33PBOP.command;

import B33PBOP.ui.UI;

/**
 * ByeCommand implements the CommandExecutor interface and handles the bye command.
 */
public class ByeCommand implements CommandExecutor {
    private final UI UI;

    public ByeCommand(UI ui) {
        this.UI = ui;
    }

    @Override
    public boolean execute(String arg) {
        this.UI.showByeResponse();
        return false;
    }
}
