package se.sbab.busbackend.service;

import org.json.JSONException;
import se.sbab.busbackend.model.Bus;

import java.util.LinkedHashMap;
import java.util.List;

public interface BusJourneyLineService {
    LinkedHashMap<String, List<Bus>> getBusJourneyLine() throws JSONException;
}
