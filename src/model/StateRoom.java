package model;

import controller.Nullable;

public abstract class StateRoom implements Nullable {
    private final static String UNREACHABLE = "You can't go there!\n";
    private String description;
    private StateRoom north;
    private StateRoom south;
    private StateRoom east;
    private StateRoom west;

    public String getDefaultUnreachableMessage() {
        return UNREACHABLE;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public StateRoom getNorth() {
        return north == null ? NullRoom.getInstance() : north;
    }

    public StateRoom getSouth() {
        return south == null ? NullRoom.getInstance() : south;
    }

    public StateRoom getEast() {
        return east == null ? NullRoom.getInstance() : east;
    }

    public StateRoom getWest() {
        return west == null ? NullRoom.getInstance() : west;
    }

    public void setNorth(StateRoom north) {
        this.north = north;
    }

    public void setSouth(StateRoom south) {
        this.south = south;
    }

    public void setEast(StateRoom east) {
        this.east = east;
    }

    public void setWest(StateRoom west) {
        this.west = west;
    }

    public boolean canGo(StateRoom room) {
        return room.accesible();
    }

    public abstract boolean accesible();

    public abstract String moveNorth(Model model);

    public abstract String moveSouth(Model model);

    public abstract String moveEast(Model model);

    public abstract String moveWest(Model model);
}
