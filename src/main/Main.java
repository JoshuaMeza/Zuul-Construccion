package main;

import controller.Controller;
import model.Model;
import view.CLIView;

public class Main {
    public static void main(String[] args) {
        Controller controller = new Controller(new CLIView(), new Model());
        controller.start();
    }
}
