package by.training.epam.controller.command;

import by.training.epam.service.exception.BadFileBookServiceException;
import by.training.epam.service.exception.BadFileGroupServiceException;
import by.training.epam.service.exception.BadRequestBookServiceException;
import by.training.epam.service.exception.BadRequestGroupServiceException;

public interface Command {

    String execute(String request) throws BadFileBookServiceException, BadRequestBookServiceException, BadFileGroupServiceException, BadRequestGroupServiceException;

}
