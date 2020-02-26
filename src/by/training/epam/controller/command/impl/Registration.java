package by.training.epam.controller.command.impl;

import by.training.epam.controller.command.Command;
import by.training.epam.service.ClientService;
import by.training.epam.service.ServiceFactory;
import by.training.epam.service.exception.ServiceException;
import by.training.epam.service.impl.ClientServiceImpl;

public class Registration implements Command {

    @Override
    public String execute(String request) throws ServiceException {
        ServiceFactory factory = ServiceFactory.getInstance();
        ClientService clientService = factory.getClientService();
        return clientService.register(request);
    }

}
