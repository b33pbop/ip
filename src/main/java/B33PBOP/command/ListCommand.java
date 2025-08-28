package B33PBOP.command;

import B33PBOP.ui.UI;
import B33PBOP.task.TaskList;

public class ListCommand implements CommandExecutor {
    private final TaskList TASK_LIST;
    private final UI UI;

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
