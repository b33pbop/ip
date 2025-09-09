package chatbot.client;

import java.util.ArrayList;
import java.util.List;

/**
 * ClientList handles temporary storage of clients the user added.
 * It handles client addition, deletion, updating mobile number,
 * updating last contacted date, storing and loading Client from ClientStorage.
 */
public class ClientList {
    private final List<Client> myClients = new ArrayList<>();

    /**
     * Returns a Client given its ID (1-based index).
     */
    public Client getClient(int clientId) {
        assert clientId > 0 && clientId <= myClients.size() : "Client ID out of bounds";
        int clientIdx = clientId - 1;
        return myClients.get(clientIdx);
    }

    public List<Client> getAllClients() {
        return this.myClients;
    }

    /**
     * Adds a new Client.
     * @param name Name of the client.
     * @param mobileNumber Mobile number of the client.
     * @param lastContactedDate Last contacted date of the client.
     * @return The newly added Client.
     */
    public Client addClient(String name, String mobileNumber, String lastContactedDate) {
        Client newClient = new Client(name, mobileNumber, lastContactedDate);
        myClients.add(newClient);
        return newClient;
    }

    /**
     * Deletes a client given its ID.
     * @param clientId 1-based index of the client.
     * @return The deleted Client.
     */
    public Client deleteClient(int clientId) {
        assert clientId > 0 && clientId <= myClients.size() : "Client ID out of bounds";
        int clientIdx = clientId - 1;
        return myClients.remove(clientIdx);
    }

    /**
     * Updates the mobile number of a client.
     * @param clientId ID of the client.
     * @param newMobileNumber New mobile number.
     * @return True if update was successful.
     */
    public boolean updateMobileNumber(int clientId, String newMobileNumber) {
        Client client = getClient(clientId);
        return client.updateMobileNumber(newMobileNumber);
    }

    /**
     * Updates the last contacted date of a client.
     * @param clientId ID of the client.
     * @param newLastContactedDate New last contacted date.
     * @return True if update was successful.
     */
    public boolean updateLastContactedDate(int clientId, String newLastContactedDate) {
        Client client = getClient(clientId);
        return client.updateLastContactedDate(newLastContactedDate);
    }

    /**
     * Returns the number of clients.
     */
    public int size() {
        return myClients.size();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Client List:\n");
        for (int i = 0; i < myClients.size(); i++) {
            sb.append(i + 1).append(". ").append(myClients.get(i)).append("\n");
        }
        return sb.toString();
    }
}
