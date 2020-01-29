package by.training.epam.controller.command.impl;

import by.training.epam.controller.command.Command;
import by.training.epam.service.ClientService;
import by.training.epam.service.exception.BadFileGroupServiceException;
import by.training.epam.service.exception.BadRequestGroupServiceException;
import by.training.epam.service.impl.ClientServiceImpl;

public class Registration implements Command {

    @Override
    public String execute(String request) throws BadFileGroupServiceException, BadRequestGroupServiceException {
        String res;
        ClientService clientService = ClientServiceImpl.getInstance();
        res = clientService.registration(request);
        return res;
    }

}
