package by.training.epam.service;

import by.training.epam.service.impl.BookServiceImpl;
import by.training.epam.service.impl.ClientServiceImpl;

public class ServiceFactory {

    private static ServiceFactory instance;
    private BookService bookService;
    private ClientService clientService;

    private ServiceFactory(){
        bookService = new BookServiceImpl();
        clientService = new ClientServiceImpl();
    }

    public static synchronized ServiceFactory getInstance(){
        if (instance == null) {
            instance = new ServiceFactory();
        }
        return instance;
    }

    public BookService getBookService(){
        return bookService;
    }

    public ClientService getClientService(){
        return clientService;
    }

}
