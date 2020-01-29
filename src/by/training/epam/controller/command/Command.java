package by.training.epam.controller.command;

import by.training.epam.service.exception.*;

public interface Command {

    String execute(String request) throws ServiceException;

}
