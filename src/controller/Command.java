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
        /*if (model == null) return printWelcome();*/
        System.out.println(command.toString());
        if (command.toString().equalsIgnoreCase("help")) {
            return printHelp();
        } else if (command.toString().equalsIgnoreCase("quit")) {
            return "Goodbye!";
<<<<<<< HEAD
        } else if(command.word1.equalsIgnoreCase("go")) {
            if(word2 == null) {
=======
                return "";
            }
        }
        return "";
    }

    private String goRoom(String word2) {
        switch (word2) {
<<<<<<< HEAD
            case "up" -> model.roomToNorth();
            case "down" -> model.roomToSouth();
            case "left" -> model.roomToWest();
            case "right" -> model.roomToEast();
            default -> throw new NullPointerException();
        }
        return model.getRoomDescription();
=======
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
>>>>>>> aa0fd5ef97f0cf5d9ba8d9af5bfa9a5ee2fb4938
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
<<<<<<< HEAD
            if(getWord2() == null) return getWord1();
=======
            if (getWord2() == null)
                return getWord1();
>>>>>>> aa0fd5ef97f0cf5d9ba8d9af5bfa9a5ee2fb4938
            return getWord1() + " " + getWord2();
        }
        return getWord2();
    }
}
