package se.sbab.busbackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import se.sbab.busbackend.model.Bus;
import se.sbab.busbackend.service.BusServiceImpl;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/bus")
public class HomeController {

    @Autowired
    private BusServiceImpl busService;

    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public Object getTraffic() {
        return busService.getAllBusStop();
    }
}
