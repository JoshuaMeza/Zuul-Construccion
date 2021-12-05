package model;

public class Model {
    private StateRoom currentRoom;

    public Model() {
        this.currentRoom = new WorldGenerator().generateWorld();
    }

    public void setCurrentRoom(StateRoom currentRoom) {
        this.currentRoom = currentRoom;
    }

    public String getRoomDescription() {
        return currentRoom.getDescription();
    }

    public String roomToNorth() {
        return currentRoom.moveNorth(this);
    }

    public String roomToSouth() {
        return currentRoom.moveSouth(this);
    }

    public String roomToEast() {
        return currentRoom.moveEast(this);
    }

    public String roomToWest() {
        return currentRoom.moveWest(this);
    }
}
