package com.felipe.booking.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class Booking {
    private LocalDateTime checkInDate;
    private LocalDateTime checkOutDate;
}
