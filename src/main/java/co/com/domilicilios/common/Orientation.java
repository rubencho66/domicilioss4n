package co.com.domilicilios.common;

public enum Orientation {
    NORTH("dirección Norte"),
    SOUTH("dirección Sur"),
    WEST("dirección Oeste"),
    EAST("dirección Este");

    private String direction;

    private Orientation(String direction) {
        this.direction = direction;
    }

    public String getDirection() {
        return direction;
    }
}
