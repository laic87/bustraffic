package se.sbab.busbackend.service;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import se.sbab.busbackend.model.BusLine;
import se.sbab.busbackend.model.BusStop;
import se.sbab.busbackend.utility.SimpleJSON;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toMap;

@Service
public class BusRouteServiceImpl implements BusRouteService {

    private final static String API_KEY = "key=5da196d47f8f4e5facdb68d2e25b9eae";
    private final static String MODEL = "&model=JourneyPatternPointOnLine&DefaultTransportModeCode=BUS";
    private final static String SL_API = API_KEY + MODEL;

    private final static String STOP_MODEL = "&model=stop&DefaultTransportModeCode=BUS";
    private final static String SL_STOP_API = API_KEY + STOP_MODEL;

    @Value("${sl.url}")
    private String BASE_URL;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public LinkedHashMap<String, List<BusLine>> getBusRoutes() throws JSONException {
        HashMap<String, HashMap> busRoutesHashMap = restTemplate.getForObject(BASE_URL + SL_API, HashMap.class);

        List<BusStop> busStopList = getStopsByName();

        final Object busRoutesObject = busRoutesHashMap.get("ResponseData").get("Result");
        final JSONArray busRoutesJSONArray = (JSONArray) SimpleJSON.toJSON(busRoutesObject);
        ArrayList<BusLine> buses =  new ArrayList<BusLine>();
        for (int i = 0; i < busRoutesJSONArray.length(); i++) {
            final JSONObject jsonObject = busRoutesJSONArray.getJSONObject(i);
            String journeyPatternPointNumber = jsonObject.getString("JourneyPatternPointNumber");
            busStopList.stream()
                    .filter(busStop -> busStop.getStopPointNumber().equals(journeyPatternPointNumber))
                    .collect(Collectors.toList());
            BusStop busStop = busStopList.isEmpty() ? null : busStopList.get(0);
            String lineNumber = jsonObject.getString("LineNumber");
            String stopName = busStop != null ? busStop.getStopPointName() : "Nothing";
            final BusLine busLine = new BusLine(lineNumber, stopName);
            buses.add(busLine);
        }

        return getTopTenRoutes(buses);
    }

    @Override
    public List<BusStop> getStopsByName() throws JSONException {
        HashMap<String, HashMap> busStopHashMap = restTemplate.getForObject(BASE_URL + SL_STOP_API, HashMap.class);
        final Object busStopObject = busStopHashMap.get("ResponseData").get("Result");
        final JSONArray busStopJSONArray = (JSONArray) SimpleJSON.toJSON(busStopObject);
        ArrayList<BusStop> stops =  new ArrayList<BusStop>();
        for (int i = 0; i < busStopJSONArray.length(); i++) {
            final JSONObject jsonObject = busStopJSONArray.getJSONObject(i);
            final BusStop bus = new BusStop(jsonObject.getString("StopPointNumber"), jsonObject.getString("StopPointName"));
            stops.add(bus);
        }
        return stops;
    }

    private LinkedHashMap<String, List<BusLine>> getTopTenRoutes(List<BusLine> busLines) {
        System.out.println("Time to get top 10!");

        Map<String, List<BusLine>> busMapByJourney;
        busMapByJourney = busLines.stream().collect(Collectors.groupingBy(busLine -> busLine.getLineNumber()));

        LinkedHashMap<String, List<BusLine>> collectTopTen = busMapByJourney.entrySet().stream()
                .sorted(Comparator.<Map.Entry<String, List<BusLine>>>comparingInt(entry -> entry.getValue().size()).reversed())
                .limit(10).collect(toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (key, value) -> {
                            throw new AssertionError();
                        },
                        LinkedHashMap::new
                ));

        return collectTopTen;
    }

}
