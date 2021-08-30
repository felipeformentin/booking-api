package com.felipe.booking.alten.domain.gateway

import com.felipe.booking.alten.domain.model.Booking
import com.felipe.booking.alten.domain.model.BookingLock
import reactor.core.publisher.Mono

interface BookingDataSourceGateway {

    fun createBooking(booking: Booking, lock: BookingLock): Mono<Booking>
    fun getLock(): Mono<BookingLock>
}