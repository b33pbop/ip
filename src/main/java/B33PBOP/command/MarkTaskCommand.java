package B33PBOP.command;

import B33PBOP.exception.BotException;
import B33PBOP.task.TaskList;
import B33PBOP.ui.UI;

/**
 * MarkTaskCommand implements the CommandExecutor interface and handles the mark command.
 */
public class MarkTaskCommand implements CommandExecutor {
    private final TaskList TASK_LIST;
    private final UI UI;

    /**
     * Constructor, initializes class constant variables.
     * @param taskList List of tasks the user has added.
     * @param ui User interface where the responses to commands are displayed.
     */
    public MarkTaskCommand(TaskList taskList, UI ui) {
        this.TASK_LIST = taskList;
        this.UI = ui;
    }

    @Override
    public boolean execute(String taskIdx) throws BotException {
        this.TASK_LIST.handleMarkTaskComplete(taskIdx);
        UI.showMarkTaskCompleteResponse(this.TASK_LIST.getTask(Integer.parseInt(taskIdx)));
        return true;
    }
}
