package B33PBOP.command;

import B33PBOP.storage.Storage;
import B33PBOP.task.TaskList;
import B33PBOP.task.Task;
import B33PBOP.exception.BotException;
import B33PBOP.ui.UI;

import java.io.IOException;

public class AddTaskCommand implements CommandExecutor {
    private final TaskList TASK_LIST;
    private final UI UI;
    private final Storage STORAGE;

    public AddTaskCommand(TaskList taskList, UI ui, Storage storage) {
        this.TASK_LIST = taskList;
        this.UI = ui;
        this.STORAGE = storage;
    }

    @Override
    public boolean execute(String taskDescription) throws BotException {
        Task newTask = this.TASK_LIST.addTask(taskDescription);
        UI.showAddTaskResponse(newTask);
        try {
            STORAGE.updateStorage(this.TASK_LIST.getAllTasks());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return true;
    }
}