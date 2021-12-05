package model;

public class Room extends StateRoom {
    @Override
    public boolean accesible() {
        return true;
    }

    @Override
    public String moveNorth(Model model) {
        String output;

        if (canGo(getNorth())) {
            model.setCurrentRoom(getNorth());
            output = model.getRoomDescription();
        } else {
            output = "You can't go there!\n" + model.getRoomDescription();
        }

        return output;
    }

    @Override
    public String moveSouth(Model model) {
        String output;

        if (canGo(getSouth())) {
            model.setCurrentRoom(getSouth());
            output = model.getRoomDescription();
        } else {
            output = "You can't go there!\n" + model.getRoomDescription();
        }

        return output;
    }

    @Override
    public String moveEast(Model model) {
        String output;

        if (canGo(getEast())) {
            model.setCurrentRoom(getEast());
            output = model.getRoomDescription();
        } else {
            output = "You can't go there!\n" + model.getRoomDescription();
        }

        return output;
    }

    @Override
    public String moveWest(Model model) {
        String output;

        if (canGo(getWest())) {
            model.setCurrentRoom(getWest());
            output = model.getRoomDescription();
        } else {
            output = "You can't go there!\n" + model.getRoomDescription();
        }

        return output;
    }
}
