package by.training.epam.dao;

import by.training.epam.dao.exception.DAOException;
import by.training.epam.dao.impl.GroupDAOImpl;
import by.training.epam.dao.impl.LibraryDAOImpl;

public class DAOFactory {

    private static DAOFactory instance;
    private GroupDAO groupDAO;
    private LibraryDAO libraryDAO;

    private DAOFactory() throws DAOException {
        groupDAO = new GroupDAOImpl();
        libraryDAO = new LibraryDAOImpl();
    }

    public static synchronized DAOFactory getInstance() throws DAOException {
        if (instance == null) {
            instance = new DAOFactory();
        }
        return instance;
    }

    public GroupDAO getGroupDAO(){
        return groupDAO;
    }

    public LibraryDAO getLibraryDAO(){
        return libraryDAO;
    }

}
