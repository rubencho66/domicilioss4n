package co.com.domilicilios.dao.impl;

import co.com.domilicilios.common.Orientation;
import co.com.domilicilios.dao.PropertiesDao;
import co.com.domilicilios.dao.PositionDao;
import co.com.domilicilios.model.Position;
import org.apache.commons.collections4.CollectionUtils;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class PositionDaoImplTest {

    PositionDao dao;
    PropertiesDao daoFile;

    @Before
    public void setUp() {
        dao = new PositionDaoImpl();
        daoFile = new PropertiesDaoImpl();
    }

    @Test
    public void convertPositionToStringTest() {
        List<String> visitedLocations = dao.convertPositionToString(getPositionList());
        assertTrue(CollectionUtils.isNotEmpty(visitedLocations));
        assertEquals(daoFile.getSize(), visitedLocations.size());
        visitedLocations.forEach(v -> assertTrue(v.split(",").length == 3));
    }

    private List<Position> getPositionList() {
        List<Position> list = new ArrayList<>();
        int j=1;
        for (int i = 0; i < daoFile.getSize(); i++) {
            list.add(new Position(i, j++, Orientation.NORTH));
        }
        return list;
    }
}
