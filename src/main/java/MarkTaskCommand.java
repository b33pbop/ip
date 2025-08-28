public class MarkTaskCommand implements CommandExecutor {
    private final TaskList TASK_LIST;
    private final UI UI;

    public MarkTaskCommand(TaskList taskList, UI ui) {
        this.TASK_LIST = taskList;
        this.UI = ui;
    }

    @Override
    public boolean execute(String taskIdx) throws BotException {
        this.TASK_LIST.handleMarkTaskComplete(taskIdx);
        UI.showMarkTaskCompleteResponse(this.TASK_LIST.getTask(Integer.parseInt(taskIdx)));
        return true;
    }
}
