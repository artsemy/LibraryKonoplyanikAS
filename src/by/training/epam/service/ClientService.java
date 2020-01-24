package by.training.epam.service;

import java.io.IOException;

public interface ClientService {

    public String registration(String request) throws IOException;

    public String signIn(String request);

}
