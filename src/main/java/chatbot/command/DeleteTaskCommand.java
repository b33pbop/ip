package chatbot.command;

import java.io.IOException;

import chatbot.exception.BotException;
import chatbot.storage.Storage;
import chatbot.task.Task;
import chatbot.task.TaskList;
import chatbot.ui.UI;

/**
 * DeleteTaskCommand implements the CommandExecutor interface and handles the delete command.
 */
public class DeleteTaskCommand implements CommandExecutor {
    private final TaskList taskList;
    private final UI ui;
    private final Storage storage;

    /**
     * Constructor, initializes class variables.
     * @param taskList List of tasks the user has added.
     * @param ui User interface where the responses to commands are displayed.
     * @param storage Persistent storage for user's tasks
     */
    public DeleteTaskCommand(TaskList taskList, UI ui, Storage storage) {
        this.taskList = taskList;
        this.ui = ui;
        this.storage = storage;
    }

    @Override
    public String execute(String taskDescription) throws BotException {
        Task newTask = this.taskList.deleteTask(taskDescription);
        String response;

        if (this.storage == null) {
            return "I can't find my storage so I basically forgot what you just said";
        }

        try {
            storage.updateStorage(taskList.getAllTasks());
        } catch (IOException e) {
            response = "I didn't quite catch that, less work for me I guess";
            return response;
        }

        response = ui.deleteTaskResponse(newTask);
        return response;
    }
}
