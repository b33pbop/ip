package chatbot.ui;

import chatbot.task.Task;
import chatbot.task.TaskList;

/**
 * UI class handles communication between B33PBOP's functionality and user interface.
 */
public class UI {
    private final String horizontalLine = "_".repeat(75);

    /**
     * Prints the initial greeting message when the bot starts
     */
    public void showGreetResponse() {
        String greetings = horizontalLine + "\n"
                + "I'm B33PBOP\n"
                + "What do you want?\n"
                + horizontalLine + "\n";
        System.out.println(greetings);
    }

    /**
     * Prints the exit message when the BYE command is executed.
     */
    public void showByeResponse() {
        String response = horizontalLine + "\n"
                + "Please leave me alone\n"
                + horizontalLine + "\n";
        System.out.println(response);
    }

    /**
     * Prints the current list of tasks in a formatted way when the LIST command is executed.
     */
    public void showListResponse(TaskList myTasks) {
        String response = horizontalLine + "\n"
                + myTasks.showTaskList()
                + horizontalLine + "\n";
        System.out.println(response);
    }

    /**
     * Marks a task as complete based on its index.
     */
    public void showMarkTaskCompleteResponse(Task task) {
        String response = horizontalLine + "\n"
                + "Ugh. Can't you do this yourself?\n"
                + task + "\n"
                + horizontalLine + "\n";
        System.out.println(response);
    }

    /**
     * Unmarks a task as complete based on its index.
     */
    public void showUnmarkTaskCompleteResponse(Task task) {
        String response = horizontalLine + "\n"
                + "Make up your mind...\n"
                + task + "\n"
                + horizontalLine + "\n";
        System.out.println(response);
    }

    /**
     * Prints the bot's response when any add task command is executed.
     */
    public void showAddTaskResponse(Task newTask) {
        String response = horizontalLine + "\n"
                + "This will be the last time I'm adding this for you:\n "
                + "+ " + newTask + "\n"
                + horizontalLine + "\n";
        System.out.println(response);
    }

    /**
     * Prints the bot's response when the DELETE command is executed.
     */
    public void showDeleteTaskResponse(Task task) {
        String response = horizontalLine + "\n"
                + "Thank god, you should really keep deleting tasks:\n"
                + "- " + task + "\n"
                + horizontalLine + "\n";
        System.out.println(response);
    }

    /**
     * Prints the bot's response when the FIND command is executed.
     * @param foundTasksString Tasks that matches keyword of user input in String format.
     */
    public void showFindTaskResponse(String foundTasksString) {
        String response = horizontalLine + "\n"
                + "You are really bossy you know that\n"
                + foundTasksString + "\n"
                + horizontalLine + "\n";
        System.out.println(response);
    }

    /**
     * Prints the bot's response when there is an error with input
     * @param errorMessage Error message when exception is caught
     */
    public void showRunErrorMessage(String errorMessage) {
        String errorMsg = horizontalLine + "\n"
                + errorMessage
                + horizontalLine + "\n";
        System.out.println(errorMsg);
    }
}
