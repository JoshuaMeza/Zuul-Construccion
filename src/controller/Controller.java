package controller;

import view.ICLI;
import model.Model;

public class Controller {
    private ICLI view;
    private Model model;

    public Controller(ICLI view, Model model) {
        this.view = view;
        this.model = model;
    }

    public void start() {
        view.print("Hola Mundo");
    }
}
