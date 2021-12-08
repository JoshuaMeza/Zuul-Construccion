package controller;

import java.util.Scanner;

import model.Model;

public class Parser {
    private CommandWords commands; // holds all valid command words
    private Model model;

    public Parser(Model model) {
        this.model = model;
        this.commands = new CommandWords();
    }

    public Command getCommand(String inputLine) {
        String word1 = null;
        String word2 = null;

        // Find up to two words on the line.
        Scanner tokenizer = new Scanner(inputLine);
        if (tokenizer.hasNext()) {
            word1 = tokenizer.next(); // get first word
            if (tokenizer.hasNext()) {
                word2 = tokenizer.next(); // get second word
                // note: we just ignore the rest of the input line.
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
