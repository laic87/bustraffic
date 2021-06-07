package se.sbab.busbackend.controller;

import org.json.JSONException;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import se.sbab.busbackend.model.BusLine;

import org.slf4j.Logger;
import se.sbab.busbackend.service.BusRouteServiceImpl;

import java.util.LinkedHashMap;
import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/busapi")
public class BusRouteController {

    private static final Logger logger = LoggerFactory.getLogger(BusRouteController.class);

    @Autowired
    private BusRouteServiceImpl busRouteServiceImpl;

    @GetMapping(value = "/routes", produces = MediaType.APPLICATION_JSON_VALUE)
    public LinkedHashMap<String, List<BusLine>> getTraffic() throws JSONException {
        logger.debug("Controller getTraffic function got called...");
        return busRouteServiceImpl.getBusRoutes();
    }
}
