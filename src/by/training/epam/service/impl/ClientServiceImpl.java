package by.training.epam.service.impl;

import by.training.epam.bean.Client;
import by.training.epam.dao.DAOFactory;
import by.training.epam.dao.GroupDAO;
import by.training.epam.dao.exception.DAOException;
import by.training.epam.service.ClientService;
import by.training.epam.service.exception.ServiceException;
import by.training.epam.service.validator.ClientValidator;
import by.training.epam.service.validator.impl.ClientValidatorImpl;

public class ClientServiceImpl implements ClientService {

    private final static String REGISTRATION_OK = "Hello newbie";
    private final static String REGISTRATION_NOT_OK = "can't register, bad login";
    private final static String SIGN_IN_OK = "welcome back";
    private final static String SIGN_IN_NOT_OK = "bad login or password";

    public ClientServiceImpl() {}

    @Override
    public String register(String request) throws ServiceException {
        boolean success;
        try {
            DAOFactory daoFactory = DAOFactory.getInstance();
            GroupDAO groupDAO = daoFactory.getGroupDAO();
            ClientValidator clientValidator = ClientValidatorImpl.getInstance();
            Client client = clientValidator.validateClient(request);
            success = groupDAO.register(client);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return success ? REGISTRATION_OK : REGISTRATION_NOT_OK;
    }

    @Override
    public String signIn(String request) throws ServiceException {
        boolean success;
        try {
            DAOFactory daoFactory = DAOFactory.getInstance();
            GroupDAO groupDAO = daoFactory.getGroupDAO();
            ClientValidator clientValidator = ClientValidatorImpl.getInstance();
            Client client = clientValidator.validateClient(request);
            success = groupDAO.signIn(client);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return success ? SIGN_IN_OK : SIGN_IN_NOT_OK;
    }

}
