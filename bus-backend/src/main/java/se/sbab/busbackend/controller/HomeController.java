package se.sbab.busbackend.controller;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import se.sbab.busbackend.service.BusJourneyLineServiceImpl;
import se.sbab.busbackend.service.BusStopServiceImpl;

import org.slf4j.Logger;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/")
public class HomeController {

    private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

    @Autowired
    private BusJourneyLineServiceImpl busJourneyLineService;

    @Autowired
    private BusStopServiceImpl busStopService;

    @GetMapping(value = "/bus", produces = MediaType.APPLICATION_JSON_VALUE)
    public Object getTraffic() {
        logger.info("Controller getTraffic function got called...");
        return busJourneyLineService.getBusJourneyLine();
    }

    @GetMapping(value = "/busstop", produces = MediaType.APPLICATION_JSON_VALUE)
    public Object getStops() {
        logger.info("Controller gettBusStop function got called...");
        return busStopService.getBussStop();
    }


}
