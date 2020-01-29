package by.training.epam.service.impl;

import by.training.epam.bean.Client;
import by.training.epam.dao.GroupDAO;
import by.training.epam.dao.exception.BadFileGroupDAOException;
import by.training.epam.service.ClientService;
import by.training.epam.service.exception.BadFileGroupServiceException;
import by.training.epam.service.exception.BadRequestGroupServiceException;
import by.training.epam.dao.impl.GroupDAOImpl;
import by.training.epam.service.exception.ServiceException;

import static by.training.epam.data.Constant.*;

public class ClientServiceImpl implements ClientService {

    private static ClientServiceImpl instance;
    private GroupDAO groupDAO;

    private final static String REGISTRATION_OK = "Hello newbie";
    private final static String REGISTRATION_NOT_OK = "can't register, bad login";
    private final static String SIGN_IN_OK = "welcome back";
    private final static String SIGN_IN_NOT_OK = "bad login or password";

    private ClientServiceImpl() throws BadFileGroupServiceException {
        try {
            groupDAO = GroupDAOImpl.getInstance();
        } catch (BadFileGroupDAOException e) {
            throw new BadFileGroupServiceException(e.getMessage(), e);
        }
    }

    public static synchronized ClientServiceImpl getInstance() throws BadFileGroupServiceException {
        if (instance == null) {
            instance = new ClientServiceImpl();
        }
        return instance;
    }

    @Override
    public String registration(String request) throws ServiceException {
        Client client = validateClient(request); //clientRole
        boolean success;
        try {
            success = groupDAO.registration(client);
        } catch (BadFileGroupDAOException e) {
            throw new BadFileGroupServiceException(e.getMessage(), e);
        }
        return success ? REGISTRATION_OK : REGISTRATION_NOT_OK;
    }

    @Override
    public String signIn(String request) throws ServiceException {
        Client client = validateClient(request.trim());
        boolean success = groupDAO.signIn(client);
        return success ? SIGN_IN_OK : SIGN_IN_NOT_OK;
    }

    private Client validateClient(String request) throws BadRequestGroupServiceException {
        if (request == null) {
            throw new BadRequestGroupServiceException(MESSAGE_CANT_VALIDATE_REQUEST);
        }
        Client client = new Client();
        String[] array = request.split(DIVIDER_LINE);
        client.setLogin(array[0]);
        if (array.length > 1) {
            client.setPassword(array[1]);
        }
        return client;
    }

}
