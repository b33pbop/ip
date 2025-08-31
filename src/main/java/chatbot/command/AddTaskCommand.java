package chatbot.command;

import java.io.IOException;

import chatbot.exception.BotException;
import chatbot.storage.Storage;
import chatbot.task.Task;
import chatbot.task.TaskList;
import chatbot.ui.UI;


/**
 * AddTaskCommand implements the CommandExecutor interface and handles To Do, Deadline and Event commands.
 */
public class AddTaskCommand implements CommandExecutor {
    private final TaskList taskList;
    private final UI ui;
    private final Storage storage;

    /**
     * Constructor, initializes class variables.
     * @param taskList List of tasks the user has added.
     * @param ui User interface where the responses to commands are displayed.
     * @param storage Persistent storage for user's tasks
     */
    public AddTaskCommand(TaskList taskList, UI ui, Storage storage) {
        this.taskList = taskList;
        this.ui = ui;
        this.storage = storage;
    }

    @Override
    public boolean execute(String taskDescription) throws BotException {
        Task newTask = this.taskList.addTask(taskDescription);
        ui.showAddTaskResponse(newTask);
        try {
            storage.updateStorage(this.taskList.getAllTasks());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return true;
    }
}
