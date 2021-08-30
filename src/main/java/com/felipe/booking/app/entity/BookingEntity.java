package com.felipe.booking.app.entity;

import com.felipe.booking.domain.model.Booking;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(value = "booking")
@NoArgsConstructor
@AllArgsConstructor
public class BookingEntity {

    @Id
    private String id;
    private LocalDateTime checkInDate;
    private LocalDateTime checkOutDate;
    private LocalDateTime creationDate;
    private LocalDateTime updateDate;

    public static BookingEntity of(Booking booking) {
        return new BookingEntity(
                null,
                booking.getCheckInDate(),
                booking.getCheckOutDate(),
                LocalDateTime.now(),
                LocalDateTime.now()
        );
    }

    public Booking toDomain() {
        return new Booking(
                this.checkInDate,
                this.checkOutDate
        );
    }
}
