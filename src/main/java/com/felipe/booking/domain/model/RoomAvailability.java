package com.felipe.booking.domain.model;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class RoomAvailability {
    private List<LocalDateTime> daysAvailable;
    private List<LocalDateTime> daysBooked;
}
