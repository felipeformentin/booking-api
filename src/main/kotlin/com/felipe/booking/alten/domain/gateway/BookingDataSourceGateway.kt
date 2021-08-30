package com.felipe.booking.alten.domain.gateway

import com.felipe.booking.alten.app.entity.BookingEntity
import reactor.core.publisher.Mono

interface BookingDataSourceGateway {

    fun createBooking(): Mono<BookingEntity>
}