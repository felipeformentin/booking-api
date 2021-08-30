package com.felipe.booking.alten.domain.usecase

import com.felipe.booking.alten.domain.gateway.BookingDataSourceGateway
import com.felipe.booking.alten.domain.model.Booking
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono

@Component
class CreateBookingUseCase(
    private val bookingRepositoryGateway: BookingDataSourceGateway
) {

    fun execute(booking: Booking): Mono<Booking> =
        bookingRepositoryGateway
            .getLock()
            .flatMap { lock -> bookingRepositoryGateway.createBooking(booking, lock) }
}