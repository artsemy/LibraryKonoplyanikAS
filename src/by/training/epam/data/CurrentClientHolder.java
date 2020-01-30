package by.training.epam.data;

import by.training.epam.bean.Client;

public class CurrentClientHolder {

    private static Client client = new Client("empty", "empty", ClientRole.NO_ONE);

    public static void setClient(Client c) {
        if (c != null) {
            client = c;
        }
    }

    public static ClientRole getRole() {
        return client.getClientRole();
    }

}
