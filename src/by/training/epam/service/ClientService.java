package by.training.epam.service;

import by.training.epam.service.exception.ServiceException;

public interface ClientService {

    String registration(String request) throws ServiceException;

    String signIn(String request) throws ServiceException;

}
