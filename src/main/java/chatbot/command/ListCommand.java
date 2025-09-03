package chatbot.command;

import chatbot.task.TaskList;
import chatbot.ui.UI;

/**
 * ListCommand implements the CommandExecutor interface and handles the list command.
 */
public class ListCommand implements CommandExecutor {
    private final TaskList taskList;
    private final UI ui;

    /**
     * Constructor, initializes class constant variables.
     * @param taskList List of tasks the user has added.
     * @param ui User interface where the responses to commands are displayed.
     */
    public ListCommand(TaskList taskList, UI ui) {
        this.taskList = taskList;
        this.ui = ui;
    }

    @Override
    public String execute(String arg) {
        return ui.listResponse(this.taskList);
    }
}
