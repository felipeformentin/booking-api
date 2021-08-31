package com.felipe.booking.domain.validations;

import com.felipe.booking.domain.gateway.BookingDataSourceGateway;
import com.felipe.booking.domain.model.Booking;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@Component
public class IdExistsValidator implements BookingValidator<Booking> {

    @Autowired
    private BookingDataSourceGateway bookingDataSourceGateway;

    @Override
    public Mono<Booking> validate(Booking booking) {
        if (booking.getId() != null) {
            return bookingDataSourceGateway
                    .findBooking(booking.getId())
                    .switchIfEmpty( Mono.defer(() ->
                            Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND))));
        } 
        return Mono.empty();
    }

}
