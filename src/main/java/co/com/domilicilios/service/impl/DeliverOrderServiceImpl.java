package co.com.domilicilios.service.impl;

import co.com.domilicilios.builder.FileInstruction;
import co.com.domilicilios.builder.InstructionBuilder;
import co.com.domilicilios.dao.DroneDao;
import co.com.domilicilios.dao.PositionDao;
import co.com.domilicilios.dao.PropertiesDao;
import co.com.domilicilios.dao.impl.DroneDaoImpl;
import co.com.domilicilios.dao.impl.PositionDaoImpl;
import co.com.domilicilios.dao.impl.PropertiesDaoImpl;
import co.com.domilicilios.model.Drone;
import co.com.domilicilios.model.Order;
import co.com.domilicilios.service.DeliverOrderService;

import java.util.ArrayList;
import java.util.List;

public class DeliverOrderServiceImpl implements DeliverOrderService {

    private static final String NAME = "out";

    private DroneDao droneDao;
    private PropertiesDao propertiesDao;
    private PositionDao positionDao;
    private InstructionBuilder builder;

    public DeliverOrderServiceImpl() {
        this.propertiesDao = new PropertiesDaoImpl();
        this.droneDao = new DroneDaoImpl(this.propertiesDao);
        this.positionDao = new PositionDaoImpl();
        this.builder = new FileInstruction();
    }

    @Override
    public void executeOrder(final List<String> instructionList) {
        List<Order> orderList = new ArrayList<>();
        for (String instruction : instructionList) {
            orderList.add(generateOrder(instruction));
        }
        Drone drone = new Drone(orderList);
        droneDao.carryOrders(drone);
        generateReportOfDrone(drone);
    }

    private void generateReportOfDrone(final Drone drone) {
        builder.writePosition(positionDao.convertPositionToString(drone.getVisitedLocations()));
    }

    private Order generateOrder(final String instruction) {
        return new Order(false, instruction);
    }


}
