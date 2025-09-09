package chatbot.command;

import chatbot.client.Client;
import chatbot.client.ClientList;
import chatbot.exception.BotException;

/**
 * AdcClientCommand implements the CommandExecutor Interface and handles client add command
 */
public class AddClientCommand implements CommandExecutor {
    private final ClientList clientList;

    public AddClientCommand(ClientList clientList) {
        this.clientList = clientList;
    }

    @Override
    public String execute(String taskDescription) throws BotException {
        try {
            // Expect format: <name> <mobileNumber> <lastContactedDate>
            String[] parts = taskDescription.trim().split("\\s+", 3);
            if (parts.length < 3) {
                String errorMessage = "Usage: client add <name> <mobileNumber> <lastContactedDate>\n"
                        + "E.g. client add Alice 98765432 2025-09-09";
                throw new BotException(errorMessage);
            }

            String name = parts[0];
            String mobile = parts[1];
            String lastContacted = parts[2];

            Client newClient = clientList.addClient(name, mobile, lastContacted);
            return "Added new client: " + newClient;
        } catch (Exception e) {
            throw new BotException("Failed to add client: " + e.getMessage());
        }
    }
}
