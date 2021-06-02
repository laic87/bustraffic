package se.sbab.busbackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import se.sbab.busbackend.model.Bus;
import se.sbab.busbackend.service.BusJourneyLineServiceImpl;
import se.sbab.busbackend.service.BusServiceImpl;
import se.sbab.busbackend.service.BusStopServiceImpl;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/")
public class HomeController {

    @Autowired
    private BusJourneyLineServiceImpl busJourneyLineService;

    @Autowired
    private BusStopServiceImpl busStopService;

    @GetMapping(value = "/bus", produces = MediaType.APPLICATION_JSON_VALUE)
    public Object getTraffic() {
        return busJourneyLineService.getBusJourneyLine();
    }

    @GetMapping(value = "/busstop", produces = MediaType.APPLICATION_JSON_VALUE)
    public Object getStops() {
        return busStopService.getBussStop();
    }


}
