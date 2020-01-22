package by.training.epam.service;

import by.training.epam.bean.Client;
import by.training.epam.util.Reader;
import by.training.epam.util.Writer;
import by.training.epam.dao.impl.Group;
import by.training.epam.data.ClientRole;
import by.training.epam.data.ClientRoleHolder;

import java.io.IOException;
import java.util.List;

import static by.training.epam.data.Constant.PATH_TO_USER_FILE;

public class ClientService {

    private Group group;
    private final String REGISTRATION_OK = "Hello newbie";
    private final String REGISTRATION_NOT_OK = "can't register, bad login";
    private final String SIGN_IN_OK = "welcome back";
    private final String SIGN_IN_NOT_OK = "bad login or password";

    public ClientService() throws IOException {
        group = Reader.readFileClient(PATH_TO_USER_FILE);
    }

    private void saveChangeGroup(boolean needUpdate) throws IOException {
        if (needUpdate) {
            Writer.writeFileUser(PATH_TO_USER_FILE, group);
        }
    }

    public String registration(Client client) throws IOException {
        List<Client> clientList = group.getClientList();
        for (Client c: clientList) {
            if (client.getLogin().equals(c.getLogin())) {
                return REGISTRATION_NOT_OK;
            }
        }
        client.setClientRole(ClientRole.USER);
        group.addClient(client);
        saveChangeGroup(true);
        ClientRoleHolder.setRole(ClientRole.USER);
        return REGISTRATION_OK;
    }

    public String signIn(Client client) throws IOException {
        List<Client> clientList = group.getClientList();
        for (Client c: clientList) {
            if (client.equals(c)) {
                ClientRoleHolder.setRole(c.getClientRole());
                return SIGN_IN_OK;
            }
        }
        return SIGN_IN_NOT_OK;
    }

}
