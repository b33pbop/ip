package chatbot.command;

import chatbot.exception.BotException;
import chatbot.task.TaskList;
import chatbot.ui.UI;

/**
 * MarkTaskCommand implements the CommandExecutor interface and handles the mark command.
 */
public class MarkTaskCommand implements CommandExecutor {
    private final TaskList taskList;
    private final UI ui;

    /**
     * Constructor, initializes class constant variables.
     * @param taskList List of tasks the user has added.
     * @param ui User interface where the responses to commands are displayed.
     */
    public MarkTaskCommand(TaskList taskList, UI ui) {
        this.taskList = taskList;
        this.ui = ui;
    }

    @Override
    public boolean execute(String taskIdx) throws BotException {
        this.taskList.handleMarkTaskComplete(taskIdx);
        ui.showMarkTaskCompleteResponse(this.taskList.getTask(Integer.parseInt(taskIdx)));
        return true;
    }
}
