package B33PBOP.command;

import B33PBOP.exception.BotException;
import B33PBOP.task.Task;
import B33PBOP.task.TaskList;
import B33PBOP.ui.UI;

public class FindTasksCommand implements CommandExecutor {
    private final TaskList TASK_LIST;
    private final B33PBOP.ui.UI UI;

    public FindTasksCommand(TaskList taskList, UI ui) {
        this.TASK_LIST = taskList;
        this.UI = ui;
    }

    private String parseTaskArray(Task[] tasks) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < tasks.length; i++) {
            if (tasks[i] == null) {
                break;
            }
            int idx = i + 1;
            Task curTask = tasks[i];
            String task = idx + "." + curTask + "\n";
            sb.append(task);
        }

        if (sb.isEmpty()) {
            return "Theres nothing";
        }
        return sb.toString();
    }

    @Override
    public boolean execute(String search) throws BotException {
        Task[] matchingTasks = TASK_LIST.findTasks(search);
        UI.showFindTaskResponse(parseTaskArray(matchingTasks));
        return true;
    }
}
