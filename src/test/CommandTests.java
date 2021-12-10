package test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import controller.Command;
import controller.Parser;
import model.Model;

public class CommandTests {
    // @pre: Building a go-command without direction only saves the action
    @Test
    @DisplayName("null command")
    public void testCommandGoPre() {
        assertEquals("go", new Parser(new Model()).getCommand("go").toString());
    }

    // @post: Model movement output must be the same that the command does
    @ParameterizedTest
    @ValueSource(strings = { "up", "down", "right", "left", "INVALID" })
    @DisplayName("go <direction>")
    public void testCommandGoPost(String direction) {
        Command command = new Command("go", direction, new Model());
        switch (direction) {
            case "up" -> assertEquals(new Model().roomToNorth(), command.executeCommand(command));
            case "down" -> assertEquals(new Model().roomToSouth(), command.executeCommand(command));
            case "right" -> assertEquals(new Model().roomToEast(), command.executeCommand(command));
            case "left" -> assertEquals(new Model().roomToWest(), command.executeCommand(command));
            default -> assertEquals("Unknown command!", command.executeCommand(command));
        }
    }
}
