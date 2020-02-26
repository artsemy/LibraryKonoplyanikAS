package by.training.epam.dao;

import by.training.epam.bean.Client;
import by.training.epam.service.ClientRole;

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
