package com.felipe.booking.alten.domain.validations

import com.felipe.booking.alten.domain.model.Booking
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import org.springframework.web.server.ResponseStatusException
import reactor.core.publisher.Mono
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit

@Component
class ReservationPeriodValidator: BookingValidator<Booking> {

    companion object {
        const val MAX_STAY = 2
    }

    override fun validate(booking: Booking): Mono<Booking> =
        if (bookingPeriodNotAllowed(booking.checkInDate, booking.checkOutDate))
            Mono.error(ResponseStatusException(HttpStatus.BAD_REQUEST,
                "Your stay is longer than the maximum period allowed ($MAX_STAY days)"))
        else Mono.empty()

    private fun bookingPeriodNotAllowed(checkInDate: LocalDateTime, checkOutDate: LocalDateTime) =
        ChronoUnit.DAYS.between(checkInDate, checkOutDate) > MAX_STAY
}