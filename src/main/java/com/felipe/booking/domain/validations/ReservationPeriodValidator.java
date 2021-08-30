package com.felipe.booking.domain.validations;

import com.felipe.booking.domain.model.Booking;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Component
public class ReservationPeriodValidator implements BookingValidator<Booking> {

    private static int MAX_STAY = 2;

    @Override
    public Mono<Booking> validate(Booking booking) {
        if (bookingPeriodNotAllowed(booking.getCheckInDate(), booking.getCheckOutDate()))
            return Mono.error(new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Your stay is longer than the maximum period allowed ($MAX_STAY days)"));
        return Mono.empty();
    }

    private boolean bookingPeriodNotAllowed(LocalDateTime checkInDate, LocalDateTime checkOutDate) {
       return ChronoUnit.DAYS.between(checkInDate, checkOutDate) > MAX_STAY;
    }
}
