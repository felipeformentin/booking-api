package com.felipe.booking.alten.domain.validations

import com.felipe.booking.alten.domain.model.Booking
import reactor.core.publisher.Mono

interface BookingValidator<T> {
    fun validate(booking: Booking): Mono<T>
}