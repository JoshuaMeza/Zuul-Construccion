package controller;

import java.util.Scanner;

import model.Model;

public class Parser {
    private CommandWords commands;
    private Model model;

    public Parser(Model model) {
        this.model = model;
        this.commands = new CommandWords();
    }

    public Command getCommand(String inputLine) {
        String word1 = null;
        String word2 = null;

        Scanner tokenizer = new Scanner(inputLine);
        if (tokenizer.hasNext()) {
            word1 = tokenizer.next();
            if (tokenizer.hasNext()) {
                word2 = tokenizer.next();
            }
        }
        tokenizer.close();

        // Now check whether this word is known. If so, create a command
        // with it. If not, create a "null" command (for unknown command).
        if (commands.isCommand(word1)) {
            return new Command(word1, word2, model);
        } else {
            return new Command("", word2, model);
        }
    }
}
