package controller;

import model.Model;

public class Command {
    private String word1, word2;
    private Model model;

    public Command(String word1, String word2, Model model) {
        this.model = model;
        setWord1(word1);
        setWord2(word2);
    }

    public String getWord1() {
        return word1;
    }

    private void setWord1(String word1) {
        this.word1 = word1;
    }

    public String getWord2() {
        return word2;
    }

    private void setWord2(String word2) {
        this.word2 = word2;
    }

    public String executeCommand(Command command) {
        /* if (model == null) return printWelcome(); */
        System.out.println(command.toString());
        if (command.toString().equalsIgnoreCase("help")) {
            return printHelp();
        } else if (command.toString().equalsIgnoreCase("quit")) {
            return "Goodbye!";
        } else if (command.word1.equalsIgnoreCase("go")) {
            if (word2 == null) {
                System.out.println("go where?");
                return "";
            }
            return goRoom(command.word2);
        }
        return "";
    }

    private String goRoom(String word2) {
        switch (word2) {
            case "up":
                return model.roomToNorth();
            case "down":
                return model.roomToSouth();
            case "left":
                return model.roomToWest();
            case "right":
                return model.roomToEast();
            default:
                throw new NullPointerException();
        }
    }

    private String printHelp() {
        return "You are lost. You are alone. You wander\n" +
                "around at the university.\n\n" +
                "Your command words are:\n" +
                "go quit help";
    }

    @Override
    public String toString() {
        if (getWord1() != null) {
            if (getWord2() == null)
                return getWord1();
            return getWord1() + " " + getWord2();
        }
        return getWord2();
    }
}
