package co.com.domilicilios.dao.impl;

import co.com.domilicilios.dao.PropertiesDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.*;

public class PropertiesDaoImpl implements PropertiesDao {

    final static Logger LOGGER = LoggerFactory.getLogger(PropertiesDaoImpl.class);

    private Properties config;
    private static final String SIZE_DEFAULT = "limit.order";
    private static final String DISTANCE_DEFAULT = "size.distance";

    private static final int SIZE_VALUE_DEFAULT = 3;
    private static final long DISTANCE_VALUE_DEFAULT = 10;

    @Override
    public long getDistance() {
        long distance;
        try {
            Properties prop = loadProperties();
            distance = Long.valueOf(prop.getProperty(DISTANCE_DEFAULT));
        } catch (IOException e) {
            LOGGER.warn("Could not load parameterized value, default value is set");
            distance = DISTANCE_VALUE_DEFAULT;
        }
        return distance;
    }

    @Override
    public int getSize() {
        int size;
        try {
            Properties prop = loadProperties();
            size = Integer.valueOf(prop.getProperty(SIZE_DEFAULT));
        } catch (IOException e) {
            LOGGER.warn("Could not load parameterized value, default value is set");
            size = SIZE_VALUE_DEFAULT;
        }
        return size;
    }

    private Properties loadProperties() throws IOException {
        if (Objects.isNull(config)) {
            this.config = new Properties();
            config.load(new FileInputStream("src/main/resources/config.properties"));
        }
        return config;
    }
}
