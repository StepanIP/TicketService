package com.example.ticketservice.service.impl;

import com.example.ticketservice.exception.NullEntityReferenceException;
import com.example.ticketservice.model.BusRoute;
import com.example.ticketservice.repository.BusRouteRepository;
import com.example.ticketservice.service.BusRouteService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class BusRouteServiceImpl implements BusRouteService {

    private final BusRouteRepository busRouteRepository;

    public BusRouteServiceImpl(BusRouteRepository busRouteRepository) {
        this.busRouteRepository = busRouteRepository;
    }

    @Override
    public BusRoute create(BusRoute busRoute) {
        if (busRoute != null) {
            return busRouteRepository.save(busRoute);
        }
        throw new NullEntityReferenceException("BusRoute cannot be 'null'");
    }

    @Override
    public BusRoute readById(long id) {
        return busRouteRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("BusRoute with id " + id + " not found"));
    }

    @Override
    public BusRoute update(BusRoute busRoute) {
        if (busRoute != null) {
            readById(busRoute.getId());
            return busRouteRepository.save(busRoute);
        }
        throw new NullEntityReferenceException("BusRoute cannot be 'null'");
    }

    @Override
    public void delete(long id) {
        busRouteRepository.delete(readById(id));
    }

    @Override
    public List<BusRoute> getAll() {
        List<BusRoute> busRoutes = busRouteRepository.findAll();
        return busRoutes.isEmpty() ? new ArrayList<>() : busRoutes;
    }

    @Override
    public List<BusRoute> findBusRoutesByDeparturePoint(String departurePoint) {
        return busRouteRepository.getBusRoutesByDeparturePoint(departurePoint);
    }

    @Override
    public List<BusRoute> findBusRoutesByDestinationPoint(String destinationPoint) {
        return busRouteRepository.getBusRoutesByDestinationPoint(destinationPoint);
    }

    @Override
    public List<BusRoute> findBusRoutesByDepartureDatetime(LocalDateTime departureDatetime) {
        return busRouteRepository.getBusRoutesByDepartureDatetime(departureDatetime);
    }

    @Override
    public List<BusRoute> findBusRoutesByDeparturePointAndDestinationPointAndDepartureDatetime(String departurePoint, String destinationPoint, LocalDateTime departureDatetime) {
        return busRouteRepository.getBusRoutesByDeparturePointAndDestinationPointAndDepartureDatetime(departurePoint,destinationPoint,departureDatetime);
    }

    @Override
    public List<BusRoute> findBusRoutesByDeparturePointAndDestinationPoint(String departurePoint, String destinationPoint) {
        return busRouteRepository.getBusRoutesByDeparturePointAndDestinationPoint(departurePoint, destinationPoint);
    }

    @Override
    public List<BusRoute> findBusRoutesByDeparturePointAndDepartureDatetime(String departurePoint, LocalDateTime departureDatetime) {
        return busRouteRepository.getBusRoutesByDeparturePointAndDepartureDatetime(departurePoint, departureDatetime);
    }

    @Override
    public List<BusRoute> findBusRoutesByDestinationPointAndDepartureDatetime(String destinationPoint, LocalDateTime departureDatetime) {
        return busRouteRepository.getBusRoutesByDestinationPointAndDepartureDatetime(destinationPoint, departureDatetime);
    }

}
