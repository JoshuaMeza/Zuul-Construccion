package model;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class WorldGenerator {
    Validation validate = new Validation();
    private static final String PANEL = ("" +
            "░░░░░░░░░░░░░░░░░░░░░░░░░\n" +
            "░░░░░░░░░░░░░░░░░░░░░░░░░\n" +
            "░░░░░░░░░░░░░░░░░░░░░░░░░\n" +
            "░░░░░░░░░░░░░░░░░░░░░░░░░\n" +
            "░░░░░░░░░░█████░░░░░░░░░░\n" +
            "░░░░░░░░░░█████░░░░░░░░░░\n" +
            "░░░░░░░░░░░░░░░░░░░░░░░░░\n" +
            "░░░░░░░░░░░░░░░░░░░░░░░░░\n" +
            "░░░░░░░░░░░░░░░░░░░░░░░░░\n" +
            "░░░░░░░░░░░░░░░░░░░░░░░░░");

    public StateRoom generateWorld() {
        StateRoom generatedRoom;
        JSONObject json = readConfig();

        if (validateFileStructure(json)) {
            HashMap<String, StateRoom> rooms = generateRoomsMap(json);
            generateConnections(json, rooms);

            generatedRoom = rooms.get((String) json.get("Main"));
        } else {
            generatedRoom = new Room();
            generatedRoom.setDescription(generateDescription("BADLY_CONFIGURED_WORLD", false, false, false, false));
        }

        return generatedRoom;
    }

    private JSONObject readConfig() {
        final String PATH = "./config/WorldConfig.json";
        JSONObject json;

        try {
            String jsonContent = Files.readString(Paths.get(PATH), StandardCharsets.UTF_8);
            json = (JSONObject) new JSONParser().parse(jsonContent);
        } catch (Exception e) {
            json = new JSONObject();
        }

        return json;
    }

    private boolean validateFileStructure(JSONObject json) {
        return validate.validateFileStructure(json);
    }

    private HashMap<String, StateRoom> generateRoomsMap(JSONObject json) {
        HashMap<String, StateRoom> rooms = new HashMap<>();
        JSONArray roomsArray = (JSONArray) json.get("Rooms");

        roomsArray.forEach((Object o) -> {
            String room = (String) o;
            rooms.put(room, new Room());
        });

        return rooms;
    }

    private void generateConnections(JSONObject json, HashMap<String, StateRoom> rooms) {
        JSONObject connections = (JSONObject) json.get("Connections");

        for (String roomName : rooms.keySet()) {
            JSONObject actualRoom = (JSONObject) connections.get(roomName);
            boolean north = false, south = false, east = false, west = false;
            StateRoom room = rooms.get(roomName);
            String target;

            if ((target = (String) actualRoom.get("North")) != null) {
                room.setNorth(rooms.get(target));
                north = true;
            }
            if ((target = (String) actualRoom.get("South")) != null) {
                room.setSouth(rooms.get(target));
                south = true;
            }
            if ((target = (String) actualRoom.get("East")) != null) {
                room.setEast(rooms.get(target));
                east = true;
            }
            if ((target = (String) actualRoom.get("West")) != null) {
                room.setWest(rooms.get(target));
                west = true;
            }

            room.setDescription(generateDescription(roomName, north, south, east, west));
        }
    }

    private String generateDescription(String name, boolean north, boolean south, boolean east, boolean west) {
        StringBuilder strBuilder = new StringBuilder(PANEL);

        if (north) {
            for (int i = 0; i < 4; i++) {
                int start = 10 + 26 * i;
                strBuilder = strBuilder.replace(start, start + 5, "█████");
            }
        }
        if (south) {
            for (int i = 0; i < 4; i++) {
                int start = 166 + 26 * i;
                strBuilder = strBuilder.replace(start, start + 5, "█████");
            }
        }
        if (west) {
            for (int i = 0; i < 2; i++) {
                int start = 104 + 26 * i;
                strBuilder = strBuilder.replace(start, start + 10, "██████████");
            }
        }
        if (east) {
            for (int i = 0; i < 2; i++) {
                int start = 104 + 26 * i;
                strBuilder = strBuilder.replace(start + 15, start + 25, "██████████");
            }
        }

        return "You are at the " + name + "\n" + strBuilder.toString();
    }
}
