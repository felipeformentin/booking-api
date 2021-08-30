package com.felipe.booking.alten.app.dto

import com.felipe.booking.alten.domain.model.RoomAvailability
import java.time.LocalDate

data class RoomAvailabilityDTO(
    val daysAvailable: List<LocalDate>,
    val daysBooked: List<LocalDate>
) {
    companion object {
        fun of(roomAvailability: RoomAvailability) =
            RoomAvailabilityDTO(
                daysAvailable = roomAvailability.daysAvailable,
                daysBooked = roomAvailability.daysBooked
            )
    }
}