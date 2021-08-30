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
public class DaysInAdvanceValidator implements BookingValidator<Booking> {

    private static int DAYS_IN_ADVANCE = 30;

    @Override
    public Mono<Booking> validate(Booking booking) {
        if (isBookingDateNotAllowed(booking.getCheckInDate()))
            return Mono.error(new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "You can't book more than 30 days in advance."));
        return Mono.empty();
    }

    private boolean isBookingDateNotAllowed(LocalDateTime checkInDate) {
       return ChronoUnit.DAYS.between(LocalDate.now(), checkInDate.toLocalDate()) > DAYS_IN_ADVANCE;
    }
}
