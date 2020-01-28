package by.training.epam.dao;

import by.training.epam.bean.Client;
import by.training.epam.dao.exception.BadFileGroupDAOException;

public interface GroupDAO {

    public boolean addClient(Client client) throws BadFileGroupDAOException;

}
