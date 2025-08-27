public class ToDoTask extends Task{
    public ToDoTask(String taskName) {
        super(taskName);
    }

    @Override
    public String printCompleteStatus() {
        return "[T]" + super.printCompleteStatus();
    }
}
