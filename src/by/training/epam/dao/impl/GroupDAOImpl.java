package by.training.epam.dao.impl;

import by.training.epam.bean.Client;
import by.training.epam.dao.GroupDAO;
import by.training.epam.dao.exception.BadFileGroupDAOException;
import by.training.epam.util.Reader;
import by.training.epam.util.Writer;

import java.io.IOException;
import java.util.Set;
import java.util.TreeSet;

import static by.training.epam.data.Constant.*;

public class GroupDAOImpl implements GroupDAO {

    private static GroupDAOImpl singleton;
    private static Set<Client> clientsCache;

    private GroupDAOImpl() throws BadFileGroupDAOException {
        download();
    }

    public static synchronized GroupDAOImpl getInstance() throws BadFileGroupDAOException {
        if (singleton == null) {
            singleton = new GroupDAOImpl();
        }
        return singleton;
    }

    public Set<Client> getClientSet() {
        return clientsCache;
    }

    private void download() throws BadFileGroupDAOException {
        clientsCache = new TreeSet<>();
        try {
            Reader.readFileClient(PATH_TO_USER_FILE);
        } catch (IOException e) {
            throw new BadFileGroupDAOException(MESSAGE_CANT_READ, e);
        }
    }

    private void upload() throws BadFileGroupDAOException {
        try {
            Writer.writeFileUser(PATH_TO_USER_FILE);
        } catch (IOException e) {
            throw new BadFileGroupDAOException(MESSAGE_CANT_WRITE, e);
        }
    }

    @Override
    public boolean addClient(Client client) throws BadFileGroupDAOException {
        if (client != null && !clientsCache.contains(client)) {
            clientsCache.add(client);
            upload();
            return true;
        }
        return false;
    }

}
