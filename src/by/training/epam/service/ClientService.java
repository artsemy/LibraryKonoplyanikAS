package by.training.epam.service;

import by.training.epam.service.exception.ServiceException;

public interface ClientService {

    public String registration(String request) throws ServiceException;

    public String signIn(String request) throws ServiceException;

}
