package by.training.epam.dao.impl;

import by.training.epam.bean.Client;
import by.training.epam.dao.GroupDAO;

import java.util.ArrayList;

public class GroupDAOImpl implements GroupDAO {

    private ArrayList<Client> clientList;

    public GroupDAOImpl() {
        clientList = new ArrayList<>();
    }

    public GroupDAOImpl(ArrayList<Client> clientList) {
        this.clientList = clientList;
    }

    public ArrayList<Client> getClientList() {
        return clientList;
    }

    public void setClientList(ArrayList<Client> clientList) {
        this.clientList = clientList;
    }

    @Override
    public boolean addClient(Client client) {
        if (client != null && !clientList.contains(client)) {
            clientList.add(client);
            return true;
        }
        return false;
    }

}
