package by.training.epam.bean;

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
        String[] array = str.split(" ");
        try {
            login = array[0];
            password = array[1];
            clientRole = ClientRole.valueOf(array[2].toUpperCase());
        } catch (ArrayIndexOutOfBoundsException | IllegalArgumentException ignored) {
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
        if (login == null) {
            login = "";
        }
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        if (password == null) {
            password = "";
        }
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
