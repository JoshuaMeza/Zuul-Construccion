package model;

public abstract class StateRoom {
    private String description;
    private StateRoom north;
    private StateRoom south;
    private StateRoom east;
    private StateRoom west;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public StateRoom getNorth() {
        return north;
    }

    public StateRoom getSouth() {
        return south;
    }

    public StateRoom getEast() {
        return east;
    }

    public StateRoom getWest() {
        return west;
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
        return room != null ? room.accesible() : false;
    }

    public abstract boolean accesible();

    public abstract String moveNorth(Model model);

    public abstract String moveSouth(Model model);

    public abstract String moveEast(Model model);

    public abstract String moveWest(Model model);
}
