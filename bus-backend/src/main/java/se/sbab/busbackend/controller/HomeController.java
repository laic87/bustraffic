package se.sbab.busbackend.controller;

import org.json.JSONException;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import se.sbab.busbackend.model.Bus;
import se.sbab.busbackend.service.BusJourneyLineServiceImpl;

import org.slf4j.Logger;

import java.util.LinkedHashMap;
import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/")
public class HomeController {

    private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

    @Autowired
    private BusJourneyLineServiceImpl busJourneyLineService;

    @GetMapping(value = "/bus", produces = MediaType.APPLICATION_JSON_VALUE)
    public LinkedHashMap<String, List<Bus>> getTraffic() throws JSONException {
        logger.info("Controller getTraffic function got called...");
        return busJourneyLineService.getBusJourneyLine();
    }
}
