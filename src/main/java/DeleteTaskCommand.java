import java.io.IOException;

public class DeleteTaskCommand implements CommandExecutor {
    private final TaskList TASK_LIST;
    private final UI UI;
    private final Storage STORAGE;

    public DeleteTaskCommand(TaskList taskList, UI ui, Storage storage) {
        this.TASK_LIST = taskList;
        this.UI = ui;
        this.STORAGE = storage;
    }

    @Override
    public boolean execute(String taskDescription) throws BotException {
        Task newTask = this.TASK_LIST.addTask(taskDescription);
        UI.showAddTaskResponse(newTask);
        try {
            STORAGE.updateStorage(TASK_LIST.getAllTasks());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        UI.showDeleteTaskResponse(taskDescription, TASK_LIST);
        return true;
    }
}