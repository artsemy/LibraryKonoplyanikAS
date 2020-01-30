package by.training.epam.controller.command.impl;

import by.training.epam.controller.command.Command;
import by.training.epam.service.ClientService;
import by.training.epam.service.exception.ServiceException;
import by.training.epam.service.impl.ClientServiceImpl;

public class SignIn implements Command {

    private ClientService clientService;

    public SignIn() throws ServiceException {
        clientService = ClientServiceImpl.getInstance();
    }

    @Override
    public String execute(String request) throws ServiceException {
        return clientService.signIn(request);
    }

}
