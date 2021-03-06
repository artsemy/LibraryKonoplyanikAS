package by.training.epam.dao;

import by.training.epam.bean.Client;
import by.training.epam.dao.exception.BadFileGroupDAOException;

public interface GroupDAO {

    boolean register(Client client) throws BadFileGroupDAOException;

    boolean signIn(Client client);

}
