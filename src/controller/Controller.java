package controller;

import view.ICLI;
import model.Model;

public class Controller {
    private ICLI view;
    private Model model;
    private Parser parser;

    public Controller(ICLI view, Model model) {
        this.view = view;
        this.model = model;
        parser = new Parser(model);
    }

    public void start() {
        view.print(printWelcome());
        Command command;
        String print = "";

        while(!print.equalsIgnoreCase("Goodbye!")) {
            command = parser.getCommand();
            print = command.executeCommand(command);
            view.print(print);
        }
    }

    private String printWelcome() {
        return "Welcome to the World of Zuul!\n" +
                "World of Zuul is a new, incredibly boring adventure game.\n" +
                "Type 'help' if you need help.\n\n" +
                model.getRoomDescription();

    }
}
