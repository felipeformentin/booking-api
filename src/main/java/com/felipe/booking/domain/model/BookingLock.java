package com.felipe.booking.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class BookingLock {
    private String roomId;
    private Long version;
}
