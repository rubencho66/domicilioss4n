package co.com.domilicilios.dao;

import co.com.domilicilios.model.Position;

import java.util.List;

public interface PositionDao {

    List<String> convertPositionToString(List<Position> visitedLocations);
}
