package co.com.domilicilios.dao.impl;

import co.com.domilicilios.common.Orientation;
import co.com.domilicilios.dao.DroneDao;
import co.com.domilicilios.dao.PropertiesDao;
import co.com.domilicilios.model.Drone;
import co.com.domilicilios.model.Order;
import co.com.domilicilios.model.Position;
import org.apache.commons.lang3.tuple.Triple;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DroneDaoImpl implements DroneDao {

    final static Logger LOGGER = LoggerFactory.getLogger(DroneDaoImpl.class);

    private static final String INSTRUCTION_D = "D";
    private static final String INSTRUCTION_I = "I";
    private static final String INSTRUCTION_A = "A";

    private final List<Orientation> cardinalPoints = Arrays.asList(Orientation.NORTH, Orientation.EAST, Orientation.SOUTH, Orientation.WEST);

    private PropertiesDao propertiesDao;

    public DroneDaoImpl(PropertiesDao propertiesDao) {
        this.propertiesDao = propertiesDao;
    }

    @Override
    public void carryOrders(Drone drone) {
        List<Position> visitedLocations = new ArrayList<>();
        for (Order order: drone.getListOrders()) {
            visitedLocations.add(goToDirection(order));
        }
        drone.setVisitedLocations(visitedLocations);
    }

    private Position goToDirection(Order order) {
        String goTo = order.getAction();
        Position position = getInitialPosition();
        long x = position.getCardinalPointX();
        long y = position.getCardinalPointY();
        Triple<Long, Long, Orientation> movement = Triple.of(x, y, position.getOrientation());

        for (int i = 0; i < goTo.length(); i++) {
            final String action = Character.toString(goTo.charAt(i));
            if (action.equals(INSTRUCTION_A)) {
                if (movement.getRight().equals(Orientation.NORTH) || movement.getRight().equals(Orientation.SOUTH)) {
                    y = moveOnXAndY(movement.getRight(), movement.getMiddle());
                    movement = Triple.of(movement.getLeft(), y, movement.getRight());
                } else {
                    x = moveOnXAndY(movement.getRight(), movement.getLeft());
                    movement = Triple.of(x, movement.getMiddle(), movement.getRight());
                }
            } else {
                Orientation orientation = turnSideways(movement.getRight(), action);
                movement = Triple.of(movement.getLeft(), movement.getMiddle(), orientation);
            }
        }
        order.setDelivered(true);
        setFinalPosition(position, movement);

        return position;
    }

    private Position getInitialPosition() {
        return new Position(0,0, Orientation.NORTH);
    }

    private void setFinalPosition(Position position, final Triple<Long, Long, Orientation> finalMove){
        position.setCardinalPointX(finalMove.getLeft());
        position.setCardinalPointY(finalMove.getMiddle());
        position.setOrientation(finalMove.getRight());
    }

    private long moveOnXAndY(Orientation orientation, long pos) {
        if (orientation.equals(Orientation.EAST) || orientation.equals(Orientation.NORTH)) {
            pos++;
        } else {
            pos--;
        }
        return validationRange(pos);
    }

    private long validationRange(long position) {
        long valueValidated = propertiesDao.getDistance();
        if (position > propertiesDao.getDistance() || position < -propertiesDao.getDistance()) {
            if (position < 0) {
                valueValidated = valueValidated * -1;
            }
            LOGGER.warn("Exceeds the allowed limit, the value is left: {} ", valueValidated);
        } else {
            valueValidated = position;
        }
        return valueValidated;
    }

    private Orientation turnSideways(Orientation orientation, String turn) {
        int index = cardinalPoints.indexOf(orientation);
        if (turn.equals(INSTRUCTION_D)) {
            index++;
            index = index > 3 ? 0 : index;
        } else if (turn.equals(INSTRUCTION_I)) {
            index--;
            index = index < 0 ? 3 : index;
        }
        return cardinalPoints.get(index);
    }


}
