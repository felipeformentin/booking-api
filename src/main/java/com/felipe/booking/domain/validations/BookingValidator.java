package com.felipe.booking.domain.validations;

import com.felipe.booking.domain.model.Booking;
import reactor.core.publisher.Mono;

public interface BookingValidator<T> {
    Mono<T> validate(Booking booking);
}
