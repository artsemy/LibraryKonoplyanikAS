package by.training.epam.controller.command.impl;

import by.training.epam.controller.command.Command;
import by.training.epam.service.ClientService;
import by.training.epam.service.exception.ServiceException;
import by.training.epam.service.impl.ClientServiceImpl;

public class SignIn implements Command {

    private ClientService clientService;

    @Override
    public String execute(String request) throws ServiceException {
        String res;
        clientService = ClientServiceImpl.getInstance();
        res =  clientService.signIn(request);
        return res;
    }

}
