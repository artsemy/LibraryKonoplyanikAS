package by.training.epam.service;

import by.training.epam.service.exception.ServiceException;

public interface ClientService {

    String register(String request) throws ServiceException;

    String signIn(String request) throws ServiceException;

}
