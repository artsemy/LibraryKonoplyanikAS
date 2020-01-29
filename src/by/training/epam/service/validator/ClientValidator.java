package by.training.epam.service.validator;

import by.training.epam.bean.Client;
import by.training.epam.data.ClientRole;

import static by.training.epam.data.Constant.*;

public class ClientValidator {

    public static Client validateClient(String sClient) {
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
