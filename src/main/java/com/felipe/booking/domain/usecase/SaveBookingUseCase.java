package com.felipe.booking.domain.usecase;

import com.felipe.booking.domain.gateway.BookingDataSourceGateway;
import com.felipe.booking.domain.model.Booking;
import com.felipe.booking.domain.model.BookingLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class SaveBookingUseCase {

    @Autowired
    private ValidateBookingUseCase validateBookingUseCase;

    @Autowired
    private BookingDataSourceGateway bookingDataSourceGateway;


    public Mono<Booking> execute(Booking booking) {
        return bookingDataSourceGateway
                .getLock()
                .flatMap (lock -> saveBooking(booking, lock));
    }

    private Mono<Booking> saveBooking(Booking booking, BookingLock lock) {
        return validateBookingUseCase
                .validate(booking)
                .switchIfEmpty( Mono.defer(() ->
                        bookingDataSourceGateway.saveBooking(booking, lock)));
        }
}
