package com.felipe.booking.alten.app.dto

import com.felipe.booking.alten.domain.model.Booking
import java.time.LocalDateTime

data class BookingDTO(
    val checkInDate: LocalDateTime,
    val checkOutDate: LocalDateTime,
) {

    companion object {
        fun of(booking: Booking) =
            BookingDTO(
                checkInDate =  booking.checkInDate,
                checkOutDate = booking.checkOutDate
            )
    }

    fun toDomain() =
        Booking(
            checkInDate = checkInDate,
            checkOutDate = checkOutDate
        )
}