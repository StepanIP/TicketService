package com.example.ticketservice.dto.request;

import lombok.*;

@EqualsAndHashCode
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SigninRequest {
    private String email;
    private String password;
}