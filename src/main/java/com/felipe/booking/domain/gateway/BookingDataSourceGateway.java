package com.felipe.booking.domain.gateway;

import com.felipe.booking.domain.model.Booking;
import com.felipe.booking.domain.model.BookingLock;
import reactor.core.publisher.Mono;

import java.util.List;

public interface BookingDataSourceGateway {
    Mono<BookingLock> getLock();
    Mono<List<Booking>> findAllBookings();
    Mono<Booking> saveBooking(Booking booking, BookingLock lock);
    Mono<Void> deleteBooking(String id, BookingLock lock);
    Mono<Booking> findBooking(String id);
}
