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
public class IsCheckOutAfterCheckInValidator implements BookingValidator<Booking> {

    @Override
    public Mono<Booking> validate(Booking booking) {
        if (isCheckOutAfterCheckIn(booking.getCheckInDate(), booking.getCheckOutDate()))
            return Mono.error(new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Checkout date should be after checkin date."));
        return Mono.empty();
    }

    private boolean isCheckOutAfterCheckIn(LocalDateTime checkInDate, LocalDateTime checkOutDate) {
       return ChronoUnit.DAYS.between(checkInDate.toLocalDate(), checkOutDate.toLocalDate()) < 0;
    }
}
