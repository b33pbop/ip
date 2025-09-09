package chatbot.command;

import chatbot.client.ClientList;
import chatbot.exception.BotException;

/**
 * UpdateClientCommand implements the CommandExecutor interface and handles client update command.
 */
public class UpdateClientCommand implements CommandExecutor {
    private final ClientList clientList;

    public UpdateClientCommand(ClientList clientList) {
        this.clientList = clientList;
    }

    @Override
    public String execute(String taskDescription) throws BotException {
        // Expect format: <clientId> <prefix1> <value1> [<prefix2> <value2> ...]
        String[] parts = taskDescription.trim().split("\\s+");
        if (parts.length < 3 || parts.length % 2 == 0) {
            throw new BotException(
                    "Usage: client update <clientId> [/m <newMobile>] [/d <newDate>] (you can use both prefixes)");
        }

        int clientId;
        try {
            clientId = Integer.parseInt(parts[0]);
        } catch (NumberFormatException e) {
            throw new BotException("Client ID must be a number.");
        }

        boolean updated = false;
        for (int i = 1; i < parts.length; i += 2) {
            String prefix = parts[i];
            String value = parts[i + 1];

            switch (prefix.toLowerCase()) {
            case "/m":
                updated |= clientList.updateMobileNumber(clientId, value);
                break;
            case "/d":
                updated |= clientList.updateLastContactedDate(clientId, value);
                break;
            default:
                throw new BotException(
                        "Unknown prefix: " + prefix + ". Use /m for mobile, /d for date.");
            }
        }

        return updated ? "Client updated successfully." : "No changes made.";
    }
}
