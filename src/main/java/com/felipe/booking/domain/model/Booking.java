package com.felipe.booking.domain.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Booking {
    private LocalDateTime checkInDate;
    private LocalDateTime checkOutDate;
}
