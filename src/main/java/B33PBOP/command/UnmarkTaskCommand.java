package B33PBOP.command;

import B33PBOP.exception.BotException;
import B33PBOP.ui.UI;
import B33PBOP.task.TaskList;

public class UnmarkTaskCommand implements CommandExecutor {
    private final TaskList TASK_LIST;
    private final UI UI;

    public UnmarkTaskCommand(TaskList taskList, UI ui) {
        this.TASK_LIST = taskList;
        this.UI = ui;
    }

    @Override
    public boolean execute(String taskIdx) throws BotException {
        this.TASK_LIST.handleUnmarkTaskComplete(taskIdx);
        UI.showUnmarkTaskCompleteResponse(this.TASK_LIST.getTask(Integer.parseInt(taskIdx)));
        return true;
    }
}
