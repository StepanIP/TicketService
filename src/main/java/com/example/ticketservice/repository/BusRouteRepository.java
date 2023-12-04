package com.example.ticketservice.repository;

import com.example.ticketservice.model.BusRoute;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface BusRouteRepository extends JpaRepository<BusRoute, Long> {
    List<BusRoute> getBusRoutesByDeparturePoint(String departurePoint);
    List<BusRoute> getBusRoutesByDestinationPoint(String destinationPoint);
    List<BusRoute> getBusRoutesByDepartureDatetime(LocalDateTime departureDatetime);

    List<BusRoute> getBusRoutesByDeparturePointAndDestinationPointAndDepartureDatetime(
            String departurePoint, String destinationPoint, LocalDateTime departureDatetime);

    List<BusRoute> getBusRoutesByDeparturePointAndDestinationPoint(
            String departurePoint, String destinationPoint);

    List<BusRoute> getBusRoutesByDeparturePointAndDepartureDatetime(
            String departurePoint, LocalDateTime departureDatetime);

    List<BusRoute> getBusRoutesByDestinationPointAndDepartureDatetime(
            String destinationPoint, LocalDateTime departureDatetime);

}
