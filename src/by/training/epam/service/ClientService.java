package by.training.epam.service;

import by.training.epam.bean.Client;
import by.training.epam.dao.Reader;
import by.training.epam.dao.Writer;
import by.training.epam.dao.impl.Group;
import by.training.epam.data.ClientRole;
import by.training.epam.data.ClientRoleHolder;
import by.training.epam.data.Constant;

import java.io.IOException;
import java.util.List;

public class ClientService {

    private static Group group;

    private static void initGroup() throws IOException {
        String pathFile = Constant.pathUserFile;
        group = Reader.readFileClient(pathFile);
    }


    private static void saveChangeGroup(boolean b) throws IOException {
        if (b) {
            String path = Constant.pathUserFile;
            Writer.writeFileUser(path, group);
        }
    }

    public static String registration(Client client) throws IOException {
        initGroup();
        List<Client> clientList = group.getClientList();
        for (Client c: clientList) {
            if (client.getLogin().equals(c.getLogin())) {
                return "can't register, bad login";
            }
        }
        client.setClientRole(ClientRole.USER);
        group.addClient(client);
        saveChangeGroup(true);
        ClientRoleHolder.setRole(ClientRole.USER);
        return "Hello newbie";
    }

    public static String signIn(Client client) throws IOException {
        initGroup();
        List<Client> clientList = group.getClientList();
        for (Client c: clientList) {
            if (client.equals(c)) {
                ClientRoleHolder.setRole(c.getClientRole());
                return "welcome back";
            }
        }
        return "bad login or password";
    }

}
