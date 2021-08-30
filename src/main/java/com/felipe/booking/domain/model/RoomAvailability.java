package com.felipe.booking.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
public class RoomAvailability {
    private List<LocalDate> daysAvailable;
    private List<LocalDate> daysBooked;
}
