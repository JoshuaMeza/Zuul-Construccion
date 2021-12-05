package model;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Set;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class WorldGenerator {
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
        try {
            return (validateUpperLevelOptions(json) && validateLength(json) &&
                    validateConnections(json));
        } catch (Exception e) {
            return false;
        }
    }

    private boolean validateUpperLevelOptions(JSONObject json) {
        Set<String> upperLevel = json.keySet();
        return (upperLevel.contains("Rooms") && upperLevel.contains("Main") && upperLevel.contains("Connections")
                && json.get("Rooms") != null && json.get("Main") != null && json.get("Connections") != null);
    }

    private boolean validateLength(JSONObject json) {
        JSONArray declaredRooms = (JSONArray) json.get("Rooms");
        Set<String> declaredConnections = ((JSONObject) json.get("Connections")).keySet();
        return declaredRooms.size() == declaredConnections.size() && declaredConnections.size() > 0;
    }

    private boolean validateConnections(JSONObject json) {
        JSONObject connections = (JSONObject) json.get("Connections");
        JSONArray declaredRooms = (JSONArray) json.get("Rooms");
        Set<String> roomsLevel = connections.keySet();

        if (!declaredRooms.contains((String) json.get("Main"))) {
            return false;
        }

        for (String roomName : roomsLevel) {
            JSONObject room = (JSONObject) connections.get(roomName);
            Set<String> orientationKeys = room.keySet();

            if (!(declaredRooms.contains(roomName) && validateOrientations(orientationKeys) &&
                    validatePaths(room, orientationKeys, declaredRooms))) {
                return false;
            }
        }

        return true;
    }

    private boolean validateOrientations(Set<String> orientationKeys) {
        return (orientationKeys.size() == 4 && orientationKeys.contains("North") &&
                orientationKeys.contains("South") && orientationKeys.contains("East") &&
                orientationKeys.contains("West"));
    }

    private boolean validatePaths(JSONObject room, Set<String> orientationKeys, JSONArray declaredRooms) {
        for (String path : orientationKeys) {
            String destination = (String) room.get(path);

            if (destination != null) {
                if (!declaredRooms.contains(destination)) {
                    return false;
                }
            }
        }

        return true;
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

        return "You are in the " + name + "\n" + strBuilder.toString();
    }
}
