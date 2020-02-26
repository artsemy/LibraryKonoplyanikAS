package by.training.epam.source.impl;

import by.training.epam.bean.Client;
import by.training.epam.service.ClientRole;
import by.training.epam.source.ClientSource;

import java.io.*;
import java.util.Collection;
import java.util.Set;
import java.util.TreeSet;

import static by.training.epam.Constant.*;

public class ClientSourceImpl implements ClientSource {

    private static ClientSourceImpl instance;

    private ClientSourceImpl(){}

    public static synchronized ClientSourceImpl getInstance(){
        if (instance == null) {
            instance = new ClientSourceImpl();
        }
        return instance;
    }

    @Override
    public Collection<Client> read() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(PATH_TO_USER_FILE));
        Set<Client> clientCache = new TreeSet<>();
        String line;
        while ( (line = reader.readLine()) != null) {
            Client client = parseClient(line);
            if (client != null) {
                clientCache.add(client);
            }
        }
        return clientCache;
    }

    @Override
    public void write(Collection<Client> clients) throws IOException {
        FileWriter writer = new FileWriter(PATH_TO_USER_FILE, false);
        for (Client c: clients) {
            String str = String.join(DIVIDER_LINE, c.getLogin(), c.getPassword(), c.getClientRole().toString(), END_LINE);
            writer.write(str);
        }
        writer.close();
    }

    private Client parseClient(String line) {
        if (line == null || line.equals(EMPTY_STRING)) {
            return null;
        }
        String[] array = line.split(DIVIDER_LINE);
        return new Client(array[0], array[1], ClientRole.valueOf(array[2].toUpperCase()));
    }

}
