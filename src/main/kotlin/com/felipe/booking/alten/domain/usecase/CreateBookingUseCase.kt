package com.felipe.booking.alten.domain.usecase

import com.felipe.booking.alten.app.entity.BookingEntity
import com.felipe.booking.alten.domain.gateway.BookingDataSourceGateway
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono

@Component
class CreateBookingUseCase(
    private val bookingRepositoryGateway: BookingDataSourceGateway
) {

    fun execute(): Mono<BookingEntity> =
        bookingRepositoryGateway.createBooking()
}