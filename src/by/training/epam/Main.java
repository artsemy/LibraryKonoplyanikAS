package by.training.epam;

import by.training.epam.bean.Book;
import by.training.epam.controller.Controller;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        String request;
        String response;
        Scanner scanner = new Scanner(System.in);
        Controller controller = new Controller();
        do {
            request = scanner.nextLine();
            response = controller.start(request);
            System.out.println(response);
        } while (!request.equals("exit"));
    }

}
