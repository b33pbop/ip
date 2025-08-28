package B33PBOP.task;

public class ToDoTask extends Task{
    public ToDoTask(String taskName) {
        super(taskName);
    }

    @Override
    public String printCompleteStatus() {
        return "[T]" + super.printCompleteStatus();
    }

    @Override
    public String toSaveFormat() {
        return "T | "
                + super.printCompleteStatus() + "| "
                + getTaskName();
    }
}
