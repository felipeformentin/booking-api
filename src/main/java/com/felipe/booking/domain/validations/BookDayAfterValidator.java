package com.felipe.booking.domain.validations;

import com.felipe.booking.domain.model.Booking;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Component
public class BookDayAfterValidator implements BookingValidator<Booking> {

    @Override
    public Mono<Booking> validate(Booking booking) {
        if (isBookingBeforeTomorrow(booking.getCheckInDate()))
            return Mono.error(new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "You can't make bookings without at least 1 day in advance."));
        return Mono.empty();
    }

    private boolean isBookingBeforeTomorrow(LocalDateTime checkInDate) {
       return ChronoUnit.DAYS.between(LocalDate.now(), checkInDate.toLocalDate()) < 1;
    }
}
