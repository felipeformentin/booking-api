package com.felipe.booking.app.dto.response;

import com.felipe.booking.domain.model.Booking;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class BookingResponseDTO {
    private String id;
    private LocalDateTime checkInDate;
    private LocalDateTime checkOutDate;

    public static BookingResponseDTO of(Booking booking) {
        return new BookingResponseDTO(booking.getId(), booking.getCheckInDate(), booking.getCheckOutDate());
    }
}
