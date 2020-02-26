package by.training.epam.controller.command.impl;

import by.training.epam.controller.command.Command;
import by.training.epam.service.BookService;
import by.training.epam.service.ServiceFactory;
import by.training.epam.service.exception.ServiceException;
import by.training.epam.service.impl.BookServiceImpl;

public class DeleteBook implements Command {

    @Override
    public String execute(String request) throws ServiceException {
        ServiceFactory factory = ServiceFactory.getInstance();
        BookService bookService = factory.getBookService();
        return bookService.delete(request);
    }

}
