package by.training.epam.data;

public class ClientRoleHolder {

    private static ClientRole role = ClientRole.NO_ONE;

    public static ClientRole getRole() {
        return role;
    }

    public static void setRole(ClientRole role) {
        ClientRoleHolder.role = role;
    }

}
