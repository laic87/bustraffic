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
import se.sbab.busbackend.model.Result;
import se.sbab.busbackend.utility.SimpleJSON;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class BusJourneyLineServiceImpl implements BusJourneyLineService {

    private final static String API_KEY = "key=5da196d47f8f4e5facdb68d2e25b9eae";
    private final static String MODEL = "&model=JourneyPatternPointOnLine&DefaultTransportModeCode=BUS";
    private final static String SL_API = API_KEY + MODEL;

    @Value("${sl.url}")
    private String BASE_URL;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public Object getBusJourneyLine() throws JSONException {
        HashMap<String, HashMap> hashmap = restTemplate.getForObject(BASE_URL + SL_API, HashMap.class);
        final Object o1 = hashmap.get("ResponseData").get("Result");
        final JSONArray o = (JSONArray) SimpleJSON.toJSON(o1);
        ArrayList<Bus> buses =  new ArrayList<Bus>();
        for (int i=0; i < o.length(); i++) {
            final JSONObject jsonObject = o.getJSONObject(i);
            final Bus bus = new Bus(jsonObject.getString("JourneyPatternPointNumber"), jsonObject.getString("LineNumber"));
            buses.add(bus);
        }

        Map<String, List<Bus>> busMapByJourney;
        busMapByJourney = buses.stream().collect(Collectors.groupingBy(bus -> bus.getJourneyPatternPointNumber()));



        //List<Result> newResult = new 
        // ArrayList<>();
        //System.out.println(newResult);
        //System.out.println(BASE_URL + SL_API);
        return o1;
    }
}
