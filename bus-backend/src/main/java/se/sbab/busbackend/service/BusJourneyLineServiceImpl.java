package se.sbab.busbackend.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import se.sbab.busbackend.model.Bus;
import se.sbab.busbackend.model.BusStop;
import se.sbab.busbackend.model.Result;
import se.sbab.busbackend.utility.SimpleJSON;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.Arrays.*;
import static java.util.stream.Collectors.toMap;

@Service
public class BusJourneyLineServiceImpl implements BusJourneyLineService {

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
    public LinkedHashMap<String, List<Bus>> getBusJourneyLine() throws JSONException {
        HashMap<String, HashMap> hashmap = restTemplate.getForObject(BASE_URL + SL_API, HashMap.class);
        HashMap<String, HashMap> stopHashMap = restTemplate.getForObject(BASE_URL + SL_STOP_API, HashMap.class);

        final Object o2 = stopHashMap.get("ResponseData").get("Result");
        final JSONArray o3 = (JSONArray) SimpleJSON.toJSON(o2);
        ArrayList<BusStop> stops =  new ArrayList<BusStop>();
        for (int i=0; i < o3.length(); i++) {
            final JSONObject jsonObject = o3.getJSONObject(i);
            final BusStop bus = new BusStop(jsonObject.getString("StopPointNumber"), jsonObject.getString("StopPointName"));
            stops.add(bus);
        }



        final Object o1 = hashmap.get("ResponseData").get("Result");
        final JSONArray o = (JSONArray) SimpleJSON.toJSON(o1);
        ArrayList<Bus> buses =  new ArrayList<Bus>();
        for (int i=0; i < o.length(); i++) {
            final JSONObject jsonObject = o.getJSONObject(i);
            String journeyPatternPointNumber = jsonObject.getString("JourneyPatternPointNumber");
            List<BusStop> busStopList = stops.stream()
                    .filter(busStop -> busStop.getStopPointNumber().equals(journeyPatternPointNumber))
                    .collect(Collectors.toList());
            BusStop busStop = busStopList.isEmpty() ? null : busStopList.get(0);
            String lineNumber = jsonObject.getString("LineNumber");
            String stopName = busStop != null ? busStop.getStopPointName() : "Nothing";
            final Bus bus = new Bus(lineNumber, stopName);
            buses.add(bus);
        }

        Map<String, List<Bus>> busMapByJourney;
        busMapByJourney = buses.stream().collect(Collectors.groupingBy(bus -> bus.getLineNumber()));

        LinkedHashMap<String, List<Bus>> collect = busMapByJourney.entrySet().stream()
                .sorted(Comparator.<Map.Entry<String, List<Bus>>>comparingInt(entry -> entry.getValue().size()).reversed())
                .limit(10).collect(toMap(
                Map.Entry::getKey,
                Map.Entry::getValue,
                (key, value) -> {
                    throw new AssertionError();
                },
                LinkedHashMap::new
        ));

        return collect;
    }
}
