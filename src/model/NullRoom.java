package model;

public class NullRoom extends StateRoom {
    private static NullRoom nullRoom;

    private NullRoom() {
    }

    public static NullRoom getInstance() {
        if (nullRoom == null) {
            nullRoom = new NullRoom();
        }
        return nullRoom;
    }

    @Override
    public boolean isNull() {
        return true;
    }

    // All settters deactivated
    @Override
    public void setDescription(String description) {
        return;
    }

    @Override
    public void setNorth(StateRoom north) {
        return;
    }

    @Override
    public void setSouth(StateRoom south) {
        return;
    }

    @Override
    public void setEast(StateRoom east) {
        return;
    }

    @Override
    public void setWest(StateRoom west) {
        return;
    }

    // The players shouldn't be here, so they are not supposed to be able of moving
    // around.
    @Override
    public boolean accesible() {
        return false;
    }

    @Override
    public String getDescription() {
        return "UNDEFINED_ROOM";
    }

    @Override
    public String moveNorth(Model model) {
        return getDefaultUnreachableMessage() + model.getRoomDescription();
    }

    @Override
    public String moveSouth(Model model) {
        return moveNorth(model);
    }

    @Override
    public String moveEast(Model model) {
        return moveNorth(model);
    }

    @Override
    public String moveWest(Model model) {
        return moveNorth(model);
    }
}
