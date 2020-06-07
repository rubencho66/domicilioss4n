package co.com.domilicilios.dao.impl;

import co.com.domilicilios.common.Orientation;
import co.com.domilicilios.dao.DroneDao;
import co.com.domilicilios.dao.PropertiesDao;
import co.com.domilicilios.model.Drone;
import co.com.domilicilios.model.Order;
import co.com.domilicilios.model.Position;
import org.apache.commons.collections4.CollectionUtils;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class DroneDaoImplTest {

    PropertiesDao daoFile;
    DroneDao dao;

    @Before
    public void setUp(){
        daoFile = new PropertiesDaoImpl();
        dao = new DroneDaoImpl(daoFile);
    }

    @Test
    public void carryOrdersNegativeMovementTest() {
        Drone test = getDrone("DDDAIAD");
        assertTrue(CollectionUtils.isEmpty(test.getVisitedLocations()));
        dao.carryOrders(test);
        final List<Position> visitedLocations = test.getVisitedLocations();
        assertTrue(CollectionUtils.isNotEmpty(visitedLocations));
        visitedLocations.forEach(p -> {
            assertEquals(-1, p.getCardinalPointX());
            assertEquals(-1, p.getCardinalPointY());
            assertEquals(Orientation.WEST, p.getOrientation());
        });
    }

    @Test
    public void carryOrdersRightStepsFinalOrientationNorthTest() {
        Drone test = getDrone("AAADAAIA");
        assertTrue(CollectionUtils.isEmpty(test.getVisitedLocations()));
        dao.carryOrders(test);
        final List<Position> visitedLocations = test.getVisitedLocations();
        assertTrue(CollectionUtils.isNotEmpty(visitedLocations));
        visitedLocations.forEach(p -> {
            assertEquals(2, p.getCardinalPointX());
            assertEquals(4, p.getCardinalPointY());
            assertEquals(Orientation.NORTH, p.getOrientation());
        });
    }

    @Test
    public void carryOrdersRightStepsDoesNotExceedMaximumDistanceTest() {
        Drone test = getDrone("AAAAAAAAAAAADAAAAAAAAAAAAAAA");
        assertTrue(CollectionUtils.isEmpty(test.getVisitedLocations()));
        dao.carryOrders(test);
        final List<Position> visitedLocations = test.getVisitedLocations();
        assertTrue(CollectionUtils.isNotEmpty(visitedLocations));
        visitedLocations.forEach(p -> {
            assertEquals(daoFile.getDistance(), p.getCardinalPointX());
            assertEquals(daoFile.getDistance(), p.getCardinalPointY());
            assertEquals(Orientation.EAST, p.getOrientation());
        });
    }

    @Test
    public void carryOrdersRightStepsDoesNotExceedMaximumDistanceNegativeTest() {
        Drone test = getDrone("IAAAAAAAAAAAAIAAAAAAAAAAAAAAA");
        assertTrue(CollectionUtils.isEmpty(test.getVisitedLocations()));
        dao.carryOrders(test);
        final List<Position> visitedLocations = test.getVisitedLocations();
        assertTrue(CollectionUtils.isNotEmpty(visitedLocations));
        visitedLocations.forEach(p -> {
            assertEquals(-daoFile.getDistance(), p.getCardinalPointX());
            assertEquals(-daoFile.getDistance(), p.getCardinalPointY());
            assertEquals(Orientation.SOUTH, p.getOrientation());
        });
    }

    private Drone getDrone(String action) {
        return new Drone(Arrays.asList(getOrder(action)));
    }

    private Order getOrder(String action) {
        return new Order(false, action);
    }
}
