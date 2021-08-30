package com.felipe.booking.app.dto;

import com.felipe.booking.domain.model.RoomAvailability;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
public class RoomAvailabilityDTO {
    private List<LocalDate> daysAvailable;
    private List<LocalDate> daysBooked;

    public static RoomAvailabilityDTO of(RoomAvailability roomAvailability) {
        return new RoomAvailabilityDTO(roomAvailability.getDaysAvailable(), roomAvailability.getDaysBooked());
    }
}
