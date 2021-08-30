package com.felipe.booking.alten.domain.gateway

import com.felipe.booking.alten.domain.model.Booking
import com.felipe.booking.alten.domain.model.BookingLock
import reactor.core.publisher.Mono

interface BookingDataSourceGateway {

    fun getLock(): Mono<BookingLock>
    fun findAllBookings(): Mono<List<Booking>>
    fun createBooking(booking: Booking, lock: BookingLock): Mono<Booking>
}