package by.training.epam.service.impl;

import by.training.epam.bean.Client;
import by.training.epam.dao.exception.BadFileGroupDAOException;
import by.training.epam.service.ClientService;
import by.training.epam.service.exception.BadFileGroupServiceException;
import by.training.epam.service.exception.BadRequestGroupServiceException;
import by.training.epam.dao.impl.GroupDAOImplSingleton;
import by.training.epam.data.ClientRole;
import by.training.epam.data.ClientRoleHolder;

import static by.training.epam.data.Constant.*;

import java.util.Set;


public class ClientServiceImpl implements ClientService {

    private final String REGISTRATION_OK = "Hello newbie";
    private final String REGISTRATION_NOT_OK = "can't register, bad login";
    private final String SIGN_IN_OK = "welcome back";
    private final String SIGN_IN_NOT_OK = "bad login or password";

    @Override
    public String registration(String request) throws BadFileGroupServiceException, BadRequestGroupServiceException {
        Client client = validateClient(request);
        Set<Client> clientSet;
        try {
            clientSet = GroupDAOImplSingleton.getInstance().getClientSet();
            for (Client c: clientSet) {
                if (client.getLogin().equals(c.getLogin())) {
                    return REGISTRATION_NOT_OK;
                }
            }
            client.setClientRole(ClientRole.USER);
            GroupDAOImplSingleton.getInstance().addClient(client);
        } catch (BadFileGroupDAOException e) {
            throw new BadFileGroupServiceException(e.getMessage(), e);
        }
        ClientRoleHolder.setRole(ClientRole.USER);
        return REGISTRATION_OK;
    }

    @Override
    public String signIn(String request) throws BadRequestGroupServiceException, BadFileGroupServiceException {
        Client client = validateClient(request.trim());
        Set<Client> clientSet;
        try {
            clientSet = GroupDAOImplSingleton.getInstance().getClientSet();
        } catch (BadFileGroupDAOException e) {
            throw new BadFileGroupServiceException(e.getMessage(), e);
        }
        for (Client c: clientSet) {
            if (client.equals(c)) {
                ClientRoleHolder.setRole(c.getClientRole());
                return SIGN_IN_OK;
            }
        }
        return SIGN_IN_NOT_OK;
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
