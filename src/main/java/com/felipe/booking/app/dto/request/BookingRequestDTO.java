package com.felipe.booking.app.dto.request;

import com.felipe.booking.domain.model.Booking;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingRequestDTO {
    private LocalDateTime checkInDate;
    private LocalDateTime checkOutDate;

    public static BookingRequestDTO of(Booking booking) {
        return new BookingRequestDTO(booking.getCheckInDate(), booking.getCheckOutDate());
    }

    public Booking toDomain() {
        return new Booking(null, this.checkInDate, this.checkOutDate);
    }

    public Booking toDomain(String id) {
        return new Booking(id, this.checkInDate, this.checkOutDate);
    }
}
