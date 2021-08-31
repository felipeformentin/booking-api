package com.felipe.booking.domain.gateway;

import com.felipe.booking.domain.model.Booking;
import com.felipe.booking.domain.model.BookingLock;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public interface BookingAvailabilityGateway {
    Mono<Set<LocalDate>> getBookedDays();
    Mono<Set<LocalDate>> getBookedDaysMinusCurrentBooking(Booking booking);
}
