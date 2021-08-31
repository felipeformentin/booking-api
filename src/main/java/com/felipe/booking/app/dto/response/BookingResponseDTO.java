package com.felipe.booking.app.dto.response;

import com.felipe.booking.domain.model.Booking;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class BookingResponseDTO {
    private String id;
    private LocalDateTime checkInDate;
    private LocalDateTime checkOutDate;

    public static BookingResponseDTO of(Booking booking) {
        return new BookingResponseDTO(booking.getId(), booking.getCheckInDate(), booking.getCheckOutDate());
    }

    public static List<BookingResponseDTO> of(List<Booking> booking) {
        List<BookingResponseDTO> bookingsResponse = new ArrayList<>();
        booking.forEach(b -> {
            bookingsResponse.add(BookingResponseDTO.of(b));
        });
        return bookingsResponse;
    }
}
