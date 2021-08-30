package com.felipe.booking.domain.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BookingLock {
    private String roomId;
    private Long version;
}
