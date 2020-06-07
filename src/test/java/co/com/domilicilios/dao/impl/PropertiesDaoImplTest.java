package co.com.domilicilios.dao.impl;

import co.com.domilicilios.dao.PropertiesDao;
import org.apache.commons.collections4.CollectionUtils;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class PropertiesDaoImplTest {

    PropertiesDao dao;

    @Before
    public void setUp() {
        dao = new PropertiesDaoImpl();
    }

    @Test
    public void getDistanceTest() {
        assertEquals("not the expected value for the distance", dao.getDistance(), 10);
    }

    @Test
    public void getSizeTest() {
        assertEquals("not the expected value for the size", dao.getSize(), 3);
    }

}
