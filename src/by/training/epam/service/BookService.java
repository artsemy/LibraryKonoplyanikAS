package by.training.epam.service;

import by.training.epam.service.exception.BadFileBookServiceException;
import by.training.epam.service.exception.BadRequestBookServiceException;

public interface BookService {

    public String addBook(String request) throws BadFileBookServiceException, BadRequestBookServiceException;

    public String deleteBook(String request) throws BadFileBookServiceException, BadRequestBookServiceException;

    public String changeBook(String request) throws BadFileBookServiceException, BadRequestBookServiceException;

    public String findBook(String request) throws BadRequestBookServiceException;

}
