package by.training.epam.service;

import by.training.epam.service.exception.BadFileBookServiceException;
import by.training.epam.service.exception.BadRequestBookServiceException;

public interface BookService {

    public String create(String request) throws BadFileBookServiceException, BadRequestBookServiceException;

    public String read(String request) throws BadRequestBookServiceException, BadFileBookServiceException;

    public String update(String request) throws BadFileBookServiceException, BadRequestBookServiceException;

    public String delete(String request) throws BadFileBookServiceException, BadRequestBookServiceException;

}
