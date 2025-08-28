package B33PBOP.command;

import B33PBOP.ui.UI;

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
