package by.training.epam.service;

import by.training.epam.service.exception.BadFileBookServiceException;
import by.training.epam.service.exception.BadRequestBookServiceException;

import java.io.IOException;

public interface BookService {

    public String addBook(String request) throws IOException, BadFileBookServiceException, BadRequestBookServiceException;

    public String deleteBook(String request) throws IOException, BadFileBookServiceException, BadRequestBookServiceException;

    public String changeBook(String request) throws IOException, BadFileBookServiceException, BadRequestBookServiceException;

    public String findBook(String request) throws BadRequestBookServiceException;

}
