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
     *
     * @param taskList List of tasks the user has added; must not be null.
     * @param ui User interface where the responses to commands are displayed; must not be null.
     */
    public UnmarkTaskCommand(TaskList taskList, UI ui, Storage storage) {
        assert taskList != null : "TaskList must not be null";
        assert ui != null : "UI must not be null";
        this.taskList = taskList;
        this.ui = ui;
        this.storage = storage; // Storage might be null (problem is handled later in the code), no assertions needed
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
