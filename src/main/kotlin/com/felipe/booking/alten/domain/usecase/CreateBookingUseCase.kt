package com.felipe.booking.alten.domain.usecase

import com.felipe.booking.alten.domain.gateway.BookingDataSourceGateway
import com.felipe.booking.alten.domain.model.Booking
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono

@Component
class CreateBookingUseCase(
    private val bookingRepositoryGateway: BookingDataSourceGateway
) {

    fun execute(): Mono<Booking> =
        bookingRepositoryGateway
            .getLock()
            .flatMap { bookingRepositoryGateway.createBooking(it) }
}