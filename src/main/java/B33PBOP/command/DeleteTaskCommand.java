package B33PBOP.command;

import B33PBOP.exception.BotException;
import B33PBOP.storage.Storage;
import B33PBOP.task.Task;
import B33PBOP.ui.UI;
import B33PBOP.task.TaskList;
import java.io.IOException;

/**
 * DeleteTaskCommand implements the CommandExecutor interface and handles the delete command.
 */
public class DeleteTaskCommand implements CommandExecutor {
    private final TaskList TASK_LIST;
    private final UI UI;
    private final Storage STORAGE;

    /**
     * Constructor, initializes class constant variables.
     * @param taskList List of tasks the user has added.
     * @param ui User interface where the responses to commands are displayed.
     * @param storage Persistent storage for user's tasks
     */
    public DeleteTaskCommand(TaskList taskList, UI ui, Storage storage) {
        this.TASK_LIST = taskList;
        this.UI = ui;
        this.STORAGE = storage;
    }

    @Override
    public boolean execute(String taskDescription) throws BotException {
        Task newTask = this.TASK_LIST.deleteTask(taskDescription);
        UI.showDeleteTaskResponse(newTask);
        try {
            STORAGE.updateStorage(TASK_LIST.getAllTasks());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        return true;
    }
}