package chatbot.command;

import java.io.IOException;

import chatbot.exception.BotException;
import chatbot.storage.Storage;
import chatbot.task.TaskList;
import chatbot.ui.UI;

/**
 * UnmarkTaskCommand implements the CommandExecutor interface and handles the unmark command.
 */
public class UnmarkTaskCommand implements CommandExecutor {
    private final TaskList taskList;
    private final UI ui;
    private final Storage storage;

    /**
     * Constructor, initializes class constant variables.
     * @param taskList List of tasks the user has added.
     * @param ui User interface where the responses to commands are displayed.
     */
    public UnmarkTaskCommand(TaskList taskList, UI ui, Storage storage) {
        this.taskList = taskList;
        this.ui = ui;
        this.storage = storage;
    }

    @Override
    public String execute(String taskIdx) throws BotException {
        String[] input = taskIdx.split(" ", 2);
        this.taskList.handleUnmarkTaskComplete(input[1]);
        String response;
        if (this.storage != null) {
            try {
                storage.updateStorage(this.taskList.getAllTasks());
            } catch (IOException e) {
                response = "I didn't quite catch that, less work for me I guess";
                return response;
            }
        } else {
            return "I can't find my storage so I basically forgot what you just said";
        }
        response = ui.unmarkTaskCompleteResponse(this.taskList.getTask(Integer.parseInt(input[1])));
        return response;
    }
}
