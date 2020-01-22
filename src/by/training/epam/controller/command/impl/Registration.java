package by.training.epam.controller.command.impl;

import by.training.epam.bean.Client;
import by.training.epam.controller.command.Command;
import by.training.epam.service.ClientService;

import java.io.IOException;

public class Registration implements Command {

    @Override
    public String execute(String request) {
        Client client = new Client(request);
        String res = null;
        try {
            ClientService clientService = new ClientService();
            res = clientService.registration(client);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return res;
    }

}
