package by.training.epam.controller.command.impl;

import by.training.epam.controller.command.Command;
import by.training.epam.service.impl.ClientServiceImpl;

import java.io.IOException;

public class SignIn implements Command {

    @Override
    public String execute(String request) {
        String res = null;
        try {
            ClientServiceImpl clientService = new ClientServiceImpl();
            res =  clientService.signIn(request);
        } catch (IOException e) {
            e.printStackTrace(); //bad
        }
        return res;
    }

}
