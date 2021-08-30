package com.felipe.booking.alten.domain.usecase

import com.felipe.booking.alten.domain.model.Booking
import com.felipe.booking.alten.domain.validations.BookingValidator
import org.springframework.stereotype.Component
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Component
class ValidateBookingUseCase(
    private val validators: Set<BookingValidator<Booking>>
) {

    fun validate(booking: Booking) =
        Mono.just(booking)
            .flatMap { executeValidations(booking) }

    private fun executeValidations(booking: Booking) =
        Flux.fromIterable(validators)
            .flatMap { it.validate(booking) }
            .collectList()
}