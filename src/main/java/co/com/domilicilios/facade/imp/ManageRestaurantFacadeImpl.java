package co.com.domilicilios.facade.imp;

import co.com.domilicilios.builder.impl.FileInstructionImpl;
import co.com.domilicilios.builder.InstructionBuilder;
import co.com.domilicilios.facade.ManageRestaurantFacade;
import co.com.domilicilios.service.DeliverOrderService;
import co.com.domilicilios.service.impl.DeliverOrderServiceImpl;

public class ManageRestaurantFacadeImpl implements ManageRestaurantFacade {

    private DeliverOrderService service;
    private InstructionBuilder builder;

    public ManageRestaurantFacadeImpl() {
        this.service = new DeliverOrderServiceImpl();
        this.builder = new FileInstructionImpl();
    }

    @Override
    public void executeService() {
        service.executeOrder(builder.readInstruction());
    }
}
