package by.training.epam.service.validator;

import by.training.epam.bean.Book;

public interface BookValidator {

    Book validateRead(String sBook);

    Book validateCreate(String sBook);

    Book validateUpdate(String sBook);

    int validateDelete(String sId);

}
