package co.com.domilicilios.model;

import java.util.ArrayList;
import java.util.List;

public class Drone {

    private List<Order> listOrders;

    private List<Position> visitedLocations;

    public Drone(List<Order> listOrders) {
        this.listOrders = listOrders;
        this.visitedLocations = new ArrayList<>();
    }

    public List<Order> getListOrders() {
        return listOrders;
    }

    public void setListOrders(List<Order> listOrders) {
        this.listOrders = listOrders;
    }

    public List<Position> getVisitedLocations() {
        return visitedLocations;
    }

    public void setVisitedLocations(List<Position> visitedLocations) {
        this.visitedLocations = visitedLocations;
    }

}
