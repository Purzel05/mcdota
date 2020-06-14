package de.prentl.firsttestproject;

public class McdPlayer {
    private final McdMap.Side side;

    public McdPlayer(McdMap.Side side) {
        this.side = side;
    }

    public McdMap.Side getSide() {
        return side;
    }
}
