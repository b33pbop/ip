package chatbot.ui;

import chatbot.task.Task;
import chatbot.task.TaskList;

/**
 * UI class handles communication between B33PBOP's functionality and user interface.
 */
public class UI {
    /**
     * Prints the initial greeting message when the bot starts
     */
    public String greetResponse() {
        return """
                I'm B33PBOP
                What do you want?
                """;
    }

    /**
     * Prints the exit message when the BYE command is executed.
     */
    public String byeResponse() {
        return "Please leave me alone\n";
    }

    /**
     * Prints the current list of tasks in a formatted way when the LIST command is executed.
     */
    public String listResponse(TaskList myTasks) {
        return myTasks.showTaskList();
    }

    /**
     * Marks a task as complete based on its index.
     */
    public String markTaskCompleteResponse(Task task) {
        return "Ugh. Can't you do this yourself?\n"
                + task + "\n";
    }

    /**
     * Unmarks a task as complete based on its index.
     */
    public String unmarkTaskCompleteResponse(Task task) {
        return "Make up your mind...\n"
                + task + "\n";
    }

    /**
     * Prints the bot's response when any add task command is executed.
     */
    public String addTaskResponse(Task newTask) {
        return "This will be the last time I'm adding this for you:\n "
                + "+ " + newTask + "\n";
    }

    /**
     * Prints the bot's response when the DELETE command is executed.
     */
    public String deleteTaskResponse(Task task) {
        return "Thank god, you should really keep deleting tasks:\n"
                + "- " + task + "\n";
    }

    /**
     * Prints the bot's response when the FIND command is executed.
     * @param foundTasksString Tasks that matches keyword of user input in String format.
     */
    public String findTaskResponse(String foundTasksString) {
        return "You are really bossy you know that\n"
                + foundTasksString + "\n";
    }

    /**
     * Prints the bot's response when there is an error with input
     * @param errorMessage Error message when exception is caught
     */
    public String runErrorMessage(String errorMessage) {
        return errorMessage;
    }
}
