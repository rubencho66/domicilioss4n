package co.com.domilicilios.process;

import co.com.domilicilios.facade.ManageRestaurantFacade;
import co.com.domilicilios.facade.imp.ManageRestaurantFacadeImpl;

public class Executor implements Runnable {

    private ManageRestaurantFacade facade;

    public Executor() {
        this.facade = new ManageRestaurantFacadeImpl();
    }

    @Override
    public void run() {
        facade.executeService();

    }
}
