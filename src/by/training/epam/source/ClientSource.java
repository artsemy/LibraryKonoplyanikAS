package by.training.epam.source;

import by.training.epam.bean.Client;

import java.io.IOException;
import java.util.Collection;

public interface ClientSource {

    public Collection<Client> read() throws IOException;

    public void write(Collection<Client> clients) throws IOException;

}
