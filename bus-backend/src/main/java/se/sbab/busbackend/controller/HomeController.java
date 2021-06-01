package se.sbab.busbackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import se.sbab.busbackend.service.BusServiceImpl;

@CrossOrigin("localhost:3000")
@RestController
@RequestMapping("/buss")
public class HomeController {

    @Autowired
    private BusServiceImpl busService;

    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public void getTraffic() {
        busService.getBussTraffic();
    }


}
