package com.example.ticketservice.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tickets")
@Builder
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "route_id")
    private BusRoute busRoute;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    private LocalDateTime purchaseDatetime;
}
