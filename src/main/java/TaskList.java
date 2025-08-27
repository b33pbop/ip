import java.util.ArrayList;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class TaskList {
    // List of tasks managed by the bot
    private final List<Task> myTasks = new ArrayList<>();

    public Task getTask(int taskId) {
        int taskIdx = taskId - 1;
        return myTasks.get(taskIdx);
    }

    public List<Task> getAllTasks() {
        return this.myTasks;
    }

    /**
     * Creates a new task based on the given task description and adds it to the task list.
     * @param taskDescription Description of the task to be added.
     * @return A new Task object.
     * @throws BotException If the task creation fails.
     */
    public Task addTask(String taskDescription) throws BotException {
        Task newTask = TaskFactory.createTask(taskDescription);
        myTasks.add(newTask);

        return newTask;
    }

    /**
     * Deletes a task based on its ID from the task list.
     * @param taskId 1-based index of the task to delete.
     * @return The deleted Task object.
     * @throws BotException If the task is empty or the taskId is invalid.
     */
    public Task deleteTask(int taskId) throws BotException {
        int taskIdx = taskId - 1;
        if (myTasks.isEmpty()) {
            throw new TaskListIndexOutOfBoundException("Your list is literally empty\n");
        }

        if (taskId > myTasks.size()) {
            throw new InvalidArgumentException("That task don't exist, do you even know what you added??\n");
        } else if (taskId < 1) {
            throw new InvalidArgumentException("Are you drunk? Task " + taskId + "?\n");
        } else {
            return myTasks.remove(taskIdx);
        }
    }

    /**
     * Marks or unmarks a task as complete based on its index.
     * @param taskIdx 1-based index of the task to be marked/unmarked.
     * @throws BotException If index is invalid of the task does not exist.
     */
    public void handleMarkTaskComplete(String taskIdx) throws BotException {
        try {
            int taskIndex = Integer.parseInt(taskIdx) - 1;
            Task task = myTasks.get(taskIndex);
            task.markTaskComplete();
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw new InvalidArgumentException("That task don't even exist\n");
        }
    }

    /**
     * Marks or unmarks a task as complete based on its index.
     * @param taskIdx 1-based index of the task to be marked/unmarked.
     * @throws BotException If index is invalid of the task does not exist.
     */
    public void handleUnmarkTaskComplete(String taskIdx) throws BotException {
        try {
            int taskIndex = Integer.parseInt(taskIdx) - 1;
            Task task = myTasks.get(taskIndex);
            task.unmarkTaskComplete();
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw new InvalidArgumentException("That task don't even exist\n");
        }
    }

    /**
     * Returns a formatted string representation of all tasks in the task list.
     * @return A string that lists all tasks with their indices (1-indexed).
     */
    public String showTaskList() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < myTasks.size(); i++) {
            int idx = i + 1;
            Task curTask = myTasks.get(i);
            String task = idx + "." + curTask + "\n";
            sb.append(task);
        }
        return "You really need help remembering all these?\n" + sb;
    }

    public void loadTasks(File file) throws IOException{
        if (!file.exists()) {
            return;
        }

        try (Scanner sc = new Scanner(file)) {
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                Task task = parseTaskFromStorage(line);
                myTasks.add(task);
            }
        } catch (BotException e) {
            System.out.println(e.getMessage());
        }
    }

    private Task parseTaskFromStorage(String line) throws BotException {
        String[] parts = line.split(" \\| ");
        String type = parts[0].strip();
        boolean isComplete = parts[1].strip().equals("[X]");
        String description = parts[2].strip();

        switch (type) {
        case "T":
            Task todoTask = new ToDoTask(description);
            if (isComplete) {
                todoTask.markTaskComplete();
            }
            return todoTask;

        case "D":
            String deadline = parts[3].strip();
            Task deadlineTask = new DeadlineTask(description, deadline);
            if (isComplete) {
                deadlineTask.markTaskComplete();
            }
            return deadlineTask;

        case "E":
            String dateRange = parts[3].strip();
            String from = dateRange.split("-")[0];
            String to = dateRange.split("-")[1];
            Task eventTask = new EventTask(description, from, to);
            if (isComplete) {
                eventTask.markTaskComplete();
            }
            return eventTask;

        default:
            throw new BotException("Corrupted task in file: " + line);
        }
    }
}
