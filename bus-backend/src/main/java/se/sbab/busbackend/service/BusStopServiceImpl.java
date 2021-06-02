package se.sbab.busbackend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;

@Service
public class BusStopServiceImpl implements BusStopService {

    private final static String API_KEY = "key=5da196d47f8f4e5facdb68d2e25b9eae";
    private final static String STOP_MODEL = "&model=stop&DefaultTransportModeCode=BUS";
    private final static String SL_STOP_API = API_KEY + STOP_MODEL;

    @Value("${sl.url}")
    private String BASE_URL;

    @Autowired
    private RestTemplate restTemplate;


    @Override
    public Object getBussStop() {
        HashMap<String, HashMap> hashmap = restTemplate.getForObject(BASE_URL + SL_STOP_API, HashMap.class);
        HashMap<String, HashMap> responseDataHashMap = hashmap.get("ResponseData");
        //System.out.println(responseDataHashMap.get("Result"));
        System.out.println(BASE_URL + SL_STOP_API);
        return responseDataHashMap;
    }
}
