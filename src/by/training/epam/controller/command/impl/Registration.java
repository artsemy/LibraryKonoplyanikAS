package by.training.epam.controller.command.impl;

import by.training.epam.controller.command.Command;
import by.training.epam.service.ClientService;
import by.training.epam.service.impl.ClientServiceImpl;

import java.io.IOException;

public class Registration implements Command {

    @Override
    public String execute(String request) {
        String res = null;
        try {
            ClientService clientService = new ClientServiceImpl();
            res = clientService.registration(request);
        } catch (IOException e) {
            e.printStackTrace(); //bad
        }
        return res;
    }

}
