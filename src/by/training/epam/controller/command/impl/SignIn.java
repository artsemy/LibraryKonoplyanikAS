package by.training.epam.controller.command.impl;

import by.training.epam.controller.command.Command;
import by.training.epam.service.exception.BadFileGroupServiceException;
import by.training.epam.service.exception.BadRequestGroupServiceException;
import by.training.epam.service.impl.ClientServiceImpl;

public class SignIn implements Command {

    @Override
    public String execute(String request) throws BadFileGroupServiceException, BadRequestGroupServiceException {
        String res;
        ClientServiceImpl clientService = new ClientServiceImpl();
        res =  clientService.signIn(request);
        return res;
    }

}
