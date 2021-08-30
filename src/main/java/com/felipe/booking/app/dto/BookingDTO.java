package com.felipe.booking.app.dto;

import com.felipe.booking.domain.model.Booking;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class BookingDTO {
    private LocalDateTime checkInDate;
    private LocalDateTime checkOutDate;

    public static BookingDTO of(Booking booking) {
        return new BookingDTO(booking.getCheckInDate(), booking.getCheckOutDate());
    }

    public Booking toDomain() {
        return new Booking(this.checkInDate, this.checkOutDate);
    }
}
