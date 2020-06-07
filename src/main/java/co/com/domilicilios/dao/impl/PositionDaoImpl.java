package co.com.domilicilios.dao.impl;

import co.com.domilicilios.dao.PositionDao;
import co.com.domilicilios.model.Position;

import java.util.List;
import java.util.stream.Collectors;

public class PositionDaoImpl implements PositionDao {

    @Override
    public List<String> convertPositionToString(List<Position> visitedLocations) {
        return visitedLocations.stream().map(p-> p.toString()).collect(Collectors.toList());
    }
}
