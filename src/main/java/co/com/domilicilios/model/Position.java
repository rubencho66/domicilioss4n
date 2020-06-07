package co.com.domilicilios.model;

import co.com.domilicilios.common.Orientation;

public class Position {

    private long cardinalPointX;

    private long cardinalPointY;

    private Orientation orientation;

    public Position(long cardinalPointX, long cardinalPointY, Orientation orientation) {
        this.cardinalPointX = cardinalPointX;
        this.cardinalPointY = cardinalPointY;
        this.orientation = orientation;
    }

    public long getCardinalPointX() {
        return cardinalPointX;
    }

    public void setCardinalPointX(long cardinalPointX) {
        this.cardinalPointX = cardinalPointX;
    }

    public long getCardinalPointY() {
        return cardinalPointY;
    }

    public void setCardinalPointY(long cardinalPointY) {
        this.cardinalPointY = cardinalPointY;
    }

    public Orientation getOrientation() {
        return orientation;
    }

    public void setOrientation(Orientation orientation) {
        this.orientation = orientation;
    }

    @Override
    public String toString() {
        return "(" +
                cardinalPointX + ", " +
                cardinalPointY + ") , " +
                orientation.getDirection();
    }
}
