public class UI {
    private final String HORIZONTAL_LINE = "_".repeat(75);

    /**
     * Prints the initial greeting message when the bot starts
     */
    public void showGreetResponse() {
        String greetings = HORIZONTAL_LINE + "\n"
                + "I'm B33PBOP...\n"
                + "What do you want?\n"
                + HORIZONTAL_LINE + "\n";
        System.out.println(greetings);
    }

    /**
     * Prints the exit message when the BYE command is executed.
     */
    public void showByeResponse() {
        String response = HORIZONTAL_LINE + "\n"
                + "Please leave me alone\n"
                + HORIZONTAL_LINE + "\n";
        System.out.println(response);
    }

    /**
     * Prints the current list of tasks in a formatted way when the LIST command is executed.
     */
    public void showListResponse(TaskList myTasks) {
        String response = HORIZONTAL_LINE + "\n"
                + myTasks.showTaskList()
                + HORIZONTAL_LINE + "\n";
        System.out.println(response);
    }

    /**
     * Marks a task as complete based on its index.
     */
    public void showMarkTaskCompleteResponse(Task task) {
        String response = HORIZONTAL_LINE + "\n"
                + "Ugh. Can't you do this yourself?\n"
                + task + "\n"
                + HORIZONTAL_LINE + "\n";
        System.out.println(response);
    }

    /**
     * Unmarks a task as complete based on its index.
     */
    public void showUnmarkTaskCompleteResponse(Task task) {
        String response = HORIZONTAL_LINE + "\n"
                + "Make up your mind...\n"
                + task + "\n"
                + HORIZONTAL_LINE + "\n";
        System.out.println(response);
    }

    /**
     * Prints the bot's response when any add task command is executed.
     */
    public void showAddTaskResponse(Task newTask) {
        String response = HORIZONTAL_LINE + "\n"
                + "This will be the last time I'm adding this for you:\n "
                + "+ " + newTask + "\n"
                + HORIZONTAL_LINE + "\n";
        System.out.println(response);
    }

    /**
     * Prints the bot's response when the DELETE command is executed.
     * @param taskDescription Description of the task to be deleted.
     * @throws BotException If the task deletion fails.
     */
    public void showDeleteTaskResponse(String taskDescription, TaskList myTasks) throws BotException {
        Task task = myTasks.deleteTask(Integer.parseInt(taskDescription));
        String response = HORIZONTAL_LINE + "\n"
                + "Thank god, you should really keep deleting tasks:\n"
                + "- " + task + "\n"
                + HORIZONTAL_LINE + "\n";
        System.out.println(response);
    }

    /**
     * Prints the bot's response when there is an error with input
     * @param errorMessage Error message when exception is caught
     */
    public void showRunErrorMessage(String errorMessage) {
        String errorMsg = HORIZONTAL_LINE + "\n"
                + errorMessage
                + HORIZONTAL_LINE + "\n";
        System.out.println(errorMsg);
    }
}
