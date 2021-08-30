package com.felipe.booking.alten.domain.model

import java.time.LocalDate

data class RoomAvailability(
    val daysAvailable: List<LocalDate>,
    val daysBooked: List<LocalDate>
)