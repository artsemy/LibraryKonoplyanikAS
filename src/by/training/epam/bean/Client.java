package by.training.epam.bean;

import by.training.epam.data.ClientRole;
import by.training.epam.data.Constant;

public class Client {

    private String login;
    private String password;
    private ClientRole clientRole;

    public Client() {
        login = "";
        password = "";
        clientRole = ClientRole.NO_ONE;
    }

    public Client(String str) {
        this();
        String[] array = str.split(Constant.DIVIDER_LINE);
        if (array.length >= 1) {
            login = array[0];
        }
        if (array.length >= 2) {
            password = array[1];
        }
        if (array.length == 3) {
            clientRole = ClientRole.valueOf(array[2].toUpperCase());
        }
    }

    public Client(String login, String password, String role) {
        setLogin(login);
        setPassword(password);
        ClientRole clientRole = ClientRole.NO_ONE;
        try {
            clientRole = ClientRole.valueOf(role.toUpperCase());
        } catch (IllegalArgumentException ignored){
        }
        setClientRole(clientRole);
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login == null ? "" : login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? "" : password;
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
        Client user = (Client) o;
        return login.equals(user.login) && password.equals(user.password);
    }

    @Override
    public int hashCode() {
        int p = 31;
        int res = 1;
        res = res*p + login.hashCode();
        res = res*p + password.hashCode();
        return res;
    }

    @Override
    public String toString() {
        return "User{" +
                "login='" + login + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

}
