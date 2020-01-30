package by.training.epam.service.validator.impl;

import by.training.epam.bean.Client;
import by.training.epam.data.ClientRole;
import by.training.epam.service.validator.ClientValidator;

import static by.training.epam.data.Constant.*;

public class ClientValidatorImpl implements ClientValidator {

    private static ClientValidatorImpl instance;

    private ClientValidatorImpl(){}

    public static synchronized ClientValidatorImpl getInstance() {
        if (instance == null) {
            instance = new ClientValidatorImpl();
        }
        return instance;
    }

    @Override
    public Client validateClient(String sClient) {
        if (sClient == null) {
            return null;
        }
        String[] array = sClient.split(DIVIDER_LINE);
        if (array.length == 1) {
            return new Client(array[0], EMPTY_STRING, ClientRole.NO_ONE);
        }
        return new Client(array[0], array[1], ClientRole.NO_ONE);
    }

}
