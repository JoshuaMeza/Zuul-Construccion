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
        if (command.toString().equalsIgnoreCase("help")) {
            return printHelp();
        } else if (command.toString().equalsIgnoreCase("quit")) {
            return "Goodbye!";
        } else if (command.word1.equalsIgnoreCase("go")) {
            if (word2 == null) {
                return "You realize you didn't go anywhere, and maybe you " +
                        "should pick a direction first.\n" + model.getRoomDescription();
            }
            return goRoom(command.word2);
        } else {
            return "Unknown command! Try with 'help'.";
        }
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
                return "Unknown command!";
        }
    }

    private String printHelp() {
        return "Your command words are:\n" +
                "go [up/down/right/left]\nquit\nhelp";

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
