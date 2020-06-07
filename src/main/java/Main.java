import co.com.domilicilios.process.Executor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {

    final static Logger LOGGER = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        LOGGER.info("Start process");
        Thread t1 = new Thread(new Executor());
        t1.setName("1");
        Thread t2 = new Thread(new Executor());
        t2.setName("2");
        Thread t3 = new Thread(new Executor());
        t3.setName("3");
        Thread t4 = new Thread(new Executor());
        t4.setName("4");
        Thread t5 = new Thread(new Executor());
        t5.setName("5");
        // Starting threads
        t1.start();
        t2.start();
        t3.start();
        t4.start();
        LOGGER.info("End process");
    }

}
