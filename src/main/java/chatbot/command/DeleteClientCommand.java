package chatbot.command;

import chatbot.client.Client;
import chatbot.client.ClientList;
import chatbot.exception.BotException;

/**
 * DeleteClientCommand implements the CommandExecutor interface and handles client delete command.
 */
public class DeleteClientCommand implements CommandExecutor {
    private final ClientList clientList;

    public DeleteClientCommand(ClientList clientList) {
        this.clientList = clientList;
    }

    @Override
    public String execute(String taskDescription) throws BotException {
        try {
            int clientId = Integer.parseInt(taskDescription.trim());
            Client deleted = clientList.deleteClient(clientId);
            return "Deleted client: " + deleted;
        } catch (NumberFormatException e) {
            throw new BotException("Usage: client delete <clientId>");
        } catch (IndexOutOfBoundsException e) {
            throw new BotException("Client ID out of range.");
        }
    }
}
