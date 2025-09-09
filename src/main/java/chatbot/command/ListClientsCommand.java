package chatbot.command;

import chatbot.client.ClientList;

/**
 * ListClientsCommand implements the CommandExecutor interface and handles client list command.
 */
public class ListClientsCommand implements CommandExecutor {
    private final ClientList clientList;

    public ListClientsCommand(ClientList clientList) {
        this.clientList = clientList;
    }

    @Override
    public String execute(String taskDescription) {
        if (clientList.size() == 0) {
            return "No clients added yet.";
        }
        return clientList.toString();
    }
}
