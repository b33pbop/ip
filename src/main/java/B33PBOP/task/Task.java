package B33PBOP.task;

import B33PBOP.exception.BotException;
import B33PBOP.exception.InvalidArgumentException;

public abstract class Task {
    private boolean completeStatus = false;
    private final String taskName;

    public Task(String taskName) {
        this.taskName = taskName;
    }

    public void markTaskComplete() throws BotException {
        if (this.completeStatus) {
            throw new InvalidArgumentException("You already marked this as complete...\n");
        }

        this.completeStatus = true;
    }

    public void unmarkTaskComplete() throws BotException {
        if (!this.completeStatus) {
            throw new InvalidArgumentException("This task isn't even complete...\n");
        }

        this.completeStatus = false;
    }

    public String printCompleteStatus() {
        return this.completeStatus ? "[X] " : "[ ] ";
    }

    public String getTaskName() {
        return this.taskName;
    }

    public abstract String toSaveFormat();

    @Override
    public String toString() {
        return printCompleteStatus() + getTaskName();
    }
}
