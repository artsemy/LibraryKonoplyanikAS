package by.training.epam.service;

import by.training.epam.service.exception.BadFileGroupServiceException;
import by.training.epam.service.exception.BadRequestGroupServiceException;

import java.io.IOException;

public interface ClientService {

    public String registration(String request) throws BadFileGroupServiceException, BadRequestGroupServiceException;

    public String signIn(String request) throws BadRequestGroupServiceException, BadFileGroupServiceException;

}
