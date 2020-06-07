package co.com.domilicilios.model;

public class Order {

    private boolean isDelivered;

    private String action;

    public Order(boolean isDelivered, String action) {
        this.isDelivered = isDelivered;
        this.action = action;
    }

    public boolean isDelivered() {
        return isDelivered;
    }

    public void setDelivered(boolean delivered) {
        isDelivered = delivered;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }
}
