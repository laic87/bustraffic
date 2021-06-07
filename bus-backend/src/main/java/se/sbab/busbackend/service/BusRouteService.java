package se.sbab.busbackend.service;

import org.json.JSONException;
import se.sbab.busbackend.model.BusStop;
import se.sbab.busbackend.model.BusLine;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public interface BusRouteService {
    LinkedHashMap<String, List<BusLine>> getBusRoutes() throws JSONException;
    List<BusStop> getStopsByName() throws JSONException;
}
