package B33PBOP.command;

import B33PBOP.task.TaskList;
import B33PBOP.ui.UI;

/**
 * ListCommand implements the CommandExecutor interface and handles the list command.
 */
public class ListCommand implements CommandExecutor {
    private final TaskList TASK_LIST;
    private final UI UI;

    /**
     * Constructor, initializes class constant variables.
     * @param taskList List of tasks the user has added.
     * @param ui User interface where the responses to commands are displayed.
     */
    public ListCommand(TaskList taskList, UI ui) {
        this.TASK_LIST = taskList;
        this.UI = ui;
    }

    @Override
    public boolean execute(String arg) {
        UI.showListResponse(this.TASK_LIST);
        return true;
    }
}
