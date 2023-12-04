package com.example.ticketservice.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "bus_routes")
@Builder
public class BusRoute {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String departurePoint;
    private String destinationPoint;
    private LocalDateTime departureDatetime;
    private Double ticketPrice;
    private Integer availableTickets;

}
