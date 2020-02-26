package by.training.epam.dao.impl;

import by.training.epam.bean.Client;
import by.training.epam.dao.GroupDAO;
import by.training.epam.dao.exception.BadFileGroupDAOException;
import by.training.epam.data.ClientRole;
import by.training.epam.data.CurrentClientHolder;
import by.training.epam.source.ClientSource;
import by.training.epam.source.impl.ClientSourceImpl;

import java.io.IOException;
import java.util.TreeSet;

public class GroupDAOImpl implements GroupDAO {

    private static ClientSource source;
    private static final TreeSet<Client> clientsCache = new TreeSet<>();

    public GroupDAOImpl() throws BadFileGroupDAOException {
        source = ClientSourceImpl.getInstance();
        download();
    }

    @Override
    public boolean register(Client client) throws BadFileGroupDAOException {
        if (client != null && !clientsCache.contains(client)) {
            client.setClientRole(ClientRole.USER); //here?
            clientsCache.add(client);
            upload();
            setCurrentClient(client);
            return true;
        }
        return false;
    }

    @Override
    public boolean signIn(Client client) {
        if (clientsCache.contains(client)) {
            Client c = clientsCache.ceiling(client);
            if (c != null) {
                setCurrentClient(c);
                return c.getPassword().equals(client.getPassword());
            }
        }
        return false;
    }

    private void setCurrentClient(Client client) {
        CurrentClientHolder.setClient(client);
    }

    private void download() throws BadFileGroupDAOException {
        try {
            clientsCache.addAll(source.read());
        } catch (IOException e) {
            throw new BadFileGroupDAOException("can't read file", e);
        }
    }

    private void upload() throws BadFileGroupDAOException {
        try {
            source.write(clientsCache);
        } catch (IOException e) {
            throw new BadFileGroupDAOException("can't write file", e);
        }
    }

}
