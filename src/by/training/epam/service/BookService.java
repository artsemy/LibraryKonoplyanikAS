package by.training.epam.service;

import by.training.epam.service.exception.ServiceException;

public interface BookService {

    public String create(String request) throws ServiceException;

    public String read(String request) throws ServiceException;

    public String update(String request) throws ServiceException;

    public String delete(String request) throws ServiceException;

}
