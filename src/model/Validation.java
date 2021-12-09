package model;

import java.util.Set;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class Validation {
    protected boolean validateFileStructure(JSONObject json) {
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
}
