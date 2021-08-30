package com.felipe.booking.alten.domain.usecase

import com.felipe.booking.alten.domain.gateway.BookingDataSourceGateway
import com.felipe.booking.alten.domain.model.Booking
import com.felipe.booking.alten.domain.model.BookingLock
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono

@Component
class CreateBookingUseCase(
    private val validateBookingUseCase: ValidateBookingUseCase,
    private val bookingRepositoryGateway: BookingDataSourceGateway
) {

    fun execute(booking: Booking): Mono<Booking> =
        bookingRepositoryGateway
            .getLock()
            .flatMap { lock -> saveBooking(booking, lock) }

    fun saveBooking(booking: Booking, lock: BookingLock) =
        validateBookingUseCase
            .validate(booking)
            .flatMap { bookingRepositoryGateway.createBooking(booking, lock) }
}