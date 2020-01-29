package by.training.epam;

import by.training.epam.controller.Controller;
import by.training.epam.controller.command.exception.BadFileControllerException;
import by.training.epam.controller.command.exception.BadRequestControllerException;
import by.training.epam.controller.command.exception.ControllerException;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        String request;
        String response;
        Scanner scanner = new Scanner(System.in);
        Controller controller = new Controller();
        do {
            request = scanner.nextLine();
            try {
                response = controller.start(request);
            } catch (ControllerException e) {
                response = e.getMessage();
            }
            System.out.println(response);
        } while (!request.equals("exit"));
    }

}
