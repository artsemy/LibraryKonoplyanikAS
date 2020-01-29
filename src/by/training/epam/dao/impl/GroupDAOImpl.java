package by.training.epam.dao.impl;

import by.training.epam.bean.Client;
import by.training.epam.dao.GroupDAO;
import by.training.epam.dao.exception.BadFileGroupDAOException;
import by.training.epam.source.ClientSource;
import by.training.epam.source.impl.ClientSourceImpl;

import java.io.IOException;
import java.util.Set;
import java.util.TreeSet;

import static by.training.epam.data.Constant.*;

public class GroupDAOImpl implements GroupDAO {

    private static GroupDAOImpl singleton;
    private static ClientSource source;
    private static final Set<Client> clientsCache = new TreeSet<>();

    private GroupDAOImpl() throws BadFileGroupDAOException {
        source = ClientSourceImpl.getInstance();
        download();
    }

    public static synchronized GroupDAOImpl getInstance() throws BadFileGroupDAOException {
        if (singleton == null) {
            singleton = new GroupDAOImpl();
        }
        return singleton;
    }

    @Override
    public boolean registration(Client client) throws BadFileGroupDAOException {
        if (client != null && !clientsCache.contains(client)) {
            clientsCache.add(client);
            upload();
            return true;
        }
        return false;
    }

    @Override
    public boolean signIn(Client client) {
        return clientsCache.contains(client);
    }

    private void download() throws BadFileGroupDAOException {
        try {
            clientsCache.addAll(source.read());
        } catch (IOException e) {
            throw new BadFileGroupDAOException(MESSAGE_CANT_READ, e);
        }
    }

    private void upload() throws BadFileGroupDAOException {
        try {
            source.write(clientsCache);
        } catch (IOException e) {
            throw new BadFileGroupDAOException(MESSAGE_CANT_WRITE, e);
        }
    }

}
