package com.example.ticketservice.controller;

import com.example.ticketservice.model.BusRoute;
import com.example.ticketservice.service.BusRouteService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/v1/Rocket/routes")
public class BusRouteController {

    private final BusRouteService busRouteService;

    public BusRouteController(BusRouteService busRouteService) {
        this.busRouteService = busRouteService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<BusRoute>> getAll() {
        return new ResponseEntity<>(busRouteService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<List<BusRoute>> searchRoutes(
            @RequestParam(value = "from", required = false) String from,
            @RequestParam(value = "to", required = false) String to,
            @RequestParam(value = "date", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime date) {

        if (from != null && to != null && date != null) {
            return new ResponseEntity<>(busRouteService.findBusRoutesByDeparturePointAndDestinationPointAndDepartureDatetime(from, to, date), HttpStatus.OK);
        } else if (from != null && to != null) {
            return new ResponseEntity<>(busRouteService.findBusRoutesByDeparturePointAndDestinationPoint(from, to), HttpStatus.OK);
        } else if (from != null && date != null) {
            return new ResponseEntity<>(busRouteService.findBusRoutesByDeparturePointAndDepartureDatetime(from, date), HttpStatus.OK);
        } else if (to != null && date != null) {
            return new ResponseEntity<>(busRouteService.findBusRoutesByDestinationPointAndDepartureDatetime(to, date), HttpStatus.OK);
        } else if (from != null) {
            return new ResponseEntity<>(busRouteService.findBusRoutesByDeparturePoint(from), HttpStatus.OK);
        } else if (to != null) {
            return new ResponseEntity<>(busRouteService.findBusRoutesByDestinationPoint(to), HttpStatus.OK);
        } else if (date != null) {
            return new ResponseEntity<>(busRouteService.findBusRoutesByDepartureDatetime(date), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

}
