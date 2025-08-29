package B33PBOP.task;

import B33PBOP.exception.BotException;
import B33PBOP.exception.InvalidArgumentException;

/**
 * Represents an abstract task with a name and a completion status.
 * A Task has a description and can be marked as complete or incomplete.
 * Subclasses of Task must override the toSaveFormat() method.
 */
public abstract class Task {
    private boolean isComplete = false;
    private final String taskName;

    public Task(String taskName) {
        this.taskName = taskName;
    }

    /**
     * Marks task as complete
     * @throws BotException If task is already complete
     */
    public void markTaskComplete() throws BotException {
        if (this.isComplete) {
            throw new InvalidArgumentException("You already marked this as complete...\n");
        }

        this.isComplete = true;
    }

    /**
     * Unmarks task as complete
     * @throws BotException If task is already not marked as complete
     */
    public void unmarkTaskComplete() throws BotException {
        if (!this.isComplete) {
            throw new InvalidArgumentException("This task isn't even complete...\n");
        }

        this.isComplete = false;
    }

    /**
     * Prints complete status of a Task
     * @return A string representing completion status of task
     */
    public String printCompleteStatus() {
        return this.isComplete ? "[X] " : "[ ] ";
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
