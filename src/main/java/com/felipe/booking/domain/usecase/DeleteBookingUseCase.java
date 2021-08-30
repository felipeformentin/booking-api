package com.felipe.booking.domain.usecase;

import com.felipe.booking.domain.gateway.BookingDataSourceGateway;
import com.felipe.booking.domain.model.Booking;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class DeleteBookingUseCase {

    @Autowired
    private BookingDataSourceGateway bookingDataSourceGateway;

    public Mono<Void> delete(String id) {
        return bookingDataSourceGateway
                .getLock()
                .flatMap(lock -> bookingDataSourceGateway.deleteBooking(id, lock));
    }
}
