package se.sbab.busbackend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import se.sbab.busbackend.model.Result;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
    public Object getBusJourneyLine() {
        HashMap<String, HashMap> hashmap = restTemplate.getForObject(BASE_URL + SL_API, HashMap.class);
        HashMap<String, HashMap> responseDataHashMap = hashmap.get("ResponseData");
        List<Result> result = (List<Result>) responseDataHashMap.get("Result");
        System.out.println(result.size());
        //List<Result> newResult = new ArrayList<>();
        //System.out.println(newResult);
        //System.out.println(BASE_URL + SL_API);
        return responseDataHashMap;
    }
}
