package com.felipe.booking.domain.validations;

import com.felipe.booking.domain.gateway.BookingAvailabilityGateway;
import com.felipe.booking.domain.model.Booking;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Set;

@Component
public class IsAlreadyBookedValidator implements BookingValidator<Booking> {

    @Autowired
    private BookingAvailabilityGateway bookingAvailabilityGateway;

    @Override
    public Mono<Booking> validate(Booking booking) {
        return bookingAvailabilityGateway
                .getBookedDays()
                .flatMap(bookedDays ->  getBookingMono(booking, bookedDays));
    }

    private Mono<Booking> getBookingMono(Booking booking, Set<LocalDate> bookedDays) {
        LocalDate checkIn = booking.getCheckInDate().toLocalDate();
        LocalDate checkOut = booking.getCheckOutDate().toLocalDate();
        if (isDateAlreadyBooked(bookedDays, checkIn, checkOut))
            return Mono.error(new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "There's already a booking for the selected date."));
        return Mono.empty();
    }

    private boolean isDateAlreadyBooked(Set<LocalDate> bookedDays, LocalDate checkIn, LocalDate checkOut) {
        return  bookedDays.contains(checkIn)
                || bookedDays.contains(checkOut)
                || (ChronoUnit.DAYS.between(checkIn, checkOut) == 2 && bookedDays.contains(checkIn.plusDays(1)));
    }
}
