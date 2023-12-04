package com.example.ticketservice.service;

import com.example.ticketservice.model.BusRoute;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public interface BusRouteService {

    BusRoute create(BusRoute user);
    BusRoute readById(long id);
    BusRoute update(BusRoute user);
    void delete(long id);
    List<BusRoute> getAll();
    List<BusRoute> findBusRoutesByDeparturePoint(String departurePoint);
    List<BusRoute> findBusRoutesByDestinationPoint(String destinationPoint);
    List<BusRoute> findBusRoutesByDepartureDatetime(LocalDateTime departureDatetime);

    List<BusRoute> findBusRoutesByDeparturePointAndDestinationPointAndDepartureDatetime(
            String departurePoint, String destinationPoint, LocalDateTime departureDatetime);

    List<BusRoute> findBusRoutesByDeparturePointAndDestinationPoint(
            String departurePoint, String destinationPoint);

    List<BusRoute> findBusRoutesByDeparturePointAndDepartureDatetime(
            String departurePoint, LocalDateTime departureDatetime);

    List<BusRoute> findBusRoutesByDestinationPointAndDepartureDatetime(
            String destinationPoint, LocalDateTime departureDatetime);

}
