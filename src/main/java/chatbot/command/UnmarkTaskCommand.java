package chatbot.command;

import chatbot.exception.BotException;
import chatbot.task.TaskList;
import chatbot.ui.UI;

/**
 * UnmarkTaskCommand implements the CommandExecutor interface and handles the unmark command.
 */
public class UnmarkTaskCommand implements CommandExecutor {
    private final TaskList taskList;
    private final UI ui;

    /**
     * Constructor, initializes class constant variables.
     * @param taskList List of tasks the user has added.
     * @param ui User interface where the responses to commands are displayed.
     */
    public UnmarkTaskCommand(TaskList taskList, UI ui) {
        this.taskList = taskList;
        this.ui = ui;
    }

    @Override
    public boolean execute(String taskIdx) throws BotException {
        this.taskList.handleUnmarkTaskComplete(taskIdx);
        ui.showUnmarkTaskCompleteResponse(this.taskList.getTask(Integer.parseInt(taskIdx)));
        return true;
    }
}
