package by.training.epam.bean;

import by.training.epam.service.ClientRole;

import java.io.Serializable;

public class Client implements Comparable, Serializable {

    private static final long serialVersionUID = 1L;

    private String login;
    private String password;
    private ClientRole clientRole;

    public Client() {
    }

    public Client(String login, String password, ClientRole role) {
        setLogin(login);
        setPassword(password);
        setClientRole(role);
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ClientRole getClientRole() {
        return clientRole;
    }

    public void setClientRole(ClientRole clientRole) {
        this.clientRole = clientRole;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return login.equals(client.login) && password.equals(client.password) && clientRole.equals(client.clientRole);
    }

    @Override
    public int hashCode() {
        int p = 31;
        int res = 1;
        res = res*p + login.hashCode();
        res = res*p + password.hashCode();
        res = res*p + clientRole.hashCode();
        return res;
    }

    @Override
    public String toString() {
        return "Client{" +
                "login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", clientRole=" + clientRole +
                '}';
    }

    @Override
    public int compareTo(Object o) {
        Client client = (Client) o;
        return login.compareTo(client.login);
    }

}
